package antho.demo_jwt.Sale;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import antho.demo_jwt.Product.Product;
import antho.demo_jwt.Product.ProductService;
import antho.demo_jwt.Product.ProductStock;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaleService {

    private final SaleRepository saleRepository;
    private final ProductService  productService;


    //conseguir todas las ventas
    public List<Sale> getAllSales() {
        return saleRepository.findAll().stream()
            .map(sale -> new Sale(
                sale.getId(), 
                sale.getProduct(), 
                sale.getAmount(),
                sale.getPrice(),
                sale.getTotalPrice()
                ))
                .collect(Collectors.toList());   
    }

  

    //hacer venta de un producto con stock
    public void makeSale(RegisterSale registerSale) {
        // Verifica si el producto existe
        if (productService.productExists(registerSale.getProductname())){


            // Verifica si el producto tiene stock
            if (productService.checkStock(registerSale.getProductname(), registerSale.getQuantity())){
                //construir un tipo ProductStock
                ProductStock productStock = new ProductStock(registerSale.getProductname(), registerSale.getQuantity());

                //verificar si la cantidad a comprar es mayor a 0
                if (registerSale.getQuantity() <= 0) {
                    throw new RuntimeException("La cantidad a comprar debe ser mayor a 0");
                }


                //quitar stock comprado
                productService.removeStock(productStock);

                //crear venta
                Product product = productService.getProductEntityByName(registerSale.getProductname());
                Sale sale = Sale.builder()
                    .product(product)
                    .amount(registerSale.getQuantity())
                    .price(product.getPrice())
                    .totalPrice(product.getPrice() * registerSale.getQuantity())
                    .build();

                // Guardar venta en la base de datos
                saleRepository.save(sale);

            } else {
                throw new RuntimeException("No hay suficiente stock");
            }
            
        }else{
            throw new RuntimeException("Producto no encontrado");
        }
        
    }

    


}
