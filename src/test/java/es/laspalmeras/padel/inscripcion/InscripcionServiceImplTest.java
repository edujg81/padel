package es.laspalmeras.padel.inscripcion;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.laspalmeras.padel.inscripcion.model.Inscripcion;

@SpringBootTest
public class InscripcionServiceImplTest {

    @Autowired
    private InscripcionService inscripcionService;

    @Test
    public void testInscribirJugador() {
        Long campeonatoId = 1L;
        Long jugadorId = 1L;

        Inscripcion inscripcion = inscripcionService.inscribirJugador(campeonatoId, jugadorId);
        assertNotNull(inscripcion.getId());
    }
}
