package es.laspalmeras.padel.business.service;

import java.util.List;

import org.springframework.stereotype.Service;

import es.laspalmeras.padel.business.service.model.Jornada;

@Service
public interface JornadaService {

    Jornada saveJornada(Jornada jornada);

    List<Jornada> findAllJornadas();

    Jornada createJornadaForCampeonato(Long campeonatoId);
}