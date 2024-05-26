package com.tokenvalidator.app.model;


public class Token {
    
    private String token;

    // Construtor que inicializa o token
    
    public Token(String token) {
        this.token = token;
    }

    // Construtor padrão
    public Token() {
      
    }

    // Getter para o token
    public String getToken() {
        return token;
    }

    // Setter para o token
    public void setToken(String token) {
        this.token = token;
    }
}
