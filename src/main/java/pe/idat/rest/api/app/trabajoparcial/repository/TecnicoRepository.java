package pe.idat.rest.api.app.trabajoparcial.repository;

import org.springframework.stereotype.Repository;
import pe.idat.rest.api.app.trabajoparcial.model.Tecnico;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class TecnicoRepository {
    private final Map<Long, Tecnico> tecnicos = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Tecnico save(Tecnico tecnico) {
        if (tecnico.getIdTecnico() == null) {
            tecnico.setIdTecnico(idGenerator.getAndIncrement());
        }
        tecnicos.put(tecnico.getIdTecnico(), tecnico);
        return tecnico;
    }

    public Optional<Tecnico> findById(Long idTecnico) {
        return Optional.ofNullable(tecnicos.get(idTecnico));
    }

    public List<Tecnico> findAll() {
        return new ArrayList<>(tecnicos.values());
    }

    public void deleteById(Long idTecnico) {
        tecnicos.remove(idTecnico);
    }

    public boolean existsById(Long idTecnico) {
        return tecnicos.containsKey(idTecnico);
    }
}
