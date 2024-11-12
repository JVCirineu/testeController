package com.testehospede.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.testehospede.entitie.Hospede;
import com.testehospede.repository.HospedeRepository;
import com.testehospede.service.HospedeService;

@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
class HospedeControllerTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private HospedeRepository hospedeRepository;
	
	@Autowired
	private HospedeService hospedeService;
	
	@BeforeEach
	void setUp() {
		hospedeRepository.deleteAll();
	}
	
	@Test
	@DisplayName("Teste de criação Hospede")
	void TestCriarHospede() {
		Hospede hospede = new Hospede(null, "Julia Vitória", "julia@gmail.com", "(00)0000-0000");
		ResponseEntity<Hospede> response = restTemplate.postForEntity("/api/hospedes", hospede, Hospede.class);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Julia Vitória", response.getBody().getNome());
	}
	
	@Test
	@DisplayName("Teste de listagem de todos os Hospedes")
	void testListarTodosHospedes() {
		Hospede hospede1 = new Hospede (null, "Julia Vitoria", "julia@gmail.com","(00)0000-0000");
		Hospede hospede2 = new Hospede (null, "Julio Verne", "julio.com","(11)0000-1111");
		
		hospedeService.salvarHospede(hospede1);
		hospedeService.salvarHospede(hospede2);
		
		ResponseEntity<Hospede[]> response = restTemplate.getForEntity("/api/hospedes", Hospede[].class);
		
		assertEquals(HttpStatus.OK, response.getStatusCode(),"A resposta deve ser 200 OK");
		assertNotNull(response.getBody(), "O corpo da resposta nao deveria ser nulo.");
		assertEquals(2, response.getBody().length,"A quantidade de hóspede retornada deveria ser 2.");
		
	}
	
	@Test
	@DisplayName("Teste de buscar Hospede por Id")
	void testBuscarHospedeId() {
		Hospede hospede = new Hospede(null, "Julia","julia@gmail.com","(00)2222-2222");
		
		Hospede hospedeSalvo = hospedeRepository.save(hospede);
		
		ResponseEntity<Hospede> response = restTemplate.getForEntity("/api/hospedes/" + hospedeSalvo.getId(), Hospede.class);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals("Julia", response.getBody().getNome());
	}
	
	@Test
	@DisplayName("Teste de atualização de Hospede")
	void testAtualizarHospede() {
		Hospede hospedeSalvo = hospedeRepository.save(new Hospede(null, "Julia","julia@gmail.com","(00)0000-0000"));
		Hospede hospedeAtualizado = new Hospede(hospedeSalvo.getId(),"Matheus","matheus@gmail.com","(99)9999-9999");
		
		HttpEntity<Hospede> requestUpdate = new HttpEntity<>(hospedeAtualizado);
		ResponseEntity<Hospede> response = restTemplate.exchange("/api/hospedes/" + hospedeSalvo.getId(), HttpMethod.PUT, requestUpdate,Hospede.class);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals("Matheus", response.getBody().getNome());
		assertEquals("matheus@gmail.com", response.getBody().getEmail());
	}
	
	@Test
	@DisplayName("teste de exclusão de Hospede")
	void testDeletarHospede() {
		Hospede hospede = new Hospede(null, "julia","julia@gmail.com", "(00)0000-0000");
		Hospede hospedeSalvo = hospedeService.salvarHospede(hospede);
		
		ResponseEntity<Void> response = restTemplate.exchange("/api/hospedes/" + hospedeSalvo.getId(),HttpMethod.DELETE, null, Void.class);
		
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode(),"A resposta deveria ser 205 No Content");
		
		ResponseEntity<Hospede> checkDeleted = restTemplate.getForEntity("/api/hospedes/" + hospedeSalvo.getId(), Hospede.class);
		
		assertEquals(HttpStatus.NOT_FOUND, checkDeleted.getStatusCode(), "Após O DELETE o hóspede não deve ser encontrado");
		
	}
	
}
