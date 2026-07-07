package pe.idat.rest.api.app.trabajoparcial.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.idat.rest.api.app.trabajoparcial.dto.TecnicoRequestDto;
import pe.idat.rest.api.app.trabajoparcial.dto.TecnicoResponseDto;
import pe.idat.rest.api.app.trabajoparcial.service.TecnicoService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tecnicos")
@Tag(name = "Gestión de Técnicos", description = "Endpoints para registrar, consultar, actualizar y eliminar técnicos de soporte")
public class TecnicoController {

    private final TecnicoService tecnicoService;

    public TecnicoController(TecnicoService tecnicoService) {
        this.tecnicoService = tecnicoService;
    }

    @PostMapping
    @Operation(summary = "Registrar un nuevo técnico", description = "Registra un técnico de soporte técnico")
    public ResponseEntity<TecnicoResponseDto> save(@Valid @RequestBody TecnicoRequestDto request) {
        TecnicoResponseDto response = tecnicoService.save(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Listar todos los técnicos", description = "Retorna la lista completa de técnicos registrados")
    public ResponseEntity<List<TecnicoResponseDto>> findAll() {
        return ResponseEntity.ok(tecnicoService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar técnico por ID", description = "Retorna un técnico por su ID único")
    public ResponseEntity<TecnicoResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(tecnicoService.findById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar técnico existente", description = "Modifica los datos de un técnico registrado")
    public ResponseEntity<TecnicoResponseDto> update(@PathVariable Long id, @Valid @RequestBody TecnicoRequestDto request) {
        return ResponseEntity.ok(tecnicoService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar técnico", description = "Elimina un técnico por su ID")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        tecnicoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
