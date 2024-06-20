package es.laspalmeras.padel.business.service;

import java.util.List;

import org.springframework.stereotype.Service;

import es.laspalmeras.padel.business.service.model.Inscripcion;

@Service
public interface InscripcionService {

    Inscripcion saveInscripcion(Inscripcion inscripcion) throws Exception;

    List<Inscripcion> findAllInscripciones();

    void deleteInscripcion(Long id);
}
