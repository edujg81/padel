package es.laspalmeras.padel.campeonato;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.laspalmeras.padel.campeonato.model.dto.CampeonatoDTO;

@SpringBootTest
public class CampeonatoServiceImplTest {

    @Autowired
    private CampeonatoService campeonatoService;

    @Test
    public void testCreateCampeonato() {
        CampeonatoDTO campeonatoDTO = new CampeonatoDTO();
        campeonatoDTO.setYear(2025);
        campeonatoDTO.setCategoria("Masculino");
        campeonatoDTO.setDivision(1);
        campeonatoDTO.setEstado("Sin iniciar");
        campeonatoDTO.setActivo(true);
        campeonatoDTO.setPuntosPorVictoria(2);
        campeonatoDTO.setPuntosPorDerrota(0);

        CampeonatoDTO nuevoCampeonato = campeonatoService.createCampeonato(campeonatoDTO);
        assertNotNull(nuevoCampeonato.getId());
    }
}