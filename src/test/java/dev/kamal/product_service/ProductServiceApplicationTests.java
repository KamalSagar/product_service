package dev.kamal.product_service;

import dev.kamal.product_service.models.Category;
import dev.kamal.product_service.models.Product;
import dev.kamal.product_service.projections.ProductProjection;
import dev.kamal.product_service.projections.ProductWithIdAndTitle;
import dev.kamal.product_service.repositories.CategoryRepository;
import dev.kamal.product_service.repositories.ProductRepository;
import dev.kamal.product_service.services.ProductService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

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

    @Test
    @Transactional
    void testFetchType(){
        Optional<Category> category = categoryRepository.findById(2L);
        if (category.isPresent()){
            System.out.println(category.get().getTitle());
            List<Product> products = category.get().getProducts();
            for(Product product : products){
                System.out.println(product.getTitle());
            }
        }
    }

    @Test
    @Transactional
    void testFetchMode(){
        List<Category> categories = categoryRepository.findByTitleEndingWith("electronics");
        for (Category category : categories){
            System.out.println(category.getTitle());

            List<Product> products = category.getProducts();
            for(Product product : products){
                System.out.println(product.getTitle());
            }
        }

    }



}
