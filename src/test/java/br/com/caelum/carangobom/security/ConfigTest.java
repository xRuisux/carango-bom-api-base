package br.com.caelum.carangobom.security;

import javax.transaction.Transactional;

import com.google.gson.Gson;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import br.com.caelum.carangobom.auth.AuthForm;
import br.com.caelum.carangobom.auth.AuthMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@AutoConfigureTestEntityManager
@Transactional
public class ConfigTest {
    @Autowired
    private MockMvc mockMvc;

   
    @Test
    void shouldReturn403WhenUsingEndpointWithoutAccessToken() throws Exception {
        String url = "/marcas";
        this.mockMvc.perform(get(url)).andExpect(status().isForbidden());
    }
    @Test
    void shouldReturn200WhenUsingEndpointValidAccessToken() throws Exception {
        String url = "/auth";

        AuthForm form = new AuthForm("admin@email.com", "123456");
        Gson gson = new Gson();
        String json = gson.toJson(form);
    
        MvcResult result = this.mockMvc.perform(
            post(url)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isOk())
            .andReturn();
        String responseBody = result.getResponse().getContentAsString();
        AuthMapper authMapper = new Gson().fromJson(responseBody, AuthMapper.class);
        url = "/marcas";
        this.mockMvc.perform(get(url).header("Authorization", authMapper.getType()+  " " +  authMapper.getToken())).andExpect(status().isOk());
    }
}
