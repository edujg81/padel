package es.laspalmeras.padel.jornada;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.laspalmeras.padel.dto.JornadaDTO;
import es.laspalmeras.padel.service.JornadaService;

@SpringBootTest
public class JornadaServiceImplTest {

    @Autowired
    private JornadaService jornadaService;

    @Test
    public void testCreateJornada() {
        Long campeonatoId = 1L;
        LocalDate fechaInicio = LocalDate.now();

        JornadaDTO jornada = jornadaService.createJornada(campeonatoId, fechaInicio);
        assertNotNull(jornada.getId());
    }
}