package antho.demo_jwt.llantas.sales;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleOneRepository extends JpaRepository<SaleOne, Integer> {
    // JpaRepository provides basic CRUD operations
    // You can add custom query methods if needed

}
