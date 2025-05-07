package antho.demo_jwt.sales;

import java.util.List;

import org.springframework.stereotype.Service;

import antho.demo_jwt.Product.Product;
import antho.demo_jwt.Product.ProductService;
import antho.demo_jwt.Product.ProductStock;
import antho.demo_jwt.Sale.RegisterSale;
import antho.demo_jwt.Sale.Sale;
import antho.demo_jwt.Sale.SaleService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SalesService {

    private final ProductService productService;
    private final SalesRepository salesRepository;
    private final SaleService saleService;

    public void createSale(RegisterSales request) {

        // Crear una lista de ventas individuales
        List<Sale> salesList = request.getSalesList().stream().map(saleDTO -> {
            // Obtener el producto desde la base de datos
            Product product = productService.getProductEntityByName(saleDTO.getProductname());


            //verificar si hay suficiente stock
            if (product.getStock() < saleDTO.getAmount()) {
                throw new RuntimeException("No hay suficiente stock para el producto: " + saleDTO.getProductname());
            }


            // Crear una venta individual
            RegisterSale registerSale = new RegisterSale(saleDTO.getProductname(), saleDTO.getAmount());
            saleService.makeSale(registerSale);
            
            // Crear una instancia de Sale para añadir a la lista de ventas
            return Sale.builder()
                .product(product)
                .amount(saleDTO.getAmount())
                .price(product.getPrice())
                .totalPrice(product.getPrice() * saleDTO.getAmount())
                .build();
        }).toList();

        // Calcular el precio total de la venta
        double totalPrice = salesList.stream()
            .mapToDouble(Sale::getTotalPrice)
            .sum();



        // Crear la venta principal
        Sales sales = Sales.builder()
            .salesList(salesList)
            .totalPrice(totalPrice)
            .build();

        // Guardar la venta principal en la base de datos
        salesRepository.save(sales);
    }

    // Obtener todas las ventas
    public List<Sales> getAllSales() {
        return salesRepository.findAll();
    }

    //conseguir datos de venta por id
    public Sales getSaleById(Integer id) {
        return salesRepository.findByid(id).orElseThrow(() -> new RuntimeException("No se encontró la venta con id: " + id));
    }
}
