package antho.demo_jwt.Product;

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
@Table(name="products", uniqueConstraints = {@UniqueConstraint(columnNames = {"productname"})})
public class Product {
    @Id
    @GeneratedValue
    Integer id;
    @Column(nullable = false)
    String productname;
    String description;
    String category;
    String image;
    @Column(nullable = false)
    double price;
    @Column(nullable = false)
    Integer stock;

}


// esto maneja la creacion de bd 