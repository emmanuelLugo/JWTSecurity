package com.py.jwt.model.dto;

import com.py.jwt.model.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {

	private final String jwttoken;
	private final Usuario usuario;

}
