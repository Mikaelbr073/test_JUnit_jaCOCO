package com.acme.credvarejo.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.acme.credvarejo.cliente.Cpf;

public class CpfTest {
	
	Cpf cpf;
	
	@Before
	public void init() {
		
		cpf =  new Cpf(711234594);
	}
	

	@Test
	public void testIsCPF() {
		assertEquals("37", cpf.calculaDigitoMod11("363326290", 2, 12, true));
	}

	
	@Test
	public void isCpf() {
	assertTrue(cpf.isCPF("71123459444"));
	}
	
	@Test
	public void IsNotCpf() {
		assertFalse(cpf.isCPF("000000000000"));
	}
	
	
}
