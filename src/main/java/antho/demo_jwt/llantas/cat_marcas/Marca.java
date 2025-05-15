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
    Integer id_marca;
    @Column(nullable = false)
    String nomMarcas;
    LocalDateTime fec_alta;
    Boolean opc_activo;
}
