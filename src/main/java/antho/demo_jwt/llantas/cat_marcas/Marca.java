package antho.demo_jwt.llantas.cat_marcas;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="cat_marcas", uniqueConstraints = {@UniqueConstraint(columnNames = {"nom_marcas"})})
public class Marca {
    @Id
    @GeneratedValue
    @Column(name = "id_marca")
    Integer idMarca;
    @Column(nullable = false)
    String nomMarcas;
    @Column(name = "fec_alta")
    LocalDateTime fecAlta;
    @Column(name = "opc_activo")
    Boolean opcActivo;
}
