package pe.idat.rest.api.app.trabajoparcial.repository;

import org.springframework.stereotype.Repository;
import pe.idat.rest.api.app.trabajoparcial.model.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ClienteRepository {
    private final Map<Long, Cliente> clientes = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Cliente save(Cliente cliente) {
        if (cliente.getIdCliente() == null) {
            cliente.setIdCliente(idGenerator.getAndIncrement());
        }
        clientes.put(cliente.getIdCliente(), cliente);
        return cliente;
    }

    public Optional<Cliente> findById(Long idCliente) {
        return Optional.ofNullable(clientes.get(idCliente));
    }

    public List<Cliente> findAll() {
        return new ArrayList<>(clientes.values());
    }

    public void deleteById(Long idCliente) {
        clientes.remove(idCliente);
    }

    public boolean existsById(Long idCliente) {
        return clientes.containsKey(idCliente);
    }
}
