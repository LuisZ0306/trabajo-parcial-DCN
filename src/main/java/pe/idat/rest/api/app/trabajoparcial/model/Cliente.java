package pe.idat.rest.api.app.trabajoparcial.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    private Long idCliente;
    private String dni;
    private String nombres;
    private String correo;
    private String telefono;
}
