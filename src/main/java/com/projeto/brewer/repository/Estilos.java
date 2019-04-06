package com.projeto.brewer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.brewer.model.Estilo;

public interface Estilos extends JpaRepository<Estilo, Long> {

	public Optional<Estilo> findByNomeIgnoreCase(String nome);

}
