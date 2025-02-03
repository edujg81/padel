package es.laspalmeras.padel.jornada;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class JornadaControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetJornadaById() throws Exception {
        mockMvc.perform(get("/public/jornadas/1"))
                .andExpect(status().isOk());
    }
}