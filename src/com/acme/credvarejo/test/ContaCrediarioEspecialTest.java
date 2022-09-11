package com.acme.credvarejo.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.acme.credvarejo.cliente.Cliente;
import com.acme.credvarejo.cliente.Cpf;
import com.acme.credvarejo.conta.IdentificadorContaCrediario;
import com.acme.credvarejo.contaCrediario.ContaCrediarioEspecial;

public class ContaCrediarioEspecialTest {
	
	ContaCrediarioEspecial conta;
	ContaCrediarioEspecial conta2;
	Cliente cliente;
	Cliente cliente2;
	IdentificadorContaCrediario indentificador;
	
	
	@Before
	public void init() {
			Date data = new Date();
			Cpf cpf = new Cpf(7111111);
			cliente = new Cliente(cpf,"mikael carvalho", 21, data, 1500, 0);
			cliente2 = new Cliente(cpf,"mikael carvalho", 21, data, 1500, 0);
			indentificador =  new IdentificadorContaCrediario((long) 711234594);
			conta = new ContaCrediarioEspecial(indentificador, cliente, 500, 1000, 15, true, 15, 12);
			conta2 = new ContaCrediarioEspecial(indentificador, cliente2, 500, 1000, 15, true, 15);
	}

	@Test
	public void testEfetuarPagamento() {
		conta.efetuarPagamento(100.0);
		assertEquals(400.0, conta.getSaldoDevido(), 0);
	}
	
	@Test
	public void testEfetuarPagamentoSobreCarga() {
		conta2.efetuarPagamento(200, 15);
		assertEquals(225, conta2.getSaldoDevido(), 0);
	}
	@Test
	public void testEfetuarPagamentoSobreCargaMaior() {
		conta2.efetuarPagamento(200, 10);
		assertEquals(225, conta2.getSaldoDevido(), 0);
	}
	
	@Test
	public void testGetPercentualDeDesconto() {
		assertEquals(15, conta.getPercentualDeDesconto(), 0);
	}
	
	@Test
	public void testGetPontosAcumulados() {
		assertEquals(12, conta.getPontosAcumulados(), 0);
	}
			
}
