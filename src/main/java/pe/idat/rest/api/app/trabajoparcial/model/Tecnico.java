package pe.idat.rest.api.app.trabajoparcial.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tecnico {
    private Long idTecnico;
    private String dni;
    private String nombres;
    private String especialidad;
    private Boolean activo;
}
