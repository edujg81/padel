package es.laspalmeras.padel.jugador;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.laspalmeras.padel.jugador.model.dto.JugadorDTO;

@SpringBootTest
public class JugadorServiceImplTest {

    @Autowired
    private JugadorService jugadorService;

    @Test
    public void testCreateJugador() {
        JugadorDTO jugadorDTO = new JugadorDTO();
        jugadorDTO.setDni("12345678Z");
        jugadorDTO.setNombreCompleto("Juan Pérez");
        jugadorDTO.setSexo("Masculino");
        jugadorDTO.setEstado("Alta");
        jugadorDTO.setLesionado(false);

        Long id = jugadorService.create(jugadorDTO);
        assertNotNull(id);
    }
}