package es.laspalmeras.padel.business.service.impl;

import es.laspalmeras.padel.business.service.InscripcionService;
import es.laspalmeras.padel.business.service.model.Inscripcion;
import es.laspalmeras.padel.integration.repository.InscripcionRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InscripcionServiceImpl implements InscripcionService{

    @Autowired
    private InscripcionRepository inscripcionRepository;

    public Inscripcion saveInscripcion(Inscripcion inscripcion) throws Exception {
        // Aquí se deben verificar las reglas de negocio antes de guardar la inscripción
        return inscripcionRepository.save(inscripcion);
    }

    public List<Inscripcion> findAllInscripciones() {
        return inscripcionRepository.findAll();
    }

    public void deleteInscripcion(Long id) {
        inscripcionRepository.deleteById(id);
    }
}
