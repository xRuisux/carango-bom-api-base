package br.com.caelum.carangobom.seguranca;

import javax.transaction.Transactional;

import com.google.gson.Gson;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
}
