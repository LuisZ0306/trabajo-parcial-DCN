package pe.idat.rest.api.app.trabajoparcial.service;

import pe.idat.rest.api.app.trabajoparcial.dto.ClienteRequestDto;
import pe.idat.rest.api.app.trabajoparcial.dto.ClienteResponseDto;

import java.util.List;

public interface ClienteService {
    ClienteResponseDto save(ClienteRequestDto request);
    ClienteResponseDto findById(Long id);
    List<ClienteResponseDto> findAll();
    ClienteResponseDto update(Long id, ClienteRequestDto request);
    void deleteById(Long id);
}
