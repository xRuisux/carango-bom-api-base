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
class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private String url = "/auth";
    @Test
    void authenticaticationTests() throws Exception {
        this.shouldReturn200WhenUserSendCorrectEmailAndPassword("admin@email.com", "123456");
        this.shouldReturn400WhenPassowrdIsIncorrect("admin@email.com", "123457");
        this.shouldReturn400WhenPasswordFieldIsLowerThan6Characteres("admin@email.com", "12345");
        this.shouldReturn400WhenEmailFormatIsInvalid("admin","12345");
        this.shouldReturn400WhenEmailNotExists("admin@email.co","123456");

    }
    void shouldReturn200WhenUserSendCorrectEmailAndPassword(String email, String password) throws Exception {

        AuthForm form = new AuthForm(email, password);
        Gson gson = new Gson();
        String json = gson.toJson(form);
    
        this.mockMvc.perform(
            post(url)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isOk());
    }

    
    void shouldReturn400WhenPassowrdIsIncorrect(String email, String password) throws Exception {

        AuthForm form = new AuthForm(email, password);
        Gson gson = new Gson();
        String json = gson.toJson(form);
    
        this.mockMvc.perform(
            post(url)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isBadRequest());
    }
    
    void shouldReturn400WhenPasswordFieldIsLowerThan6Characteres(String email, String password) throws Exception {

        AuthForm form = new AuthForm(email, password);
        Gson gson = new Gson();
        String json = gson.toJson(form);
    
        this.mockMvc.perform(
            post(url)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isBadRequest());
    }
    
    void shouldReturn400WhenEmailFormatIsInvalid(String email, String password) throws Exception {

        AuthForm form = new AuthForm(email, password );
        Gson gson = new Gson();
        String json = gson.toJson(form);
    
        this.mockMvc.perform(
            post(url)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isBadRequest());
    }
    
    void shouldReturn400WhenPasswordFieldIsBlank(String email, String password) throws Exception {

        AuthForm form = new AuthForm(email, password);
        Gson gson = new Gson();
        String json = gson.toJson(form);
    
        this.mockMvc.perform(
            post(url)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isBadRequest());
    }
    
    void shouldReturn400WhenEmailNotExists(String email, String password) throws Exception {

        AuthForm form = new AuthForm(email, password);
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
        this.mockMvc.perform(get("/marcas")).andExpect(status().isForbidden());
    }
}
