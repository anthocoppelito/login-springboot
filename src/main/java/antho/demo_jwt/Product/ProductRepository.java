package antho.demo_jwt.Product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    // JpaRepository already provides methods for CRUD operations
    // You can define custom queries here if needed

}


//interfaz de repositorio de productos
//hereda de jpa repository para poder usar los metodos de crud