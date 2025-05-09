package antho.demo_jwt.Product;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    //obtener todos los productos
    @GetMapping(value = "/bodega/all")
    public ResponseEntity<List<ProductDTO>> getAllProducts(){
        List<ProductDTO> productDTO = productService.getAllProducts();
        return ResponseEntity.ok(productDTO);
    }

    //actualizar dato de producto por productname

    //registrar producto
    @PostMapping(value="/bodega/register")
    public ResponseEntity<?> registerProduct(@Valid @RequestBody ProductRegister request){
        productService.registerProduct(request);
        return ResponseEntity.ok("Producto registrado");
    }

    //aumentar el stock de un producto
    @PutMapping(value="/bodega/addstock")
    public ResponseEntity<?> addStock(@Valid @RequestBody ProductStock request){
        productService.addStock(request);
        return ResponseEntity.ok("Stock actualizado");
    }

    //decrementar el stock de un producto
    @PutMapping(value="/bodega/removestock")
    public ResponseEntity<?> removeStock(@Valid @RequestBody ProductStock request){
        productService.removeStock(request);
        return ResponseEntity.ok("Stock actualizado");
    }

    //Verificar si existe el producto
    @GetMapping("/bodega/exists/{productname}")
    public ResponseEntity<Boolean> checkIfProductExists(@PathVariable String productname) {
        boolean exists = productService.productExists(productname);
    return ResponseEntity.ok(exists);
    }

    //recibir productname y cantidad y regrese el precio unitario y el total(precio unitario * cantidad). antes revisa si hay stock
    @GetMapping("/bodega/price/{productname}/{amount}")
    public ResponseEntity<Map<String,Double>> getProductPrice(@PathVariable String productname, @PathVariable Integer amount) {
        Map<String,Double> productPrice = productService.getProductPrice(productname, amount);
        return ResponseEntity.ok(productPrice);
    }




}

// esto maneja las rutas de backend
