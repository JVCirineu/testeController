package com.testehospede.entitie;


import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HospedeTest {
	
	private Hospede hospede;
	
	@BeforeEach
	void setUp() {
		//Arrange
		hospede = new Hospede(1L,"Julia","julia@gmail.com","(15)99888-5566");
	}
	
	@Test
	@DisplayName("Testando getter e setter do ID")
	void testId() {
		hospede.setId(2L);
		//Assert
		assertEquals(2L,hospede.getId());
	}
	
	@Test
	@DisplayName("Testando getter e setter do NOME")
	void testNome() {
		//Act
		hospede.setNome("Joao Paulo");
		//Assert
		assertEquals("Joao Paulo", hospede.getNome());
	}
	@Test
	@DisplayName("Testando getter e setter do EMAIL")
	void testEmail() {
		//Act
		hospede.setEmail("joao@gmail.com");
		//Assert
		assertEquals("joao@gmail.com", hospede.getEmail());
	}
	@Test
	@DisplayName("Testando getter e setter do TELEFONE")
	void testTelefone() {
		//Act
		hospede.setTelefone("(15)99446-0969");
		//Assert
		assertEquals("(15)99446-0969", hospede.getTelefone());
	}
	
	@Test
	@DisplayName("Testando todos os argumentos")
	void testConstrutor() {
		//Act
		Hospede novoHospede = new Hospede(3L,"Mateus","mateus@gmail.com", "(15)1111-1111");
		//Assert
		assertAll("novoHospede",
				()-> assertEquals(3L,novoHospede.getId()),
				()-> assertEquals("Mateus",novoHospede.getNome()),
				()-> assertEquals("mateus@gmail.com",novoHospede.getEmail()),
				()-> assertEquals("(15)1111-1111",novoHospede.getTelefone()));
	}
}
