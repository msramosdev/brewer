package com.projeto.brewer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.projeto.brewer.model.Estilo;
import com.projeto.brewer.service.CadastroEstiloService;
import com.projeto.brewer.service.exception.NomeEstiloJaCadastradoException;

@Controller
@RequestMapping("/estilos")
public class EstilosController {
	
	@Autowired
	CadastroEstiloService cadastroEstiloService;

	@RequestMapping("/novo")
	public String novo(Estilo estilo) {
		return "/estilo/CadastroEstilo";
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public String salvar(@Validated Estilo estilo, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(estilo);
		}
		
		try {
			cadastroEstiloService.salvar(estilo);
		} catch(NomeEstiloJaCadastradoException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(estilo);
		}
		
		attributes.addFlashAttribute("mensagem", "Estilo salvo com sucesso!");
		return "redirect:/estilos/novo";
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<?> salvar(@RequestBody @Validated Estilo estilo, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(result.getFieldError("nome").getDefaultMessage());
		}
		
		cadastroEstiloService.salvar(estilo);
		return ResponseEntity.ok(estilo);
	}
	
}
