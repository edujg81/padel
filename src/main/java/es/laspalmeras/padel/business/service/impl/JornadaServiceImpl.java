package es.laspalmeras.padel.business.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.laspalmeras.padel.business.service.JornadaService;
import es.laspalmeras.padel.business.service.model.Jornada;
import es.laspalmeras.padel.integration.repository.JornadaRepository;

@Service
public class JornadaServiceImpl implements JornadaService {

    @Autowired
    private JornadaRepository jornadaRepository;

    public Jornada saveJornada(Jornada jornada) {
        // Lógica para asignar el número de jornada y verificar las reglas de negocio
        return jornadaRepository.save(jornada);
    }

    public List<Jornada> findAllJornadas() {
        return jornadaRepository.findAll();
    }

    public Jornada createJornadaForCampeonato(Long campeonatoId) {
        // Lógica para crear una nueva jornada para un campeonato
        // Asignar número de jornada correlativo, establecer fecha de inicio, etc.
        Jornada jornada = new Jornada();
        jornada.setNumero(1); // Implementar lógica para obtener el número correlativo
        jornada.setFechaInicio(LocalDate.now());
        // Lógica para obtener el campeonato por ID y asignarlo
        return jornadaRepository.save(jornada);
    }
}