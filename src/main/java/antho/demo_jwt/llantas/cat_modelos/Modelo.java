package antho.demo_jwt.llantas.cat_modelos;

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
@Table(name="cat_modelos")
public class Modelo {
    @Id
    @GeneratedValue
    @Column(name = "id_modelo")
    Integer id_modelo;
    String nomModelos;
    Date fec_alta;
    Boolean opc_activo;
}
