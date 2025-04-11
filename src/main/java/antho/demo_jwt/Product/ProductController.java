package antho.demo_jwt.Product;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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




}

// esto maneja las rutas de backend
