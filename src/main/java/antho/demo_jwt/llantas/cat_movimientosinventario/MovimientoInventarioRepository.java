package antho.demo_jwt.llantas.cat_movimientosinventario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimientoInventarioRepository extends JpaRepository<MovimientoInventario, Integer> {

    Optional<MovimientoInventario> findByidMovimientoinventario(Integer idMovimientoinventario);
    

}
