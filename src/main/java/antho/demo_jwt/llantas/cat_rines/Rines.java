package antho.demo_jwt.llantas.cat_rines;

import java.time.LocalDateTime;

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
@Table(name="cat_rines")
public class Rines {
    @Id
    @GeneratedValue
    @Column(name = "id_rin")
    Integer idRin;
    String nomRin;
    @Column(name = "fec_alta")
    LocalDateTime fecAlta;
    @Column(name = "opc_activo")
    Boolean opcActivo;

}
