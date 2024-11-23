package com.metaFit.metaFit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.metaFit.metaFit.model.UsuarioModel;
import com.metaFit.metaFit.repositorie.UsuarioRepositorie;

@Service
public class UsuarioService implements UserDetailsService{

	@Autowired
	private UsuarioRepositorie repo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UsuarioModel usuario = repo.findByEmail(email);
		if (usuario != null) {
			var springUser = User.withUsername(usuario.getEmail())
					.password(usuario.getPassword())
					.roles("USER")
					.build();
			return springUser;
		}
		else {
			throw new UsernameNotFoundException("Usuario no encontrado con email: " + email);
		}
	}

}
