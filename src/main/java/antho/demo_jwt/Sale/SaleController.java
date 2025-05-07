package antho.demo_jwt.Sale;

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
@RequestMapping("/api/v1/sale")
@RequiredArgsConstructor
public class SaleController {

    private final SaleService saleService;

    //ESTE ES UNA SOLA VENTA NO LISTA DE VENTAS
    
    //Obtener todas las ventas
    @GetMapping("/cajero/all")
    public ResponseEntity<List<Sale>> getAllSales() {
        List<Sale> sale = saleService.getAllSales();
        return ResponseEntity.ok(sale);
    }

    //Registrar una venta
    @PostMapping("/cajero/register")
    public ResponseEntity<?> makeSale(@Valid @RequestBody RegisterSale registerSale) {
        saleService.makeSale(registerSale);
        return ResponseEntity.ok("Venta registrada");
    }



}
