package antho.demo_jwt.Sale;

import antho.demo_jwt.Product.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SaleDTO {
    private String productname; //Nombre del producto
    private Integer amount;//cantidad del producto
}
