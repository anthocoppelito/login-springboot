package antho.demo_jwt.sales;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/sales")
@RequiredArgsConstructor
public class SalesController {

    private final SalesService salesService;

    //todos los productos
    @GetMapping(value="/cajero/all")
    public ResponseEntity<List<Sales>> getAllSales(){
        List<Sales> sales = salesService.getAllSales();
        return ResponseEntity.ok(sales);
    }

    //Obtener una venta por id
    @GetMapping("/cajero/sale/{id}")
    public ResponseEntity<Sales> getSaleById(@PathVariable Integer id) {
        Sales sales = salesService.getSaleById(id);
        return ResponseEntity.ok(sales);
    }


    //registrar producto
    @PostMapping(value="/cajero/register")
    public ResponseEntity<?> registerSale(@Valid @RequestBody RegisterSales request){
        salesService.createSale(request);
        return ResponseEntity.ok("Venta registrada correctamente");
    }

}
