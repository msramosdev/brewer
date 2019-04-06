package com.projeto.brewer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projeto.brewer.model.Cliente;

@Controller
public class ClientesController {
	
	@RequestMapping("clientes/novo")
	public String novo(Cliente cliente) {
		return "cliente/CadastroCliente";
	}

}
