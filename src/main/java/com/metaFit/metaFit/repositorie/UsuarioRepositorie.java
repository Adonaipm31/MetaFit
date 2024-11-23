package com.metaFit.metaFit.repositorie;

import org.springframework.data.jpa.repository.JpaRepository;

import com.metaFit.metaFit.model.UsuarioModel;

public interface UsuarioRepositorie extends JpaRepository<UsuarioModel, Long>{
	public UsuarioModel findByEmail(String email);
}
