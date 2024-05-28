package com.tokenvalidator.app.controller;

import com.tokenvalidator.app.model.Token;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



import javax.crypto.SecretKey;

//Controlador REST que lida com requisições HTTP.

@RestController
public class TokenController {


	
	// Método que valida o token JWT a ser validado.
	// true se o token for válido, false caso contrário.

	@PostMapping(value = "/validate")
	public boolean validate(@RequestBody Token token) {
		// Verifica se o token é nulo ou vazio
		if (token == null || token.getToken() == null || token.getToken().isEmpty()) {
			return false;
		}
		// Chama o método validateToken para verificar a validade do token
		return validateToken(token.getToken());
	}

	// Método auxiliar que realiza a validação do token JWT.
	
	// true se o token for válido, false caso contrário.

	private boolean validateToken(String jwt) {
		try {
			
			// Cria uma chave secreta para verificar o token JWT
			SecretKey key = Jwts.SIG.HS256.key().build();
             System.out.println("luana chave " + key);
			// Analisa e valida o token JWT, obtendo as claims contidas nele
			Jws<Claims> claimsJws = Jwts.parser().verifyWith(key).build().parseSignedClaims(jwt);
			Claims claims = claimsJws.getBody();

			// Verifica se o token contém 3 claims
			if (claims.size() != 3) {
				return false;
			}
			// Obtém as claims "Name", "Role" e "Seed" do token
			String name = claims.get("Name", String.class);
			String role = claims.get("Role", String.class);
			Integer seed = claims.get("Seed", Integer.class);

			// Valida a claim "Name" (não pode ser nula, maior que 256 caracteres ou conter números
			if (name == null || name.length() > 256 || name.matches(".*\\d.*")) {
				return false;
			}
			// Valida a claim "Role"- "Admin", "Member" ou "External")
			if (!("Admin".equals(role) || "Member".equals(role) || "External".equals(role))) {
				return false;
			}
			// Valida a claim "Seed" número primo
			if (!isPrime(seed)) {
				return false;
			}

			return true;
		} catch (JwtException e) {
			return false;
		}
	}

	// Método auxiliar que verifica se um número é primo.
	// true se o número for primo, false caso contrário.

	private boolean isPrime(int number) {
		// Números menores ou iguais a 1 não são primos
		if (number <= 1) {
			return false;
		}

		// Verifica se o número é divisível por algum número entre 2 e a raiz quadrada
		// dele
		for (int i = 2; i <= Math.sqrt(number); i++) {

			if (number % i == 0) {
				return false;
			}
		}
		return true;
	}
}