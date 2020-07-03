package com.produto.simular.model;

public class Parcela {

	private int numeroParcela; // Número da parcela.

	private String valor; // Valor da parcela com juros calculado.

	private String taxaJurosAoMes; // Taxa de juros ao mês com retorno sem ponto decimal.

	public int getNumeroParcela() {
		return numeroParcela;
	}

	public void setNumeroParcela(int numeroParcela) {
		this.numeroParcela = numeroParcela;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getTaxaJurosAoMes() {
		return taxaJurosAoMes;
	}

	public void setTaxaJurosAoMes(String taxaJuros) {
		this.taxaJurosAoMes = taxaJuros;
	}

}
