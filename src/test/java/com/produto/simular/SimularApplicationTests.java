package com.produto.simular;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.produto.simular.model.Parcela;
import com.produto.simular.service.Calcula;

@RunWith(SpringRunner.class)
@SpringBootTest
class SimularApplicationTests {

	@Autowired
	private Calcula calculaParcelas;
	
	@Test
	public void testaCalculoParcela() throws Exception // Testa se o calculo da parcela esta sendo efetuado.
	{
		
		int codigo = 123;
		String nome = "Nome do Produto";
		double valor = 9999.99;
		double valorEntrada = 9999.99;
		int qtdeParcelas = 999;

		List<Parcela> listaParcelas = calculaParcelas.parcelas(codigo, nome, valor, valorEntrada, qtdeParcelas);
		assertThat(listaParcelas.size()).isNotEqualTo(0);
		assertThat(listaParcelas.size()).isEqualTo(999);
		
		
	}
	
	
	

}
