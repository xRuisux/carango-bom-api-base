package br.com.caelum.carangobom.auth;

import lombok.Getter;

@Getter
public class AuthMapper {
    private String token;
    private String type;

    public AuthMapper(String token, String type) {
        this.token = token;
        this.type = type;
    }
}
