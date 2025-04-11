package antho.demo_jwt.Product;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDTO {
    private Integer id;
    private String productname;
    private String description;
    private String category;
    private String image;
    private double price;
    private Integer stock;


}

//esto maneja el dto de los productos
