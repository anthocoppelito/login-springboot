package antho.demo_jwt.llantas.ctl_movimientosinventario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Ctl_MovimientoInventarioRepository extends JpaRepository<Ctl_MovimientoInventario, Integer> {

    Optional<Ctl_MovimientoInventario> findByIdMovimientoinventario(Integer idMovimientoinventario);
    // Aquí puedes agregar métodos personalizados si es necesario
    // Por ejemplo, para buscar por empleado o fecha de movimiento
    // List<Ctl_MovimientoInventario> findByNumEmpleado(Integer numEmpleado);
    // List<Ctl_MovimientoInventario> findByFecMovimiento(Date fecMovimiento);

}
