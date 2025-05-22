package antho.demo_jwt.llantas.cat_rines;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RinesRepository extends JpaRepository<Rines, Integer> {
    
    Optional<Rines> findByNomRin(String nomRin);
    List<Rines> findByOpcActivoTrueOrderByIdRinDesc();

}
