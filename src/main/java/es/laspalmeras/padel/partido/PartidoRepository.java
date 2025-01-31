package es.laspalmeras.padel.partido;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.laspalmeras.padel.partido.model.Partido;

@Repository
public interface PartidoRepository extends JpaRepository<Partido, Long> {
	
	List<Partido> findByJornadaId(Long jornadaId);
	
}
