package es.laspalmeras.padel.integration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.laspalmeras.padel.business.service.model.Division;

@Repository
public interface DivisionRepository extends JpaRepository<Division, Long> {
}