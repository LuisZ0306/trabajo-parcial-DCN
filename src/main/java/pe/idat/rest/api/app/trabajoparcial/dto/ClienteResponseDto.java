package pe.idat.rest.api.app.trabajoparcial.dto;

public record ClienteResponseDto(
        Long idCliente,
        String dni,
        String nombres,
        String correo,
        String telefono
) {
}
