package br.com.caelum.carangobom.seguranca;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

// @RunWith(SpringRunner.class)
@WebMvcTest(Configuracao.class)
public class SecuredControllerRestTemplateIntegrationTest {

  /*
   * @Autowired private MockMvc mvc;
   * 
   * // ... other methods
   * 
   * @WithMockUser(value = "spring")
   * 
   * @Test public void givenAuthRequestOnPrivateService_shouldSucceedWith200()
   * throws Exception {
   * mvc.perform(get("/private/hello").contentType(MediaType.APPLICATION_JSON))
   * .andExpect(status().isOk()); }
   */
}