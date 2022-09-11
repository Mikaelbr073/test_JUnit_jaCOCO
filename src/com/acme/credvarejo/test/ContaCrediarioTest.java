package com.acme.credvarejo.test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Date;

import org.junit.Before;
import org.junit.Test;

import com.acme.credvarejo.cliente.Cliente;
import com.acme.credvarejo.cliente.Cpf;
import com.acme.credvarejo.conta.ContaCrediario;
import com.acme.credvarejo.conta.IdentificadorContaCrediario;

public class ContaCrediarioTest {

	ContaCrediario contaCrediario;
	IdentificadorContaCrediario identificadorConta;
	Cliente cliente;
	Cpf cpf;
	Date data;
	private final PrintStream standardOut = System.out;
	private final ByteArrayOutputStream outputStreamCaptor =  new ByteArrayOutputStream();
	
	@Before
	public void init() {
		data = new Date(0);
		cpf =  new Cpf(7111111);
		cliente = new Cliente(cpf,"mikael carvalho", 21, data, 1500, 0);
		identificadorConta = new IdentificadorContaCrediario((long) 6789);
		contaCrediario = new ContaCrediario(identificadorConta, cliente, 500, 1000, 15, true);
		System.setOut(new PrintStream(outputStreamCaptor));
		
	}
	
	@Test
	public void testValidar() {
		contaCrediario.validar();
		assertEquals("Conta Validada", outputStreamCaptor.toString().trim());
	}
	
	@Test
	public void testeFetuarPagamento() {
		contaCrediario.efetuarPagamento(99.9);
		assertEquals(400.1, contaCrediario.getSaldoDevido(), 0);
	}

	@Test
	public void testEfetuarCompra() {
		contaCrediario.efetuarCompra(200);
		assertEquals(700.0, contaCrediario.getSaldoDevido(), 0);
		
	}
}
