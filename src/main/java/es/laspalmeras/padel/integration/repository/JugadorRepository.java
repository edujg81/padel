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
	
	@Query( "SELECT j FROM Jugador j WHERE j.id > 0 AND j.lesionado = false " +
			"AND (j.sexo = :categoria OR :categoria = 'Mixto') AND j NOT IN (" +
            "SELECT DISTINCT j FROM Jugador j, Inscripcion i, Campeonato c " +
            "WHERE j = i.jugador AND i.campeonato = c AND c.categoria = :categoria AND c.year = :year AND c.activo = true)")
	List<Jugador> findJugadoresDisponiblesParaCampeonato(String categoria, int year);
}