package com.produto.simular.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.produto.simular.model.Compra;
import com.produto.simular.model.Parcela;
import com.produto.simular.service.CalculaParcela;

@RestController
@RequestMapping("simular")
public class SimularController {

	@Autowired
	private CalculaParcela calculaParcelas;

	@GetMapping(consumes = "application/json", produces = "application/json")
	@RequestMapping(method = RequestMethod.GET)

	public Iterable<Parcela> listaParcelas(@RequestBody Compra compra) throws Exception  {
		
		int codigo = compra.getProduto().getCodigo();
		String nome = compra.getProduto().getNome();
		double valor = compra.getProduto().getValor();
		double valorEntrada = compra.getCondicaoPagamento().getValorEntrada();
		int qtdeParcelas = compra.getCondicaoPagamento().getQtdeParcelas();
		
		List<Parcela> listaParcelas = calculaParcelas.parcelas(codigo, nome, valor, valorEntrada, qtdeParcelas);

		return listaParcelas;
	}
}
