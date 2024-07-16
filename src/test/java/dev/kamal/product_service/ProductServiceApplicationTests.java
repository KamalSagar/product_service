package dev.kamal.product_service;

import dev.kamal.product_service.models.Product;
import dev.kamal.product_service.projections.ProductProjection;
import dev.kamal.product_service.projections.ProductWithIdAndTitle;
import dev.kamal.product_service.repositories.CategoryRepository;
import dev.kamal.product_service.repositories.ProductRepository;
import dev.kamal.product_service.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProductServiceApplicationTests {

    // 3 ways to DI
    // 1. Constructor - use this
    // 2. Setter method
    // 3. Autowired
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;



    @Test
    void contextLoads() {
    }

    @Test
    void testJpaDeclaredJoin(){
        List<Product> products = productRepository.findAllByCategory_Title("new electronics");
        for (Product product : products){
            System.out.println(product.getTitle());
        }
    }

    @Test
    void testHQL() {
        List<Product> products = productRepository.getProductWithCategoryName("new electronics");
        for(Product product : products){
            System.out.println(product.getTitle());
        }
    }

    @Test
    void testSpecificFields() {
        List<String> productTitles = productRepository.someTitleMethod("electronics");
        for(String productTitle : productTitles){
            System.out.println(productTitle);
        }
    }

    @Test
    void testProjections() {
        List<ProductWithIdAndTitle> products = productRepository.someMethod1("electronics");
        for (ProductWithIdAndTitle product : products) {
            System.out.println(product.getId());
            System.out.println(product.getTitle());
        }

        List<ProductProjection> productsProjections = productRepository.someMethod2("electronics");
        for (ProductProjection product : productsProjections) {
            System.out.println(product.getId());
            System.out.println(product.getTitle());
        }
    }

    @Test
    void testNativeSql(){
        Product product = productRepository.someNativeSql(1L);
        System.out.println(product.getTitle());
    }



}
