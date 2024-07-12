package dev.kamal.product_service.services;

import dev.kamal.product_service.dtos.FakeStoreDto;
import dev.kamal.product_service.dtos.ProductResponseDto;
import dev.kamal.product_service.models.Product;

public interface ProductService {

    public ProductResponseDto getSingleProduct(int productId);

    public ProductResponseDto addProduct(
            String title,
            String description,
            String imageUrl,
            String category,
            double price);
}
