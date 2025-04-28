package antho.demo_jwt.Sale;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterSale {
    private String productname;
    private Integer quantity;
}
