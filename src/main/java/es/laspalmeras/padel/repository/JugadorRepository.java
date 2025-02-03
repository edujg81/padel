package es.laspalmeras.padel.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.laspalmeras.padel.model.Jugador;

@Repository
public interface JugadorRepository extends JpaRepository<Jugador, Long> {
	Optional<Jugador> findByDni(String dni);
	
	List<Jugador> findByFechaBajaBeforeAndEstado(LocalDate date, String estado);
	
	@Query( "SELECT j FROM Jugador j WHERE j.id > 0 AND j.lesionado = false " +
			"AND (j.sexo = :categoria OR :categoria = 'Mixto') " +
			//"AND j NOT IN (SELECT i.jugador FROM Inscripcion i, Campeonato c " +
            //"WHERE i.campeonato = c AND c.categoria = :categoria AND c.year = :year AND c.activo = true)")
            "AND j NOT IN (SELECT i.jugador FROM Inscripcion i " +
            "WHERE i.campeonato.categoria = :categoria AND i.campeonato.year = :year AND i.campeonato.activo = true)")
	
	List<Jugador> findJugadoresDisponiblesParaCampeonato(String categoria, int year);
}