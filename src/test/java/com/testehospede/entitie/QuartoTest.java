package com.testehospede.entitie;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QuartoTest {
	
	private Quarto quarto;
	
	@BeforeEach
	void setUp() {
		//Arrange
		quarto = new Quarto(1L,"238","suíte");
	}
	
	@Test
	@DisplayName("Testando getter e setter do ID")
	void testId() {
		quarto.setId(2L);
		//Assert
		assertEquals(2L,quarto.getId());
	}
	
	@Test
	@DisplayName("Testando getter e setter do NUM")
	void testNum() {
		//Act
		quarto.setNum("100");
		//Assert
		assertEquals("100", quarto.getNum());
	}
	@Test
	@DisplayName("Testando getter e setter do TIPO")
	void testTipo() {
		//Act
		quarto.setTipo("SUÍTE");
		//Assert
		assertEquals("SUÍTE", quarto.getTipo());
	}
	
	@Test
	@DisplayName("Testando todos os argumentos")
	void testConstrutor() {
		//Act
		Quarto novoQuarto = new Quarto(3L,"300","king");
		//Assert
		assertAll("novoQuarto",
				()-> assertEquals(3L,novoQuarto.getId()),
				()-> assertEquals("300",novoQuarto.getNum()),
				()-> assertEquals("king",novoQuarto.getTipo()));
				
	}
}
