package antho.demo_jwt.llantas.cat_marcas;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MarcaRepository extends JpaRepository<Marca, Integer> {
    Optional<Marca> findByNomMarcas(String nomMarcas);
    // JpaRepository already provides methods for CRUD operations
    // You can define custom queries here if needed

}
