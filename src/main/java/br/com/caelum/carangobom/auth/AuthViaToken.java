package br.com.caelum.carangobom.auth;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.caelum.carangobom.security.TokenService;
import br.com.caelum.carangobom.users.UsersService;
import br.com.caelum.carangobom.users.Users;

public class AuthViaToken extends OncePerRequestFilter {
    private TokenService tokenService;
    private UsersService usersService;

    public AuthViaToken(TokenService tokenService, UsersService usersService) {
        this.tokenService = tokenService;
        this.usersService = usersService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = getToken(request);
        boolean isValid = tokenService.check(token);
        if (isValid) {
            authenticateUser(token);
        }

        filterChain.doFilter(request, response);
    }

    private void authenticateUser(String token) {
        Long idUser = tokenService.getIdUser(token);
        try {
            Users user = this.usersService.findById(idUser);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                user, null,user.getAuthorities()
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    private String getToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }

        return token.substring(7, token.length());
    }
}
