package antho.demo_jwt.llantas.cat_movimientosinventario;

import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
@Table(name="cat_movimientosinventario")
public class MovimientoInventario {
    @Id
    @GeneratedValue
    @Column(name = "id_movimientoinventario")
    Integer idMovimientoinventario;
    String nomMovimientoinventario;
    LocalDateTime fec_alta;
    Boolean opc_activo;
}
