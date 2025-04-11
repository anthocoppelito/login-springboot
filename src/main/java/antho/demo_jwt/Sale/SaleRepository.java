package antho.demo_jwt.Sale;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Integer> {
    // JpaRepository provides basic CRUD operations
    // You can add custom query methods if needed

}
