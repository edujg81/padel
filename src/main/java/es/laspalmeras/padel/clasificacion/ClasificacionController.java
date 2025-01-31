package es.laspalmeras.padel.clasificacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.laspalmeras.padel.clasificacion.model.dto.ClasificacionDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/public/clasificaciones")
@Tag(name = "Gestión de Clasificaciones", description = "Operaciones relacionadas con la clasificación de campeonatos")
public class ClasificacionController {

    @Autowired
    private ClasificacionService clasificacionService;

    @Operation(summary = "Obtener la clasificación de un campeonato")
    @GetMapping("/campeonato/{campeonatoId}")
    public ResponseEntity<List<ClasificacionDTO>> getClasificacionByCampeonatoId(@PathVariable Long campeonatoId) {
        List<ClasificacionDTO> clasificacion = clasificacionService.findClasificacionByCampeonatoId(campeonatoId);
        return ResponseEntity.ok(clasificacion);
    }
}