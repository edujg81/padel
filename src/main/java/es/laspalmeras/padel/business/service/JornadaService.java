package es.laspalmeras.padel.business.service;

import java.time.LocalDate;
import java.util.List;

import es.laspalmeras.padel.business.service.model.Jornada;

public interface JornadaService {
    
    Jornada createJornada(Long campeonatoId, LocalDate fechaInicio);

    List<Jornada> findAllJornadas();

    Jornada findJornadaById(Long id);

    List<Jornada> findJornadasByCampeonato(Long campeonatoId);

    void deleteJornada(Long id);
}