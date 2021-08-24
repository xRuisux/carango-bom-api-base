package br.com.caelum.carangobom.auth;

import javax.transaction.Transactional;

import com.google.gson.Gson;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@AutoConfigureTestEntityManager
@Transactional
public class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturn200WhenUserSendCorrectEmailAndPassword() throws Exception {

        String url = "/auth";

        AuthForm form = new AuthForm("admin@email.com", "123456");
        Gson gson = new Gson();
        String json = gson.toJson(form);
    
        this.mockMvc.perform(
            post(url)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isOk());
    }

    @Test
    void shouldReturn400WhenPassowrdIsIncorrect() throws Exception {

        String url = "/auth";

        AuthForm form = new AuthForm("admin@email.com", "123457");
        Gson gson = new Gson();
        String json = gson.toJson(form);
    
        this.mockMvc.perform(
            post(url)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isBadRequest());
    }
    @Test
    void shouldReturn400WhenPasswordFieldIsLowerThan6Characteres() throws Exception {

        String url = "/auth";

        AuthForm form = new AuthForm("admin@email.com", "12345");
        Gson gson = new Gson();
        String json = gson.toJson(form);
    
        this.mockMvc.perform(
            post(url)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isBadRequest());
    }
    @Test
    void shouldReturn400WhenEmailFormatIsInvalid() throws Exception {

        String url = "/auth";

        AuthForm form = new AuthForm("admin", "12345");
        Gson gson = new Gson();
        String json = gson.toJson(form);
    
        this.mockMvc.perform(
            post(url)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isBadRequest());
    }
    @Test
    void shouldReturn400WhenPasswordFieldIsBlank() throws Exception {

        String url = "/auth";

        AuthForm form = new AuthForm("admin@gmail.com", "");
        Gson gson = new Gson();
        String json = gson.toJson(form);
    
        this.mockMvc.perform(
            post(url)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isBadRequest());
    }
    @Test
    void shouldReturn400WhenEmailNotExists() throws Exception {

        String url = "/auth";

        AuthForm form = new AuthForm("admin@email.co", "12345");
        Gson gson = new Gson();
        String json = gson.toJson(form);
    
        this.mockMvc.perform(
            post(url)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isBadRequest());
    }
    @Test
    void shouldReturn403WhenTentarUsarEndpointSemTokenDeAcesso() throws Exception {
        String url = "/marcas";
        this.mockMvc.perform(get(url)).andExpect(status().isForbidden());
    }
}
