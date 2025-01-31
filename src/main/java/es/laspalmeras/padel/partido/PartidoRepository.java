package es.laspalmeras.padel.partido;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidoRepository extends JpaRepository<Partido, Long> {
	
	List<Partido> findByJornadaId(Long jornadaId);
	
}
