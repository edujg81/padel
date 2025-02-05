package es.laspalmeras.padel.controller;

import es.laspalmeras.padel.dto.AusenciaDTO;
import es.laspalmeras.padel.service.AusenciaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/ausencias")
@Tag(name = "Gestión de Ausencia", description = "Operaciones pertenecientes a ausencias en Club de Padel")
public class AusenciaController {

    private final AusenciaService ausenciaService;

    public AusenciaController(AusenciaService ausenciaService) {
        this.ausenciaService = ausenciaService;
    }

    @PostMapping
    public ResponseEntity<AusenciaDTO> createAusencia(@Valid @RequestBody AusenciaDTO ausenciaDTO) {
        AusenciaDTO result = ausenciaService.createAusencia(ausenciaDTO);
        return ResponseEntity.ok(result);
    }
}
