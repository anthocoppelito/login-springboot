package antho.demo_jwt.Sale;

import antho.demo_jwt.Product.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SaleDTO {
    private Integer id;
    private Product product; //contiene productname precio y stock
    private Integer amount;

    //private String date;//fecha que se realiza la compra
    //private String username; //usuario que registra la compra

    private Double price; //se guardara el precio, por si llega a modificarse en un futuro
    //este se calcula
    private Double totalPrice;

}
