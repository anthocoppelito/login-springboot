package antho.demo_jwt.llantas.ctl_movimientosinventario;

import java.util.Date;

import antho.demo_jwt.llantas.ctl_inventariollantas.Ctl_InventarioLlanta;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="ctl_movimientosinventario")
public class Ctl_MovimientoInventario {

    @Id
    @GeneratedValue
    @Column(name = "id_movimientoinventario")
    Integer idMovimientoinventario;

    @ManyToOne
    @JoinColumn(name = "id_llanta")
    Ctl_InventarioLlanta llanta;

    Double num_precio;
    Date fec_movimiento;

    Integer num_empleado;
    Boolean opc_activo;


}
