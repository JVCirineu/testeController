package com.testehospede.entitie;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReservaTest {

	private Reserva reserva;
	private Hospede hospede;
	private Quarto quarto;

	@BeforeEach
	public void setUp() {
		reserva = new Reserva(1L, LocalDate.of(2024, 9, 1), LocalDate.of(2024, 9, 10),hospede, quarto);
	}

	@Test
	public void testId() {

		reserva.setId(2L);

		assertEquals(2L, reserva.getId(), "O ID deve ser igual ao valor atribuido");

	}

	@Test
	public void testHospede() {
		reserva.setHospede(hospede);
		assertEquals(hospede, reserva.getHospede(), "O hospede deve ser igual ao valor atribuido");
	}

	@Test
	public void testQuarto() {
		reserva.setQuarto(quarto);
		assertEquals(quarto, reserva.getQuarto(), "O quarto deve ser igual ao valor atribuido");
	}

	@Test
	public void testCheckInData() {
		reserva.setCheckInData(LocalDate.of(2024, 9, 1));
		assertEquals(LocalDate.of(2024, 9, 1), reserva.getCheckInData(),
				"A data de check-in deve ser igual ao valor atribuido");
	}

	@Test
	public void testCheckOutData() {
		reserva.setCheckOutData(LocalDate.of(2024, 9, 10));
		assertEquals(LocalDate.of(2024, 9, 10), reserva.getCheckOutData(),
				"A data de check-out deve ser igual ao valor atribuido");
	}

	@Test
	@DisplayName("Testando o construtor com todos os argumentos")
	void testConstrutorAll() {
		Reserva novaReserva = new Reserva(3L, LocalDate.of(2024, 9, 22), LocalDate.of(2024, 9, 25),hospede, quarto);
		assertAll("novoReserva", () -> assertEquals(3L, novaReserva.getId()),
				() -> assertEquals(hospede, novaReserva.getHospede()),
				() -> assertEquals(quarto, novaReserva.getQuarto()),
				() -> assertEquals(LocalDate.of(2024, 9, 22), novaReserva.getCheckInData()),
				() -> assertEquals(LocalDate.of(2024, 9, 25), novaReserva.getCheckOutData()));
	}
}