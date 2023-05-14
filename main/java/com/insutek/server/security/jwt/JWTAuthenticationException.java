package com.insutek.server.security.jwt;

public class JWTAuthenticationException extends Exception{
    public JWTAuthenticationException(String error){
        super(error);
    }

}
