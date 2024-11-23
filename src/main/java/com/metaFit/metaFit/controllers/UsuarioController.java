package com.metaFit.metaFit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.metaFit.metaFit.model.RegisterDTO;
import com.metaFit.metaFit.model.UsuarioModel;
import com.metaFit.metaFit.repositorie.UsuarioRepositorie;

import jakarta.validation.Valid;

@Controller
public class UsuarioController {
	@Autowired
	private UsuarioRepositorie repo;
	
	@GetMapping({"/register"})
	public String register(Model model) {
		RegisterDTO registerDTO = new RegisterDTO();
		model.addAttribute(registerDTO);
		model.addAttribute("success", false);
		return "register";
	}
	
	@PostMapping("/register")
	public String register(Model model, @Valid @ModelAttribute RegisterDTO registerDTO
			, BindingResult result) {
		if (!registerDTO.getPassword().equals(registerDTO.getConfirmPassword())) {
			result.addError(new FieldError(
					"registerDTO","confirmPassword", 
					"la contraseña y su confirmacion no coincide"));
		}
		
		UsuarioModel appUser = repo.findByEmail(registerDTO.getEmail());
		if (appUser != null) {
			result.addError(new FieldError(
					"registerDTO","email", 
					"el correo ya esta en uso"));
		}
		
		if (result.hasErrors()) {
			return "register";
		}
		
		try {
			//crear cuenta 
			var bCryptEncoder = new BCryptPasswordEncoder();
			
			UsuarioModel nuevoUser = new UsuarioModel();
			nuevoUser.setUserName(registerDTO.getUserName());
			nuevoUser.setEmail(registerDTO.getEmail());
			nuevoUser.setPassword(bCryptEncoder.encode(registerDTO.getPassword()));
			
			repo.save(nuevoUser);
			
			model.addAttribute("registerDTO", new RegisterDTO());
			model.addAttribute("success", true);
			
		} catch (Exception e) {
			// TODO: handle exception
			result.addError(new FieldError("registerDTO", "userName", e.getMessage()));
		}
		return "register";
	}
	
	
}

