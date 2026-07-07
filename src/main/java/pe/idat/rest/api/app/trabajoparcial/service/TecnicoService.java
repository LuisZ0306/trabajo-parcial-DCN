package pe.idat.rest.api.app.trabajoparcial.service;

import pe.idat.rest.api.app.trabajoparcial.dto.TecnicoRequestDto;
import pe.idat.rest.api.app.trabajoparcial.dto.TecnicoResponseDto;

import java.util.List;

public interface TecnicoService {
    TecnicoResponseDto save(TecnicoRequestDto request);
    TecnicoResponseDto findById(Long id);
    List<TecnicoResponseDto> findAll();
    TecnicoResponseDto update(Long id, TecnicoRequestDto request);
    void deleteById(Long id);
}
