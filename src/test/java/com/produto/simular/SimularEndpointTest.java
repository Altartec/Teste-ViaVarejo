package com.produto.simular;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.produto.simular.controller.SimularController;

/**
 * @author Edivaldo Nogueira Classe para testar o endpoint simular
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SimularEndpointTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	@InjectMocks
	private SimularController simularController;
	
	private MockMvc mockMvc;
		

	@Test
	public void simularTest()  {

		try {
			mockMvc.perform(get("/simular?codigo=123"))
			.andExpect(status().is4xxClientError())
			   .andExpect(content().contentType(MediaType.APPLICATION_JSON))
			   .andExpect(jsonPath("$.produto.codigo").value("123")) 
			   .andExpect(jsonPath("$.produto.nome").value("produto teste"))
			   .andExpect(jsonPath("$.produto.valor").value("99.99"))
			   .andReturn()
			;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
