package antho.demo_jwt.Product;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public void registerProduct(@Valid ProductRegister productRegister){

        Product product = Product.builder()
                .productname(productRegister.getProductname())
                .description(productRegister.getDescription())
                .category(productRegister.getCategory())
                .image(productRegister.getImage())
                .price(productRegister.getPrice())
                .stock(productRegister.getStock())
                .build();
        // Guardar producto en la base de datos
        productRepository.save(product);

    }

    public void updateProduct(ProductRegister productRegister) {
        Product product = Product.builder()
                .productname(productRegister.getProductname())
                .description(productRegister.getDescription())
                .category(productRegister.getCategory())
                .image(productRegister.getImage())
                .price(productRegister.getPrice())
                .stock(productRegister.getStock())
                .build();
        // Actualizar producto en la base de datos
        productRepository.save(product);
    }

    public void deleteProduct(Integer id) {
        // Eliminar producto de la base de datos
        productRepository.deleteById(id);
    }

    public boolean productExists(String productname) {
        // Verifica si el producto existe
        return productRepository.findByProductname(productname).isPresent();
    }

    //verifica cuanto stock del producto hay
    public Integer checkStock(String productname) {
        return productRepository.findByProductname(productname)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"))
                .getStock();

        
    }

    //verifica si el producto tiene cierto stock. si hay, devuelve true
    public boolean checkStock(String productname, Integer amount) {
        // Verifica el stock del producto
        Product product = productRepository.findByProductname(productname).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        if (product.getStock() >= amount) {
            return true;
        } else {
            throw new RuntimeException("No hay stock disponible");
        }
    }

    //disminuir stock del producto
    public void removeStock(ProductStock productStock) {
        // Disminuye el stock del producto
        Product product = productRepository.findByProductname(productStock.getProductname()).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        if (product.getStock() >= productStock.getStock()) {
            product.setStock(product.getStock() - productStock.getStock());
            productRepository.save(product);
        } else {
            throw new RuntimeException("No hay stock disponible");
        }
    }

    //aumentar stock del producto
    public void addStock(ProductStock productStock) {
        // Aumenta el stock del producto
        Product product = productRepository.findByProductname(productStock.getProductname()).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        //actualiza el stock sumando stock actual con stock por agregar
        product.setStock(product.getStock() + productStock.getStock());
        productRepository.save(product);
    }

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(product -> new ProductDTO(
                        product.getId(),
                        product.getProductname(),
                        product.getDescription(),
                        product.getCategory(),
                        product.getImage(),
                        product.getPrice(),
                        product.getStock()
                ))
                .collect(Collectors.toList());
    }

    //obtener un solo producto por productname
    public ProductDTO getProductByName(String productname) {
        Product product = productRepository.findByProductname(productname)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        return new ProductDTO(
                product.getId(),
                product.getProductname(),
                product.getDescription(),
                product.getCategory(),
                product.getImage(),
                product.getPrice(),
                product.getStock()
        );
    }

    public Product getProductEntityByName(String productName) {
        return productRepository.findByProductname(productName)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    
}
//esto maneja la logica de negocio de los productos

