package antho.demo_jwt.llantas.sales;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class RegisterSalesList {
    @NotEmpty(message = "La compra no puede estar vacia")
    List<SaleOne> salesList;//lista de productos vendidos

}
