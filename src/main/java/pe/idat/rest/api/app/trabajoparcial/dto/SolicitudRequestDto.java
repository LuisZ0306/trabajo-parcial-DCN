package pe.idat.rest.api.app.trabajoparcial.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record SolicitudRequestDto(
        @NotBlank(message = "La descripción es obligatoria")
        String descripcion,

        @NotBlank(message = "El estado es obligatorio (PENDIENTE, EN_PROCESO, RESUELTO)")
        @Pattern(regexp = "^(PENDIENTE|EN_PROCESO|RESUELTO)$", message = "El estado debe ser PENDIENTE, EN_PROCESO o RESUELTO")
        String estado,

        @NotNull(message = "El ID del cliente es obligatorio")
        Long idCliente,

        Long idTecnico // Puede ser nulo si está PENDIENTE de asignación
) {
}
