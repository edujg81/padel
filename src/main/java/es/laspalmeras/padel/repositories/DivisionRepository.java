package es.laspalmeras.padel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.laspalmeras.padel.models.Division;

@Repository
public interface DivisionRepository extends JpaRepository<Division, Long> {
}