package br.com.caelum.carangobom.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import br.com.caelum.carangobom.auth.AuthService;
import br.com.caelum.carangobom.auth.AuthViaToken;
import br.com.caelum.carangobom.user.UserService;

@EnableWebSecurity
@Configuration
public class Config extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthService authService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService usersService;

    // Configuracoes de auth
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().configurationSource(request -> {
            var cors = new CorsConfiguration();
            cors.setAllowedOrigins(List.of("http://localhost:3000"));
            cors.setAllowedMethods(List.of("GET","POST", "PUT", "DELETE","PATCH", "OPTIONS"));
            cors.setAllowedHeaders(List.of("*"));
            return cors;
          }).and()
        .authorizeRequests().antMatchers(HttpMethod.GET, "/veiculos").permitAll()
                .antMatchers(HttpMethod.GET, "/veiculos/*").permitAll().antMatchers(HttpMethod.POST, "/auth")
                .permitAll().anyRequest().authenticated().and().csrf().disable().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .addFilterBefore(new AuthViaToken(tokenService, usersService),
                        UsernamePasswordAuthenticationFilter.class);
    }

}
