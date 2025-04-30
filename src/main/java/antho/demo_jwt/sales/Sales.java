package antho.demo_jwt.sales;

import java.util.ArrayList;
import java.util.List;

import antho.demo_jwt.Sale.Sale;
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
@Table(name = "salesList")
public class Sales {
    @Id
    @GeneratedValue
    Integer id;

    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)//Relacion uno a muchos
    @JoinColumn(name = "sales_id")//clave foranea en la tabla "sale"
    List<Sale> salesList = new ArrayList<>();//lista de venta individuales

    @Column(nullable = false)
    Double totalPrice;//precio total de la compra
    

}
