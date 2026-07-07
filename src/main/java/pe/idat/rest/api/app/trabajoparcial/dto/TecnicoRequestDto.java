package pe.idat.rest.api.app.trabajoparcial.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record TecnicoRequestDto(
        @NotBlank(message = "El DNI es obligatorio")
        @Pattern(regexp = "^\\d{8}$", message = "El DNI debe tener exactamente 8 dígitos numéricos")
        String dni,

        @NotBlank(message = "El nombre es obligatorio")
        String nombres,

        @NotBlank(message = "La especialidad es obligatoria")
        String especialidad,

        @NotNull(message = "El estado activo es obligatorio")
        Boolean activo
) {
}
