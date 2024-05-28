package com.tokenvalidator.app;

import com.tokenvalidator.app.controller.TokenController;
import com.tokenvalidator.app.model.Token;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TokenTests {
	
	
	//testa um token valido (token-admin, name-ToninhoAraujo, seen-numeroprimo)

   @Test
    public void testValidTokenCase1() {
    	TokenController controller = new TokenController();
    	String jwt = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.P8CgD-q9mxEUaQDR_fNhxsz5ty50VJQzTLObf8U4nGM";
        Token token = new Token(jwt);
        assertTrue(controller.validate(token));
    }
   
    //testa um token invalido (token-corrompido)
    
   @Test
    public void testInvalidTokenCase2() {
        String jwt = "eyJhbGciOiJzI1NiJ9.dfsdfsfryJSr2xrIjoiQWRtaW4iLCJTZrkIjoiNzg0MSIsIk5hbrUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05fsdfsIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg";
        Token token = new Token(jwt);
        TokenController controller = new TokenController();
        assertFalse(controller.validate(token));
    }
    
    // Testa um token com nome inválido (caracteres numéricos)

   @Test
    public void testInvalidNameWithNumericCharactersCase3() {
        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiRXh0ZXJuYWwiLCJTZWVkIjoiODgwMzciLCJOYW1lIjoiTTRyaWEgT2xpdmlhIn0.6YD73XWZYQSSMDf6H0i3-kylz1-TY_Yt6h1cV2Ku-Qs";
        Token token = new Token(jwt);
        TokenController controller = new TokenController();
        assertFalse(controller.validate(token));
    }

    //Testa um token com mais de três claims.
    
   @Test
	public void testInvalidTokenWithMoreThanThreeClaimsCase4() {
		String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiTWVtYmVyIiwiT3JnIjoiQlIiLCJTZWVkIjoiMTQ2MjciLCJOYW1lIjoiVmFsZGlyIEFyYW5oYSJ9.cmrXV_Flm5mfdpfNUVopY_I2zeJUy4EZ4i3Fea98zvY";
        Token token = new Token(jwt);
        TokenController controller = new TokenController();
        assertFalse(controller.validate(token));
    }
}
