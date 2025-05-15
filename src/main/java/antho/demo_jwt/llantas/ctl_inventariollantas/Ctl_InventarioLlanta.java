package antho.demo_jwt.llantas.ctl_inventariollantas;

import java.time.LocalDateTime;

import antho.demo_jwt.llantas.cat_marcas.Marca;
import antho.demo_jwt.llantas.cat_modelos.Modelo;
import antho.demo_jwt.llantas.cat_rines.Rines;
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
@Table(name="ctl_inventariollantas")
public class Ctl_InventarioLlanta {
    @Id
    @GeneratedValue
    @Column(name = "id_llanta")
    Integer idLlanta;

    //Integer id_marca; que deberia conectarse con el campo id_marca de la tabla cat_marcas
    @ManyToOne
    @JoinColumn(name = "id_marca", referencedColumnName = "id_marca") //referencedcolumname funcionaria si no se tiene una llave primaria y el nombre es diferente a id. en este caso no afecta ya que es llave primaria de la tabla a la que nos conectamos
    Marca marca;
    
    @ManyToOne
    @JoinColumn(name = "id_modelo")
    Modelo modelo;

    @ManyToOne
    @JoinColumn(name = "id_rin")
    Rines rines;

    Double num_preciobasico;
    LocalDateTime fec_alta;
    Integer num_existencia;


}
