package pe.idat.rest.api.app.trabajoparcial.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Solicitud {
    private Long idSolicitud;
    private String descripcion;
    private LocalDateTime fechaCreacion;
    private String estado; // PENDIENTE, EN_PROCESO, RESUELTO
    private Long idCliente;
    private Long idTecnico; // Puede ser nulo al inicio
}
