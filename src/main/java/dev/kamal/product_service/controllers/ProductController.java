package dev.kamal.product_service.controllers;

import dev.kamal.product_service.dtos.ProductResponseDto;
import dev.kamal.product_service.models.Product;
import dev.kamal.product_service.services.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/products/{id}")
    public ProductResponseDto getProductDetails(@PathVariable("id") int productId){
        return productService.getSingleProduct(productId);
    }

    @PostMapping("/products")
    public ProductResponseDto createNewProduct(@RequestBody ProductResponseDto productRequestDto){
          return productService.addProduct(
                  productRequestDto.getTitle(),
                  productRequestDto.getDescription(),
                  productRequestDto.getImage(),
                  productRequestDto.getCategory(),
                  productRequestDto.getPrice()
          );
    }


}
