package antho.demo_jwt.Product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductStock {
    @NotBlank(message = "Se requiere un nombre del producto")
    @Size(min = 3, max = 30, message = "El nombre del producto debe tener entre 3 y 30 caracteres")
    String productname;
    Integer stock;
}

//esto maneja el registro de los productos
