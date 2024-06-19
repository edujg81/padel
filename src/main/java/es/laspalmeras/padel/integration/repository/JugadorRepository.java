package es.laspalmeras.padel.integration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.laspalmeras.padel.business.service.model.Jugador;

@Repository
public interface JugadorRepository extends JpaRepository<Jugador, Long> {
}