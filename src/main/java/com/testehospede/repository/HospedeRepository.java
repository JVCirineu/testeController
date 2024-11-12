package com.testehospede.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testehospede.entitie.Hospede;

public interface HospedeRepository extends JpaRepository<Hospede, Long> {

}
