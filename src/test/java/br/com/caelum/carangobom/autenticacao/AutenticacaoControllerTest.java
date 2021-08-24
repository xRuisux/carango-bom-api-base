package br.com.caelum.carangobom.autenticacao;

import javax.transaction.Transactional;

import com.google.gson.Gson;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/*@SpringBootTest
@TestPropertySource(properties = {"DB_NAME=carangobom","spring.jpa.hibernate.ddlAuto:update"})
@AutoConfigureMockMvc
@AutoConfigureTestEntityManager
@Transactional*/
public class AutenticacaoControllerTest {
    /*@Autowired
    private MockMvc mockMvc;

    @Test
    void deverRetornar200quandoUsuarioComSenhaEemailEstaoCorretos() throws Exception {

        String url = "/autenticacao";

        AutenticacaoForm form = new AutenticacaoForm("admin@email.com", "123456");
        Gson gson = new Gson();
        String json = gson.toJson(form);
    
        this.mockMvc.perform(
            post(url)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isOk());
    }

    @Test
    void deverRetornar400quandoUsuarioInformaSenhaInvalida() throws Exception {

        String url = "/autenticacao";

        AutenticacaoForm form = new AutenticacaoForm("admin@email.com", "123457");
        Gson gson = new Gson();
        String json = gson.toJson(form);
    
        this.mockMvc.perform(
            post(url)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isBadRequest());
    }
    @Test
    void deverRetornar400quandoUsuarioNaoInformarSenha() throws Exception {

        String url = "/autenticacao";

        AutenticacaoForm form = new AutenticacaoForm("admin@email.com", "12345");
        Gson gson = new Gson();
        String json = gson.toJson(form);
    
        this.mockMvc.perform(
            post(url)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isBadRequest());
    }
    @Test
    void deverRetornar400quandoInformarFomatodeEmailInvalido() throws Exception {

        String url = "/autenticacao";

        AutenticacaoForm form = new AutenticacaoForm("admin", "12345");
        Gson gson = new Gson();
        String json = gson.toJson(form);
    
        this.mockMvc.perform(
            post(url)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isBadRequest());
    }
    @Test
    void deverRetornar400quandoInformarEmailInvalido() throws Exception {

        String url = "/autenticacao";

        AutenticacaoForm form = new AutenticacaoForm("admin@gmail.com", "");
        Gson gson = new Gson();
        String json = gson.toJson(form);
    
        this.mockMvc.perform(
            post(url)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isBadRequest());
    }
    @Test
    void deverRetornar400quandoUsuarioInformaEmailInvalida() throws Exception {

        String url = "/autenticacao";

        AutenticacaoForm form = new AutenticacaoForm("admin@email.co", "12345");
        Gson gson = new Gson();
        String json = gson.toJson(form);
    
        this.mockMvc.perform(
            post(url)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isBadRequest());
    }
    @Test
    void deverRetornar403quandoTentarUsarEndpointSemTokenDeAcesso() throws Exception {
        String url = "/marcas";
        this.mockMvc.perform(get(url)).andExpect(status().isForbidden());
    }*/
}
