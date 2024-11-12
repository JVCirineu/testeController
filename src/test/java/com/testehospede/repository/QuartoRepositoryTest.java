package com.testehospede.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.testehospede.entitie.Quarto;

@DataJpaTest
class QuartoRepositoryTest {

	@Autowired
	private QuartoRepository quartoRepository;

	@DisplayName("Testando Save")
	@Test
	void testSalvarRepository() {
		Quarto quarto1 = new Quarto(null, "01", "SUITE");

		Quarto saveQuarto = quartoRepository.save(quarto1);

		assertNotNull(saveQuarto);
		assertTrue(saveQuarto.getId() > 0);
	}

	@DisplayName("Testando Get para todos Quartos")
	@Test
	void testGetAllRepository() {
		Quarto quarto1 = new Quarto(null, "01", "SUITE");
		Quarto quarto2 = new Quarto(null, "02", "KING");

		quartoRepository.save(quarto1);
		quartoRepository.save(quarto2);

		List<Quarto> quartoList = quartoRepository.findAll();

		assertNotNull(quartoList);
		assertEquals(2, quartoList.size());
	}

	@DisplayName("Testando Get By Id")
	@Test
	void testGetById() {
		Quarto quarto2 = new Quarto(null, "02", "KING");
		
		quartoRepository.save(quarto2);
		
		Quarto saveQuarto = quartoRepository.findById(quarto2.getId()).get();
		
		assertNotNull(saveQuarto);
		assertEquals(quarto2.getId(), saveQuarto.getId());
	}
	@DisplayName("Testando o Update")
	@Test
	void TestUpdateQuarto() {
		Quarto quarto1 = new Quarto(null, "01", "SUITE");
		quartoRepository.save(quarto1);
		
		Quarto saveQuarto = quartoRepository.findById(quarto1.getId()).get();
		quarto1.setNum("001");
		quarto1.setTipo("king1");
		
		Quarto updateQuarto = quartoRepository.save(saveQuarto);
		assertNotNull(updateQuarto);
		assertEquals("001",updateQuarto.getNum());
		assertEquals("king1",updateQuarto.getTipo());
	}
	@DisplayName("Testando o delete")
	@Test
	void TestDeleteQuarto() {
		Quarto quarto1 = new Quarto(null, "02", "suite");
		quartoRepository.save(quarto1);
		
		quartoRepository.deleteById(quarto1.getId());
		
		Optional<Quarto>quartoOptional = quartoRepository.findById(quarto1.getId());
		
		assertTrue(quartoOptional.isEmpty());
	}
	
}
