package com.produto.simular.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.produto.simular.model.Compra;
import com.produto.simular.model.CondicaoPagamento;
import com.produto.simular.model.Parcela;
import com.produto.simular.service.Calcula;
import com.produto.simular.service.Verifica;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "Simulação",description = "Api de teste ViaVarejo",produces = "application/json")
@RequestMapping("simular")
public class SimularController {

	@Autowired
	private Verifica verifica;

	@Autowired
	private Calcula calcula;

	@ApiOperation(value = "Listar parcelas da simulação")
	@GetMapping(consumes = "application/json", produces = "application/json")
	
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Retorna a lista de parcelas",response = Parcela.class),
		    @ApiResponse(code = 401, message = "Você não possui credenciais de autenticação válidas"),
		    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
		    @ApiResponse(code = 404, message = "O servidor não pôde encontrar a sua solicitação"),
		    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
		})
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> listaParcelas(@RequestBody Compra compra) throws Exception {
		
		List<Parcela> listaParcelas = new ArrayList<Parcela>();
		
		if (verifica.dadosEntrada(compra)) {
			int codigo = compra.getProduto().getCodigo();
			String nome = compra.getProduto().getNome();
			double valor = compra.getProduto().getValor();
			double valorEntrada = compra.getCondicaoPagamento().getValorEntrada();
			int qtdeParcelas = compra.getCondicaoPagamento().getQtdeParcelas();

			listaParcelas = calcula.parcelas(codigo, nome, valor, valorEntrada, qtdeParcelas);
			
			return new ResponseEntity<>(listaParcelas, HttpStatus.OK);	
		}
		
		return new ResponseEntity<>("Houve um erro em sua requisição, verifique!", HttpStatus.EXPECTATION_FAILED);

	}

}