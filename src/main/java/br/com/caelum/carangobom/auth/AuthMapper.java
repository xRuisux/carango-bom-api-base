package br.com.caelum.carangobom.auth;

// import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
// @AllArgsConstructor
public class AuthMapper {
    private String token;
    private String type;

    public AuthMapper(String token, String type) {
        this.token = token;
        this.type = type;
    }
}
