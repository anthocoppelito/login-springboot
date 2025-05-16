package antho.demo_jwt.llantas.sales;

import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
@Table(name = "salesdos")
public class SalesList {
    @Id
    @GeneratedValue
    Integer id;

    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)//Relacion uno a muchos
    @JoinColumn(name = "sales_id")//clave foranea en la tabla "sale"
    List<SaleOne> salesList = new ArrayList<>();//lista de venta individuales

    @Column(nullable = false)
    Double totalPrice;

}
