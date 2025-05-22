package antho.demo_jwt.llantas.cat_modelos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ModeloRepository extends JpaRepository<Modelo,Integer>{
    Optional<Modelo> findByNomModelos(String nomModelos);
    List<Modelo> findByOpcActivoTrueOrderByIdModeloDesc();
}
