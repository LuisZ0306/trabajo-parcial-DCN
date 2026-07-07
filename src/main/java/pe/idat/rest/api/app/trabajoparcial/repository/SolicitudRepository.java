package pe.idat.rest.api.app.trabajoparcial.repository;

import org.springframework.stereotype.Repository;
import pe.idat.rest.api.app.trabajoparcial.model.Solicitud;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class SolicitudRepository {
    private final Map<Long, Solicitud> solicitudes = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Solicitud save(Solicitud solicitud) {
        if (solicitud.getIdSolicitud() == null) {
            solicitud.setIdSolicitud(idGenerator.getAndIncrement());
        }
        solicitudes.put(solicitud.getIdSolicitud(), solicitud);
        return solicitud;
    }

    public Optional<Solicitud> findById(Long idSolicitud) {
        return Optional.ofNullable(solicitudes.get(idSolicitud));
    }

    public List<Solicitud> findAll() {
        return new ArrayList<>(solicitudes.values());
    }

    public void deleteById(Long idSolicitud) {
        solicitudes.remove(idSolicitud);
    }

    public boolean existsById(Long idSolicitud) {
        return solicitudes.containsKey(idSolicitud);
    }
}
