package pe.idat.rest.api.app.trabajoparcial.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.idat.rest.api.app.trabajoparcial.dto.ClienteRequestDto;
import pe.idat.rest.api.app.trabajoparcial.dto.ClienteResponseDto;
import pe.idat.rest.api.app.trabajoparcial.service.ClienteService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clientes")
@Tag(name = "Gestión de Clientes", description = "Endpoints para registrar, consultar, actualizar y eliminar clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    @Operation(summary = "Registrar un nuevo cliente", description = "Registra un cliente en la base de datos temporal")
    public ResponseEntity<ClienteResponseDto> save(@Valid @RequestBody ClienteRequestDto request) {
        ClienteResponseDto response = clienteService.save(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Listar todos los clientes", description = "Retorna una lista de todos los clientes registrados")
    public ResponseEntity<List<ClienteResponseDto>> findAll() {
        return ResponseEntity.ok(clienteService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar cliente por ID", description = "Retorna un cliente según su ID único")
    public ResponseEntity<ClienteResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.findById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un cliente existente", description = "Actualiza la información de un cliente por su ID")
    public ResponseEntity<ClienteResponseDto> update(@PathVariable Long id, @Valid @RequestBody ClienteRequestDto request) {
        return ResponseEntity.ok(clienteService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un cliente", description = "Elimina un cliente por su ID de la base de datos temporal")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        clienteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
