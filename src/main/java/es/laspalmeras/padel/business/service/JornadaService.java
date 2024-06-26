package es.laspalmeras.padel.business.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import es.laspalmeras.padel.business.service.dto.JornadaDTO;
import es.laspalmeras.padel.business.service.model.Jornada;

public interface JornadaService {
    
    Jornada createJornada(Long campeonatoId, LocalDate fechaInicio);

    List<JornadaDTO> findAllJornadas();

    Optional<JornadaDTO> findJornadaById(Long id);

    List<Jornada> findJornadasByCampeonato(Long campeonatoId);

    void deleteJornada(Long id);
}