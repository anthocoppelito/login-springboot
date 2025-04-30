package antho.demo_jwt.sales;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;



public interface SalesRepository extends JpaRepository<Sales, Integer> {
    Optional<Sales> findByid(Integer id);
    

}
