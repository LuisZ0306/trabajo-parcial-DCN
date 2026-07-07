package pe.idat.rest.api.app.trabajoparcial.service.impl;

import org.springframework.stereotype.Service;
import pe.idat.rest.api.app.trabajoparcial.dto.ClienteRequestDto;
import pe.idat.rest.api.app.trabajoparcial.dto.ClienteResponseDto;
import pe.idat.rest.api.app.trabajoparcial.exception.RecursoNoEncontradoException;
import pe.idat.rest.api.app.trabajoparcial.model.Cliente;
import pe.idat.rest.api.app.trabajoparcial.repository.ClienteRepository;
import pe.idat.rest.api.app.trabajoparcial.service.ClienteService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    /**
     * Registra un nuevo cliente en el sistema.
     * Mapea los datos de la solicitud (DTO) hacia la entidad Cliente antes de guardarlo.
     */
    @Override
    public ClienteResponseDto save(ClienteRequestDto request) {
        Cliente cliente = new Cliente(
                null,
                request.dni(),
                request.nombres(),
                request.correo(),
                request.telefono()
        );
        Cliente saved = clienteRepository.save(cliente);
        return mapToDto(saved);
    }

    /**
     * Obtiene los detalles de un cliente específico.
     * Lanza una excepción personalizada si el cliente no existe, asegurando un manejo de errores claro.
     */
    @Override
    public ClienteResponseDto findById(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Cliente no encontrado con id " + id));
        return mapToDto(cliente);
    }

    @Override
    public List<ClienteResponseDto> findAll() {
        return clienteRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    /**
     * Actualiza la información de un cliente existente.
     * Primero verifica su existencia, luego actualiza los campos permitidos y guarda los cambios.
     */
    @Override
    public ClienteResponseDto update(Long id, ClienteRequestDto request) {
        Cliente existing = clienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Cliente no encontrado con id " + id + " para actualizar"));
        existing.setDni(request.dni());
        existing.setNombres(request.nombres());
        existing.setCorreo(request.correo());
        existing.setTelefono(request.telefono());
        Cliente updated = clienteRepository.save(existing);
        return mapToDto(updated);
    }

    /**
     * Elimina permanentemente a un cliente de la base de datos temporal según su ID.
     */
    @Override
    public void deleteById(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("Cliente no encontrado con id " + id + " para eliminar");
        }
        clienteRepository.deleteById(id);
    }

    private ClienteResponseDto mapToDto(Cliente cliente) {
        return new ClienteResponseDto(
                cliente.getIdCliente(),
                cliente.getDni(),
                cliente.getNombres(),
                cliente.getCorreo(),
                cliente.getTelefono()
        );
    }
}
