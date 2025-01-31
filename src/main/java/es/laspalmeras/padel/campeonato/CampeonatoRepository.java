package es.laspalmeras.padel.campeonato;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.laspalmeras.padel.campeonato.model.Campeonato;

@Repository
public interface CampeonatoRepository extends JpaRepository<Campeonato, Long> {

	List<Campeonato> findByYearAndCategoriaAndDivisionAndActivoTrue(Integer year, String categoria, Integer division);

}