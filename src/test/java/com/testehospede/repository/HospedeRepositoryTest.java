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

import com.testehospede.entitie.Hospede;

@DataJpaTest
class HospedeRepositoryTest {

	@Autowired
	private HospedeRepository hospedeRepository;

	@DisplayName("Testando Save")
	@Test
	void testSalvarRepository() {
		Hospede hospede1 = new Hospede(null, "Julia", "julia@gmail.com", "(00)0000-0000");

		Hospede saveHospede = hospedeRepository.save(hospede1);

		assertNotNull(saveHospede);
		assertTrue(saveHospede.getId() > 0);
	}

	@DisplayName("Testando Get para todos Hospedes")
	@Test
	void testGetAllRepository() {
		Hospede hospede1 = new Hospede(null, "Julia", "julia@gmail.com", "(00)000-000");
		Hospede hospede2 = new Hospede(null, "Julioo", "julioo@gmail.com", "(00)000-000");

		hospedeRepository.save(hospede1);
		hospedeRepository.save(hospede2);

		List<Hospede> hospedeList = hospedeRepository.findAll();

		assertNotNull(hospedeList);
		assertEquals(2, hospedeList.size());
	}

	@DisplayName("Testando Get By Id")
	@Test
	void testGetById() {
		Hospede hospede2 = new Hospede(null, "Julioo", "julioo@gmail.com", "(00)000-000");
		
		hospedeRepository.save(hospede2);
		
		Hospede saveHospede = hospedeRepository.findById(hospede2.getId()).get();
		
		assertNotNull(saveHospede);
		assertEquals(hospede2.getId(), saveHospede.getId());
	}
	@DisplayName("Testando o Update")
	@Test
	void TestUpdateHospede() {
		Hospede hospede1 = new Hospede(null, "Julia", "julia@gmail.com", "(00000-0000");
		hospedeRepository.save(hospede1);
		Hospede saveHospede = hospedeRepository.findById(hospede1.getId()).get();
		hospede1.setNome("Leo");
		hospede1.setEmail("leo@gmail.com");
		
		Hospede updateHospede = hospedeRepository.save(saveHospede);
		assertNotNull(updateHospede);
		assertEquals("Leo",updateHospede.getNome());
		assertEquals("leo@gmail.com",updateHospede.getEmail());
	}
	@DisplayName("Testando o delete")
	@Test
	void TestDeleteHospede() {
		Hospede hospede1 = new Hospede(null, "Julia", "julia@gmail.com", "(00000-0000");
		hospedeRepository.save(hospede1);
		
		hospedeRepository.deleteById(hospede1.getId());
		
		Optional<Hospede>hospedeOptional = hospedeRepository.findById(hospede1.getId());
		
		assertTrue(hospedeOptional.isEmpty());
	}
	
}
