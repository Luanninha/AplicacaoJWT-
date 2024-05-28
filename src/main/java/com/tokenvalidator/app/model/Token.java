package com.tokenvalidator.app.model;

public class Token {

	private String token;

	// Construtor que inicializa o token

	public Token(String token) {
		this.token = token;
	}

	// Construtor padrão nao aceita argumentos
	public Token() {

	}

	// Getter para o token retrna o valor atual do token
	public String getToken() {
		return token;
	}

	// Setter para o token o novo valor a ser definido para o token
	public void setToken(String token) {
		this.token = token;
	}
}
