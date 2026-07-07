package pe.idat.rest.api.app.trabajoparcial.service.impl;

import org.springframework.stereotype.Service;
import pe.idat.rest.api.app.trabajoparcial.dto.ClienteResponseDto;
import pe.idat.rest.api.app.trabajoparcial.dto.SolicitudRequestDto;
import pe.idat.rest.api.app.trabajoparcial.dto.SolicitudResponseDto;
import pe.idat.rest.api.app.trabajoparcial.dto.TecnicoResponseDto;
import pe.idat.rest.api.app.trabajoparcial.exception.RecursoNoEncontradoException;
import pe.idat.rest.api.app.trabajoparcial.exception.ReglaNegocioException;
import pe.idat.rest.api.app.trabajoparcial.model.Cliente;
import pe.idat.rest.api.app.trabajoparcial.model.Solicitud;
import pe.idat.rest.api.app.trabajoparcial.model.Tecnico;
import pe.idat.rest.api.app.trabajoparcial.repository.ClienteRepository;
import pe.idat.rest.api.app.trabajoparcial.repository.SolicitudRepository;
import pe.idat.rest.api.app.trabajoparcial.repository.TecnicoRepository;
import pe.idat.rest.api.app.trabajoparcial.service.SolicitudService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SolicitudServiceImpl implements SolicitudService {

    private final SolicitudRepository solicitudRepository;
    private final ClienteRepository clienteRepository;
    private final TecnicoRepository tecnicoRepository;

    public SolicitudServiceImpl(SolicitudRepository solicitudRepository,
                                ClienteRepository clienteRepository,
                                TecnicoRepository tecnicoRepository) {
        this.solicitudRepository = solicitudRepository;
        this.clienteRepository = clienteRepository;
        this.tecnicoRepository = tecnicoRepository;
    }

    @Override
    public SolicitudResponseDto save(SolicitudRequestDto request) {
        // Validar que el cliente exista
        Cliente cliente = clienteRepository.findById(request.idCliente())
                .orElseThrow(() -> new RecursoNoEncontradoException("No se puede registrar la solicitud. Cliente no encontrado con id: " + request.idCliente()));

        // Validar técnico si se proporciona uno
        if (request.idTecnico() != null) {
            Tecnico tecnico = tecnicoRepository.findById(request.idTecnico())
                    .orElseThrow(() -> new RecursoNoEncontradoException("Técnico no encontrado con id: " + request.idTecnico()));
            if (!tecnico.getActivo()) {
                throw new ReglaNegocioException("No se puede asignar el técnico con id " + request.idTecnico() + " porque está inactivo");
            }
        }

        Solicitud solicitud = new Solicitud(
                null,
                request.descripcion(),
                LocalDateTime.now(),
                request.estado(),
                request.idCliente(),
                request.idTecnico()
        );

        Solicitud saved = solicitudRepository.save(solicitud);
        return mapToDto(saved);
    }

    @Override
    public SolicitudResponseDto findById(Long id) {
        Solicitud solicitud = solicitudRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Solicitud no encontrada con id: " + id));
        return mapToDto(solicitud);
    }

    @Override
    public List<SolicitudResponseDto> findAll() {
        return solicitudRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public SolicitudResponseDto update(Long id, SolicitudRequestDto request) {
        Solicitud existing = solicitudRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Solicitud no encontrada con id: " + id + " para actualizar"));

        // Validar cliente
        Cliente cliente = clienteRepository.findById(request.idCliente())
                .orElseThrow(() -> new RecursoNoEncontradoException("Cliente no encontrado con id: " + request.idCliente() + " para la solicitud"));

        // Validar técnico si se proporciona uno
        if (request.idTecnico() != null) {
            Tecnico tecnico = tecnicoRepository.findById(request.idTecnico())
                    .orElseThrow(() -> new RecursoNoEncontradoException("Técnico no encontrado con id: " + request.idTecnico()));
            if (!tecnico.getActivo()) {
                throw new ReglaNegocioException("No se puede asignar el técnico con id " + request.idTecnico() + " porque está inactivo");
            }
        }

        existing.setDescripcion(request.descripcion());
        existing.setEstado(request.estado());
        existing.setIdCliente(request.idCliente());
        existing.setIdTecnico(request.idTecnico());

        Solicitud updated = solicitudRepository.save(existing);
        return mapToDto(updated);
    }

    @Override
    public void deleteById(Long id) {
        if (!solicitudRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("Solicitud no encontrada con id: " + id + " para eliminar");
        }
        solicitudRepository.deleteById(id);
    }

    private SolicitudResponseDto mapToDto(Solicitud solicitud) {
        Cliente cliente = clienteRepository.findById(solicitud.getIdCliente())
                .orElse(null);
        ClienteResponseDto clienteDto = cliente != null ? new ClienteResponseDto(
                cliente.getIdCliente(),
                cliente.getDni(),
                cliente.getNombres(),
                cliente.getCorreo(),
                cliente.getTelefono()
        ) : null;

        TecnicoResponseDto tecnicoDto = null;
        if (solicitud.getIdTecnico() != null) {
            Tecnico tecnico = tecnicoRepository.findById(solicitud.getIdTecnico())
                    .orElse(null);
            if (tecnico != null) {
                tecnicoDto = new TecnicoResponseDto(
                        tecnico.getIdTecnico(),
                        tecnico.getDni(),
                        tecnico.getNombres(),
                        tecnico.getEspecialidad(),
                        tecnico.getActivo()
                );
            }
        }

        return new SolicitudResponseDto(
                solicitud.getIdSolicitud(),
                solicitud.getDescripcion(),
                solicitud.getFechaCreacion(),
                solicitud.getEstado(),
                clienteDto,
                tecnicoDto
        );
    }
}
