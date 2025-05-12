package antho.demo_jwt.llantas.ctl_inventariollantas;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Ctl_InventarioLlantaRepository extends JpaRepository<Ctl_InventarioLlanta, Integer> {
    Optional<Ctl_InventarioLlanta> findByIdLlanta(Integer idLlanta);
    // JpaRepository ya proporciona métodos para operaciones CRUD
    // Puedes definir consultas personalizadas aquí si es necesario

}
