package com.py.jwt.util;


public class TokenExpiredException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = -1815230109062795422L;

	public TokenExpiredException(String message) {
        super(message);
    }
}