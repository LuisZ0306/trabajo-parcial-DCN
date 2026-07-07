package pe.idat.rest.api.app.trabajoparcial.service.impl;

import org.springframework.stereotype.Service;
import pe.idat.rest.api.app.trabajoparcial.dto.TecnicoRequestDto;
import pe.idat.rest.api.app.trabajoparcial.dto.TecnicoResponseDto;
import pe.idat.rest.api.app.trabajoparcial.exception.RecursoNoEncontradoException;
import pe.idat.rest.api.app.trabajoparcial.model.Tecnico;
import pe.idat.rest.api.app.trabajoparcial.repository.TecnicoRepository;
import pe.idat.rest.api.app.trabajoparcial.service.TecnicoService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TecnicoServiceImpl implements TecnicoService {

    private final TecnicoRepository tecnicoRepository;

    public TecnicoServiceImpl(TecnicoRepository tecnicoRepository) {
        this.tecnicoRepository = tecnicoRepository;
    }

    /**
     * Da de alta a un nuevo técnico en el sistema.
     * Convierte los datos recibidos (DTO) en la entidad correspondiente y los persiste.
     */
    @Override
    public TecnicoResponseDto save(TecnicoRequestDto request) {
        Tecnico tecnico = new Tecnico(
                null,
                request.dni(),
                request.nombres(),
                request.especialidad(),
                request.activo()
        );
        Tecnico saved = tecnicoRepository.save(tecnico);
        return mapToDto(saved);
    }

    @Override
    public TecnicoResponseDto findById(Long id) {
        Tecnico tecnico = tecnicoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Técnico no encontrado con id " + id));
        return mapToDto(tecnico);
    }

    @Override
    public List<TecnicoResponseDto> findAll() {
        return tecnicoRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    /**
     * Modifica los datos de un técnico ya registrado.
     * Es útil, por ejemplo, para cambiar el estado activo/inactivo del técnico según su disponibilidad.
     */
    @Override
    public TecnicoResponseDto update(Long id, TecnicoRequestDto request) {
        Tecnico existing = tecnicoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Técnico no encontrado con id " + id + " para actualizar"));
        existing.setDni(request.dni());
        existing.setNombres(request.nombres());
        existing.setEspecialidad(request.especialidad());
        existing.setActivo(request.activo());
        Tecnico updated = tecnicoRepository.save(existing);
        return mapToDto(updated);
    }

    @Override
    public void deleteById(Long id) {
        if (!tecnicoRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("Técnico no encontrado con id " + id + " para eliminar");
        }
        tecnicoRepository.deleteById(id);
    }

    private TecnicoResponseDto mapToDto(Tecnico tecnico) {
        return new TecnicoResponseDto(
                tecnico.getIdTecnico(),
                tecnico.getDni(),
                tecnico.getNombres(),
                tecnico.getEspecialidad(),
                tecnico.getActivo()
        );
    }
}
