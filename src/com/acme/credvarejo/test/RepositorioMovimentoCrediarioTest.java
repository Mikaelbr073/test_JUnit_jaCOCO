package com.acme.credvarejo.test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.acme.credvarejo.ado.conta.RepositorioContaCrediario;
import com.acme.credvarejo.ado.conta.RepositorioMovimentoCrediario;
import com.acme.credvarejo.cliente.Cliente;
import com.acme.credvarejo.cliente.Cpf;
import com.acme.credvarejo.conta.ContaCrediario;
import com.acme.credvarejo.conta.IdentificadorContaCrediario;
import com.acme.credvarejo.conta.MovimentoCrediarioCredito;
import com.acme.credvarejo.conta.MovimentoCrediarioDebito;

public class RepositorioMovimentoCrediarioTest {
	
	RepositorioContaCrediario repositorio;
	IdentificadorContaCrediario identificador;
	ContaCrediario contaCrediario;
	RepositorioMovimentoCrediario repositorioMovimento;
	Cliente cliente;
	MovimentoCrediarioCredito movimentocredito;
	MovimentoCrediarioDebito movimentoDebito;
	private final PrintStream standardOut = System.out;
	private final ByteArrayOutputStream outputStreamCaptor =  new ByteArrayOutputStream();
	
	
	
	@Before
	public void init() {
		identificador =  new IdentificadorContaCrediario((long) 711234594);
		Date data = new Date();
		Cpf cpf = new Cpf(711234594);
		cliente = new Cliente(cpf,"mikael carvalho", 21, data, 1500, 0);
		contaCrediario = new ContaCrediario(identificador, cliente, 500, 1000, 15, true);
		repositorio = new RepositorioContaCrediario();
		System.setOut(new PrintStream(outputStreamCaptor));
		repositorioMovimento = new RepositorioMovimentoCrediario();
		movimentocredito = new MovimentoCrediarioCredito(contaCrediario, 150, data, 200);
		 movimentoDebito = new MovimentoCrediarioDebito(contaCrediario, 150, data, 200);
		
		
		}

	@Test
	public void testAlterar() {
		repositorio.incluir(contaCrediario);
		repositorio.alterar(identificador, 1500);
		assertEquals(1500, contaCrediario.getLimiteDeCredito(), 0);
	}
	
	@Test
	public void testExcluir() {
		repositorio.incluir(contaCrediario);
		repositorio.excluir(identificador);
		assertEquals(null ,repositorio.buscarPorChave(identificador));
	}
	
	@Test
	public void testBuscarTodosCredito() {
		repositorioMovimento.incluirCredito(movimentocredito);
		repositorioMovimento.buscarTodos();
		assertEquals(154, outputStreamCaptor.toString().trim().length());
		
	}
	
	@Test
	public void testBuscarTodosDebito() {
		repositorioMovimento.incluirDebito(movimentoDebito);
		repositorioMovimento.buscarTodos();
		assertEquals(155, outputStreamCaptor.toString().trim().length());
		
	}


	
	

}
