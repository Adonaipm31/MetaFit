package com.metaFit.metaFit.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping({"","/","inicio"})
	public String index() {
		return "index";	
	}
	
	@GetMapping("/nosotros")
	public String nosotros() {
		return "nosotros";	
	}
	
	@GetMapping("/contacto")
	public String contac() {
		return "contacto";	
	}
	
	@GetMapping({"/productos","productos.html"})
	public String pr() {
		return "productos";	
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		return "login";
	}

	@GetMapping({"/carrito","carrito.html"})
	public String carrito() {
		return "carrito";
	}
}
