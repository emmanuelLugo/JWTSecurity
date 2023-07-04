package com.py.jwt.service;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.py.jwt.model.Usuario;
import com.py.jwt.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findOneByLogin(username)
				.orElseThrow(() -> new UsernameNotFoundException("El usuario no existe"));

		if (usuario == null) {
			throw new UsernameNotFoundException(username);
		}
		  List<GrantedAuthority> authorities = new ArrayList<>();
		return User.withUsername(username)
				.password(usuario.getSenha())
				.authorities(authorities)
				.build();
	}

}
