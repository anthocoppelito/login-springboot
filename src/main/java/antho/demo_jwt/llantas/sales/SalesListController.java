package antho.demo_jwt.llantas.sales;

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
@RequestMapping("/api/v2/sales")
@RequiredArgsConstructor
public class SalesListController {

    private final SalesListService salesListService;

    //todos las ventas
    @GetMapping(value="/cajero/all")
    public ResponseEntity<List<SalesList>> getAllSalesList(){
        List<SalesList> sales = salesListService.getAllSalesList();
        return ResponseEntity.ok(sales);
    }

    //Obtener una venta por id
    @GetMapping("/cajero/sale/{id}")
    public ResponseEntity<SalesList> getSalesListById(@PathVariable Integer id) {
        SalesList sales = salesListService.getSalesListById(id);
        return ResponseEntity.ok(sales);
    }

    //registrar ventalist
    @PostMapping(value="/cajero/register")
    public ResponseEntity<?> registerSalesList(@Valid @RequestBody RegisterSalesList request){
        salesListService.createSaleList(request);
        return ResponseEntity.ok("Venta registrada correctamente");
    }



}
