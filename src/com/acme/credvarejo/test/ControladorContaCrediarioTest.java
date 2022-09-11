package com.acme.credvarejo.test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.acme.credvarejo.ado.conta.InterfaceRepositorioContaCrediario;
import com.acme.credvarejo.ado.conta.RepositorioContaCrediario;
import com.acme.credvarejo.ado.conta.RepositorioMovimentoCrediario;
import com.acme.credvarejo.cliente.Cliente;
import com.acme.credvarejo.cliente.Cpf;
import com.acme.credvarejo.conta.ContaCrediario;
import com.acme.credvarejo.conta.ControladorContaCrediario;
import com.acme.credvarejo.conta.ControladorMovimentoCrediario;
import com.acme.credvarejo.conta.IdentificadorContaCrediario;

public class ControladorContaCrediarioTest {
	
	ControladorContaCrediario controlador;
	RepositorioContaCrediario  repositorio;
	Cliente cliente;
	IdentificadorContaCrediario identificador;
	ContaCrediario conta;
	ControladorMovimentoCrediario movimento;
	RepositorioMovimentoCrediario repositorioMovimento;
	private final PrintStream standardOut = System.out;
	private final ByteArrayOutputStream outputStreamCaptor =  new ByteArrayOutputStream();
	

	
	@Before
	public void init() {
		repositorio = new RepositorioContaCrediario();
		controlador = new ControladorContaCrediario(repositorio);
		Date data = new Date();
		Cpf cpf = new Cpf(711234594);
		cliente = new Cliente(cpf,"mikael carvalho", 21, data, 1500, 0);
		 identificador =  new IdentificadorContaCrediario((long) 711234594);
		 System.setOut(new PrintStream(outputStreamCaptor));
		 repositorioMovimento = new RepositorioMovimentoCrediario();
		 movimento = new ControladorMovimentoCrediario(repositorioMovimento);
		 conta = new ContaCrediario(identificador, cliente, 500, 1000, 15, true);
	}
	
	
	
	
	@Test
	public void testInserir() {
		controlador.inserir(cliente, 900, 31);
		assertEquals(31 ,repositorio.getContas()[0].getVencimento());
		
	}
	
	@Test
	public void testBucarTodos() throws IOException {
		controlador.inserir(cliente, 100, 15);
		controlador.buscarTodos();
		outputStreamCaptor.close();
		assertEquals(100, outputStreamCaptor.toString().length());
		}
	
	@Test
	public void testIsNotBuscar() {
		controlador.buscar(null);
		assertEquals("Invalido!", outputStreamCaptor.toString().trim());
	}
	
	@Test
	public void testIsNotcreditar() {
		controlador.creditar(null, 150, movimento);
		assertEquals("Invalido!", outputStreamCaptor.toString().trim());
		
	}
	@Test
	public void testCreditar() {
		repositorio.incluir(conta);
		controlador.creditar(conta.getIdentificadorConta(), 150, movimento);
		assertEquals("Movimento Validado", outputStreamCaptor.toString().trim());
	
	}
	
	
	@Test
	public void testIsNotDebitar() {
		controlador.debitar(null, 10, movimento);
		assertEquals("Invalido!", outputStreamCaptor.toString().trim());
	}
	
	@Test
	public void testDebitar() {
		repositorio.incluir(conta);
		controlador.debitar(identificador, 10, movimento);
		assertEquals("Movimento Validado", outputStreamCaptor.toString().trim());
	}
	




}
