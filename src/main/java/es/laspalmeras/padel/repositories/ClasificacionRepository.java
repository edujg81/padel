package es.laspalmeras.padel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.laspalmeras.padel.models.Campeonato;
import es.laspalmeras.padel.models.Clasificacion;

import java.util.List;

@Repository
public interface ClasificacionRepository extends JpaRepository<Clasificacion, Long> {
    List<Clasificacion> findByCampeonatoOrderByPosicionAsc(Campeonato campeonato);
}