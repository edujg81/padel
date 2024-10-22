package es.laspalmeras.padel.integration.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.laspalmeras.padel.business.service.model.Jugador;

@Repository
public interface JugadorRepository extends JpaRepository<Jugador, Long> {
	Optional<Jugador> findByDni(String dni);
	
	List<Jugador> findByFechaBajaBeforeAndEstado(LocalDate date, String estado);
	
	@Query( "SELECT p FROM Jugador p WHERE p.id > 0 AND p.lesionado = false AND p NOT IN (" +
            "SELECT DISTINCT p FROM Jugador p, Inscripcion i, Campeonato c " +
            "WHERE p = i.jugador AND i.campeonato = c AND c.categoria = :categoria AND c.year = :year AND c.activo = true)")
	List<Jugador> findJugadoresDisponiblesParaCampeonato(String categoria, int year);
}