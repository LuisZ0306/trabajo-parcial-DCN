package pe.idat.rest.api.app.trabajoparcial.dto;

public record TecnicoResponseDto(
        Long idTecnico,
        String dni,
        String nombres,
        String especialidad,
        Boolean activo
) {
}
