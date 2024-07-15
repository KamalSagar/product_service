package dev.kamal.product_service.repositories;

import dev.kamal.product_service.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product save(Product product);
    List<Product> findAll();
    Product findByIdIs(Long id);
}
