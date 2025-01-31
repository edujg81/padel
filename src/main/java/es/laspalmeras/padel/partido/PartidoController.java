package es.laspalmeras.padel.partido;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/public/partidos")
@Tag(name = "Gestión de Partido", description = "Operaciones pertenecientes a partidos en Club de Padel")
public class PartidoController {

    @Autowired
    private PartidoService partidoService;

    @Operation(summary = "Obtener todos los partidos")
    @GetMapping
    public List<PartidoDTO> getAllPartidos() {
        return partidoService.getAllPartidos();
    }

    @Operation(summary = "Obtener partido por ID")
    @GetMapping("/{id}")
    public ResponseEntity<PartidoDTO> getPartidoById(@PathVariable Long id) {
        PartidoDTO partido = partidoService.getPartidoById(id);
        return ResponseEntity.ok(partido);
    }

    @Operation(summary = "Obtener partidos de una jornada(ID)")
    @GetMapping("/jornada/{jornadaId}")
    public List<PartidoDTO> getPartidosByJornada(@PathVariable Long jornadaId) {
        return partidoService.getPartidosByJornada(jornadaId);
    }

    @Operation(summary = "Crear partidos para una jornada")
    @PostMapping("/jornada/{jornadaId}")
    public ResponseEntity<List<PartidoDTO>> createPartidosForJornada(@PathVariable Long jornadaId) {
        List<PartidoDTO> partidos = partidoService.createPartidosForJornada(jornadaId);
        return ResponseEntity.ok(partidos);
    }

    @Operation(summary = "Actualizar partido")
    @PutMapping("/{id}")
    public ResponseEntity<PartidoDTO> updatePartido(@PathVariable Long id, @RequestBody PartidoDTO partidoDetails) {
        PartidoDTO updatedPartido = partidoService.updatePartido(id, partidoDetails);
        return ResponseEntity.ok(updatedPartido);
    }

    @Operation(summary = "Borrar partido")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePartido(@PathVariable Long id) {
        partidoService.deletePartido(id);
        return ResponseEntity.noContent().build();
    }
}
