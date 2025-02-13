package es.laspalmeras.padel.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import es.laspalmeras.padel.dto.JornadaDTO;

public interface JornadaService {
    
    JornadaDTO createJornada(Long campeonatoId, LocalDate fechaInicio);

    List<JornadaDTO> findAllJornadas();

    Optional<JornadaDTO> findJornadaById(Long id);

    List<JornadaDTO> findJornadasByCampeonato(Long campeonatoId);

    void deleteJornada(Long id);
}