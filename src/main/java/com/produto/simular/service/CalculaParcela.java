package com.produto.simular.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.produto.simular.model.Parcela;
import com.produto.simular.model.Selic;

@Service
public class CalculaParcela {

	public List<Parcela> parcelas(int codigo, String nome, double valor, double valorEntrada, int qtdeParcelas) throws Exception {
		ArrayList<Parcela> parcelas = new ArrayList<Parcela>();
		
		double valorParcela;
		float juros;
		String taxaJuros;
		float valorJuros;

		if (qtdeParcelas <= 6) {
			juros = (float) 1.1500; // juros fixo
			taxaJuros = "1150";
		} else {
			juros = consultaSelic(); // consulta o web service do governo
			taxaJuros = String.valueOf(juros).toString().replaceAll("\\.","").substring(0,4);
		}

		valorParcela = (valor - valorEntrada); 
		valorJuros = (float) ((valorParcela * juros) / 100);
		valorParcela = (valorParcela + valorJuros) / qtdeParcelas; 
		
		DecimalFormat dfVp = new DecimalFormat("########.00");
		
		String valorParcelaFormatada = dfVp.format( valorParcela).replaceAll("\\,",".");
		
		for (int i = 0; i < qtdeParcelas; i++) {
			Parcela parcela = new Parcela();
			parcelas.add(parcela);
			parcelas.get(i).setNumeroParcela(i + 1);
			parcelas.get(i).setValor(valorParcelaFormatada);			
			parcelas.get(i).setTaxaJurosAoMes(taxaJuros);
		}

		return parcelas;
	}

	public float consultaSelic() {
		RestTemplate restTemplate = new RestTemplate();

		float juroSelic = 0; 

		UriComponents uri = UriComponentsBuilder.newInstance()
				.scheme("https")
				.host("api.bcb.gov.br")
				.path("dados/serie/bcdata.sgs.11/dados/ultimos/30")
				.queryParam("formato", "json")
				.build();

		try {
			ResponseEntity<Selic[]> entity = restTemplate.getForEntity(uri.toUriString(), Selic[].class);
			
			for (Selic selic : entity.getBody()) {
				juroSelic += Float.valueOf(selic.getValor()); // soma o juro nos 30 ultimos dias.
			}

		} catch (Exception e) {
			System.out.print(e);
		}

		return juroSelic;

	}

}
