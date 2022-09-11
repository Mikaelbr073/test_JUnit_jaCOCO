package com.acme.credvarejo.test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.acme.credvarejo.cliente.Cliente;
import com.acme.credvarejo.cliente.Cpf;

public class ClienteTest {
	
	
	Cliente cliente;
	Cpf cpf;
	Date data;
	private final PrintStream standardOut = System.out;
	private final ByteArrayOutputStream outputStreamCaptor =  new ByteArrayOutputStream();
	
	@Before
	public void init() {
		data = new Date();
		cpf =  new Cpf(7111111);
		cliente = new Cliente(cpf,"mikael carvalho", 21, data, 1500, 0);
		System.setOut(new PrintStream(outputStreamCaptor));
	}

	@Test
	public void testGetPrimeiroNome() {
		assertEquals("mikael",cliente.getPrimeiroNome());
		
	}
	
	@Test
	public void testGetSegundoNome() {
		assertEquals("carvalho", cliente.getSegundoNome());
	}
	
	@Test 
	public void testValidar() {
		cliente.validar();
		assertEquals("Cliente Validado", outputStreamCaptor.toString().trim());
	}
	
	@Test
	public void IsNotValidar() {
		cliente.setSexo(3);
		cliente.validar();
		assertEquals("Cliente invalido", outputStreamCaptor.toString().trim());
		
		
	}
	
	@Test
	public void testGetChave() {
		assertEquals("7111111", cliente.getChave());
	}
	

	
	
	

}
