package es.laspalmeras.padel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.laspalmeras.padel.models.Campeonato;
import es.laspalmeras.padel.models.Clasificacion;
import es.laspalmeras.padel.models.Jugador;

public interface CampeonatoRepository extends JpaRepository<Campeonato, Long> {

}
