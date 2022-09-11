package com.acme.credvarejo.test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.Console;
import java.io.PrintStream;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.acme.credvarejo.ado.cliente.RepositorioCliente;
import com.acme.credvarejo.cliente.Cliente;
import com.acme.credvarejo.cliente.ControladorCliente;
import com.acme.credvarejo.cliente.Cpf;

public class ControladorClienteTest {
	
	ControladorCliente controladorCliente;
	RepositorioCliente repositorioCliente;
	Cliente cliente;
	
	private final PrintStream standardOut = System.out;
	private final ByteArrayOutputStream outputStreamCaptor =  new ByteArrayOutputStream();
	
	@Before
	public void init() {
		System.setOut(new PrintStream(outputStreamCaptor));
		
		repositorioCliente =  new RepositorioCliente(0);
		
		controladorCliente = new ControladorCliente(repositorioCliente);
		
	}


	@Test
	public void testIsNotIncluir() {
		controladorCliente.incluir(null);
		assertEquals("Invalido!", outputStreamCaptor.toString().trim());
		
	}
	
	@Test
	public void testIncluir() {
		Date data = new Date();
		Cpf cpf = new Cpf(7111111);
		controladorCliente.incluir(new Cliente(cpf,"mikael carvalho", 21, data, 1500, 0));
		assertEquals(1, repositorioCliente.posicao);
	}

	
	@Test
	public void testIsNotAlterar() {
		controladorCliente.alterar(7111111, "mikael carvalho");
		assertEquals("Vazio!Invalido!", outputStreamCaptor.toString().trim());
		
	}
	
	@Test
	public void testAlterar() {
		Date data = new Date();
		Cpf cpf = new Cpf(7111111);
		cliente = new Cliente(cpf,"mikael carvalho", 21, data, 1500, 0);
		controladorCliente.incluir(cliente);
		controladorCliente.alterar(7111111, "luis");
		assertEquals("luis", cliente.getNome());
		
	}
	
	@Test
	public void testExcluir() {
		Date data = new Date();
		Cpf cpf = new Cpf(7111111);
		cliente = new Cliente(cpf,"mikael carvalho", 21, data, 1500, 0);
		controladorCliente.incluir(cliente);
		controladorCliente.excluir(7111111);
		assertEquals(0, repositorioCliente.posicao);
	}
	
	@Test
	public void testIsNotExcluir() {
		controladorCliente.excluir(7111111);
		assertEquals("Vazio!", outputStreamCaptor.toString().trim());
	}
	
	@Test
	public void testBuscarPorChave() {
		Date data = new Date();
		Cpf cpf = new Cpf(7111111);
		cliente = new Cliente(cpf,"mikael carvalho", 21, data, 1500, 0);
		controladorCliente.incluir(cliente);
		outputStreamCaptor.reset();
		repositorioCliente.buscarPorChave(7111111);
		assertEquals("Cliente:mikael carvalho", outputStreamCaptor.toString().trim());
	}
	
	@Test
	public void testIsNotBuscarPorChave() {
		repositorioCliente.buscarPorChave(7111111);
		assertEquals("Invalido", outputStreamCaptor.toString().trim());
		
	}
	
	@Test
	public void testBuscarTodos() {
		Date data = new Date();
		Cpf cpf = new Cpf(7111111);
		cliente = new Cliente(cpf,"mikael carvalho", 21, data, 1500, 0);
		controladorCliente.incluir(cliente);
		outputStreamCaptor.reset();
		repositorioCliente.buscarTodos();
		assertEquals("Cliente:mikael carvalho", outputStreamCaptor.toString().trim());
	}
	
	@Test
	public void testIsNotBuscarTotos() {
		repositorioCliente.buscarTodos();
		assertEquals("Vazio!", outputStreamCaptor.toString().trim());
	}
	
}
