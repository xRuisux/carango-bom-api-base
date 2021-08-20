package br.com.caelum.carangobom.seguranca;

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
import org.springframework.test.web.servlet.MvcResult;

import br.com.caelum.carangobom.autenticacao.AutenticacaoDto;
import br.com.caelum.carangobom.autenticacao.AutenticacaoForm;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@TestPropertySource(properties = {"DB_NAME=carangobom","spring.jpa.hibernate.ddlAuto:update"})
@AutoConfigureMockMvc
@AutoConfigureTestEntityManager
@Transactional
public class ConfiguracaoTest {
    @Autowired
    private MockMvc mockMvc;

   
    @Test
    void deverRetornar403quandoTentarUsarEndpointSemTokenDeAcesso() throws Exception {
        String url = "/marcas";
        this.mockMvc.perform(get(url)).andExpect(status().isForbidden());
    }
    @Test
    void deverRetornar200quandoTentarUsarEndpointComTokenDeAcessoValido() throws Exception {
        String url = "/autenticacao";

        AutenticacaoForm form = new AutenticacaoForm("admin@email.com", "123456");
        Gson gson = new Gson();
        String json = gson.toJson(form);
    
        MvcResult resultado = this.mockMvc.perform(
            post(url)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isOk())
            .andReturn();
        String responseBody = resultado.getResponse().getContentAsString();
        AutenticacaoDto autenticacaoDto = new Gson().fromJson(responseBody, AutenticacaoDto.class);
        url = "/marcas";
        this.mockMvc.perform(get(url).header("Authorization", autenticacaoDto.getTipo()+  " " +  autenticacaoDto.getToken())).andExpect(status().isOk());
    }
}
