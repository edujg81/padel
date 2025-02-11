package es.laspalmeras.padel.controller;

//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class InscripcionControllerIntegrationTest {
	
//	@Autowired
//    private MockMvc mockMvc;

    @Autowired
    private void mockMvcGetInscripcionById() throws Exception {
//		mockMvc.perform(get("/public/inscripciones/1"))
//                .andExpect(status().isOk());
    }
}