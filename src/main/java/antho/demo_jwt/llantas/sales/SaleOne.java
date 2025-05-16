package antho.demo_jwt.llantas.sales;

import antho.demo_jwt.llantas.ctl_inventariollantas.Ctl_InventarioLlanta;
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

//sales de llantas
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "salesone")
public class SaleOne {
    @Id
    @GeneratedValue
    Integer id;

    @ManyToOne
    @JoinColumn(name= "llanta_id", nullable = false)
    Ctl_InventarioLlanta llantas;

    Integer amount;
    Double price;
    Double totalPrice;




}
