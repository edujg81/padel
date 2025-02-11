package es.laspalmeras.padel.controller;

//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class JugadorControllerIntegrationTest {

//    @Autowired
//    private MockMvc mockMvc;

    @Test
    public void testGetJugadorById() throws Exception {
//        mockMvc.perform(get("/public/jugadores/1"))
//                .andExpect(status().isOk());
    }
}
