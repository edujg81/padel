package es.laspalmeras.padel.jornada;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.laspalmeras.padel.jornada.model.dto.JornadaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/public/jornadas")
@Tag(name = "Jornadas", description = "Gestión de las jornadas de un campeonato")
public class JornadaController {

    @Autowired
    private JornadaService jornadaService;

    /**
     * Crea una nueva jornada en un campeonato
     * 
     * @param campeonatoId ID del campeonato
     * @param fechaInicio fecha de inicio de la jornada
     * @return objeto DTO de la jornada creada
     */
    @Operation(summary = "Crear jornada de un campeonato")
    @PostMapping
    public ResponseEntity<JornadaDTO> createJornada(@RequestParam Long campeonatoId, @RequestParam LocalDate fechaInicio) {
        JornadaDTO jornada = jornadaService.createJornada(campeonatoId, fechaInicio);
        return ResponseEntity.ok(jornada);
    }
    
    /**
     * Obtiene todas las jornadas
     * 
     * @return Lista de objetos DTO de jornada
     */
    @Operation(summary = "Obtener todas las jornadas")
    @GetMapping
    public ResponseEntity<List<JornadaDTO>> getAllJornadas() {
    	return ResponseEntity.ok(jornadaService.findAllJornadas());
    }

    @Operation(summary = "Obtener jornada por ID")
    @GetMapping("/{id}")
    public ResponseEntity<JornadaDTO> getJornadaById(@PathVariable Long id) {
        Optional<JornadaDTO> jornada = jornadaService.findJornadaById(id);
        return jornada.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Obtener jornadas de un campeonato")
    @GetMapping("/campeonato/{campeonatoId}")
    public ResponseEntity<List<JornadaDTO>> getJornadasByCampeonato(@PathVariable Long campeonatoId) {
    	List<JornadaDTO> jornadas = jornadaService.findJornadasByCampeonato(campeonatoId);
        
        if (jornadas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        
        return ResponseEntity.ok(jornadas);
    }
    
    @Operation(summary = "Borrar jornada por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJornada(@PathVariable Long id) {
        jornadaService.deleteJornada(id);
        return ResponseEntity.noContent().build();
    }
}