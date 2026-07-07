package pe.idat.rest.api.app.trabajoparcial.service;

import pe.idat.rest.api.app.trabajoparcial.dto.SolicitudRequestDto;
import pe.idat.rest.api.app.trabajoparcial.dto.SolicitudResponseDto;

import java.util.List;

public interface SolicitudService {
    SolicitudResponseDto save(SolicitudRequestDto request);
    SolicitudResponseDto findById(Long id);
    List<SolicitudResponseDto> findAll();
    SolicitudResponseDto update(Long id, SolicitudRequestDto request);
    void deleteById(Long id);
}
