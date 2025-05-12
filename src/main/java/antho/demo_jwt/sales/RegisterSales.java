package antho.demo_jwt.sales;

import java.util.List;

import antho.demo_jwt.Sale.SaleDTO;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class RegisterSales {
    @NotEmpty(message = "La compra no puede estar vacia")
    List<SaleDTO> salesList;//lista de productos vendidos
}

