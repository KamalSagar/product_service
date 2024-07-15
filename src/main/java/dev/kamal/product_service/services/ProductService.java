package dev.kamal.product_service.services;

import dev.kamal.product_service.dtos.FakeStoreDto;
import dev.kamal.product_service.dtos.ProductResponseDto;
import dev.kamal.product_service.exceptions.ProductNotFoundException;
import dev.kamal.product_service.models.Product;
import java.util.List;

public interface ProductService {

    public Product getSingleProduct(Long productId) throws ProductNotFoundException;
    public List<Product> getAllProducts();
    public Product addProduct(
            String title,
            String description,
            String imageUrl,
            String category,
            double price);
    public Product deleteProduct(Long productId) throws ProductNotFoundException;

    public Product updateProduct(Long productId,
                                 String title,
                                 String description,
                                 String imageUrl,
                                 String category,
                                 double price) throws ProductNotFoundException;

    public Product replaceProduct(Long productId,
                                  String title,
                                  String description,
                                  String imageUrl,
                                  String category,
                                  double price) throws ProductNotFoundException;
}
