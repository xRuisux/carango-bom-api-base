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
import br.com.caelum.carangobom.user.User;
import br.com.caelum.carangobom.user.UserService;
import javassist.NotFoundException;

public class AuthViaToken extends OncePerRequestFilter {
    private TokenService tokenService;
    private UserService usersService;

    public AuthViaToken(TokenService tokenService, UserService usersService) {
        this.tokenService = tokenService;
        this.usersService = usersService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = getTokenFromRequest(request);
        boolean isValid = tokenService.check(token);
        if (isValid) {
            try {
                authenticate(token);
            } catch (Exception e) {
                isValid = false;
            }
        }

        filterChain.doFilter(request, response);
    }

    private void authenticate(String token) throws NotFoundException{
        Long userId = tokenService.getUserId(token);

        User user =  usersService.findById(userId);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
            user, null,user.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }

        return token.substring(7, token.length());
    }
}
