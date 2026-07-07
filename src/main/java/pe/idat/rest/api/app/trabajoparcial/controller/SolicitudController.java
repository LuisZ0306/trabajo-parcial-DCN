package pe.idat.rest.api.app.trabajoparcial.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.idat.rest.api.app.trabajoparcial.dto.SolicitudRequestDto;
import pe.idat.rest.api.app.trabajoparcial.dto.SolicitudResponseDto;
import pe.idat.rest.api.app.trabajoparcial.service.SolicitudService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/solicitudes")
@Tag(name = "Gestión de Solicitudes de Soporte", description = "Endpoints para el CRUD completo de solicitudes de soporte técnico")
public class SolicitudController {

    private final SolicitudService solicitudService;

    public SolicitudController(SolicitudService solicitudService) {
        this.solicitudService = solicitudService;
    }

    @PostMapping
    @Operation(summary = "Registrar una nueva solicitud", description = "Crea una solicitud de soporte para un cliente, validando la existencia de este y de un técnico activo en caso sea asignado.")
    public ResponseEntity<SolicitudResponseDto> save(@Valid @RequestBody SolicitudRequestDto request) {
        SolicitudResponseDto response = solicitudService.save(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Listar todas las solicitudes", description = "Obtiene una lista con detalles completos de las solicitudes, incluyendo clientes y técnicos asociados")
    public ResponseEntity<List<SolicitudResponseDto>> findAll() {
        return ResponseEntity.ok(solicitudService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar solicitud por ID", description = "Retorna el detalle completo de una solicitud según su ID")
    public ResponseEntity<SolicitudResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(solicitudService.findById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una solicitud", description = "Modifica los datos de una solicitud de soporte técnico")
    public ResponseEntity<SolicitudResponseDto> update(@PathVariable Long id, @Valid @RequestBody SolicitudRequestDto request) {
        return ResponseEntity.ok(solicitudService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar solicitud", description = "Elimina físicamente una solicitud por su ID")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        solicitudService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
