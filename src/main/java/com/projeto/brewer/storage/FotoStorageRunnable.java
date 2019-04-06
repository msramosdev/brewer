package com.projeto.brewer.storage;

import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import com.projeto.brewer.dto.FotoDTO;

public class FotoStorageRunnable implements Runnable {

	private MultipartFile[] files;
	private DeferredResult<FotoDTO> result;
	private FotoStorage fotoStorage;
	
	public FotoStorageRunnable(MultipartFile[] files, DeferredResult<FotoDTO> result, FotoStorage fotoStorage) {
		this.files = files;
		this.result = result;
		this.fotoStorage = fotoStorage;
	}

	@Override
	public void run() {
		String nomeFoto = fotoStorage.salvarTemporariamente(files);
		String contentType = files[0].getContentType();
		result.setResult(new FotoDTO(nomeFoto, contentType));
	}

}
