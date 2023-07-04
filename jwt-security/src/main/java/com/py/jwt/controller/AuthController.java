package com.py.jwt.controller;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.py.jwt.model.Usuario;
import com.py.jwt.model.dto.JwtRequest;
import com.py.jwt.model.dto.JwtResponse;
import com.py.jwt.repository.UsuarioRepository;
import com.py.jwt.service.JwtUtilService;
import com.py.jwt.service.UserDetailsServiceImpl;

import lombok.AllArgsConstructor;

@RestController
@CrossOrigin
@RequestMapping({ "/api/auth" })
@AllArgsConstructor
public class AuthController {

	private AuthenticationManager authenticationManager;

	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	private JwtUtilService jwtUtilService;
	
	private UsuarioRepository usuarioRepository;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody JwtRequest authenticationRequest) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario y/o Contraseña Invalidos");
		}

		final UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(authenticationRequest.getUsername());

		Usuario usuario = usuarioRepository.findOneByLoginAndSenha(authenticationRequest.getUsername(),
				userDetails.getPassword());
		
		final String token = jwtUtilService.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token, usuario));
	}

}
