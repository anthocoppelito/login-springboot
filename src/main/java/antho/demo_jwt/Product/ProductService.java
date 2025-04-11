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

}
//esto maneja la logica de negocio de los productos

