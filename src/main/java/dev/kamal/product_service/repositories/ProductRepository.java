package dev.kamal.product_service.repositories;

import dev.kamal.product_service.models.Product;
import dev.kamal.product_service.projections.ProductProjection;
import dev.kamal.product_service.projections.ProductWithIdAndTitle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product save(Product product);
    List<Product> findAll();
    Page<Product> findAll(Pageable pageable);
    Product findByIdIs(Long id);
    List<Product> findAllByCategory_Title(String title);

    @Query("select p from Product p where p.category.title = :categoryName")
    List<Product> getProductWithCategoryName(String categoryName);

    @Query("select p.title as title from Product p where p.category.title = :categoryName")
    List<String> someTitleMethod(String categoryName);

    @Query("select p.id as id, p.title as title from Product p where p.category.title = :categoryName")
    List<ProductWithIdAndTitle> someMethod1(String categoryName);

    @Query("select p.id as id, p.title as title from Product p where p.category.title = :categoryName")
    List<ProductProjection> someMethod2(String categoryName);

    // Native SQL query
    @Query(value = "select * from product p where p.id = :id", nativeQuery = true)
    Product someNativeSql(Long id);
}
