package antho.demo_jwt.Sale;

import antho.demo_jwt.Product.Product;
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
@Table(name="sales")
public class Sale {
    @Id
    @GeneratedValue
    Integer id;

    @ManyToOne //Relacion muchos a uno con producto
    @JoinColumn(name = "product_id", nullable = false)
    Product product;

    @Column(nullable = false)
    Integer amount;

    Double price; //se guardara el precio, por si llega a modificarse en un futuro

    Double totalPrice;
}

//esto crea la tabla en sql
