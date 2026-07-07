package pe.idat.rest.api.app.trabajoparcial.dto;

import java.time.LocalDateTime;

public record SolicitudResponseDto(
        Long idSolicitud,
        String descripcion,
        LocalDateTime fechaCreacion,
        String estado,
        ClienteResponseDto cliente,
        TecnicoResponseDto tecnico
) {
}
