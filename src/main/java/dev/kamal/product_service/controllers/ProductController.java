package dev.kamal.product_service.controllers;

import dev.kamal.product_service.authCommons.AuthenticationCommons;
import dev.kamal.product_service.dtos.ErrorDto;
import dev.kamal.product_service.dtos.ProductRequestDto;
import dev.kamal.product_service.dtos.ProductResponseDto;
import dev.kamal.product_service.dtos.UserDto;
import dev.kamal.product_service.exceptions.ProductNotFoundException;
import dev.kamal.product_service.models.Product;
import dev.kamal.product_service.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;
    private ModelMapper modelMapper;
    private AuthenticationCommons authenticationCommons;

    public ProductController(@Qualifier("selfProductService") ProductService productService,
                             ModelMapper modelMapper,
                             AuthenticationCommons authenticationCommons){
        this.modelMapper = modelMapper;
        this.productService = productService;
        this.authenticationCommons = authenticationCommons;
    }

    // e.g: localhost:8080/products/5
    @GetMapping("{id}")
    public ResponseEntity<ProductResponseDto> getProductDetails(@PathVariable("id") Long productId,
                                                @RequestHeader String authenticationToken)
        throws ProductNotFoundException {
            // In order to make this service authenticated, we can pass token
            // in the input parameter and then we'll have to validate the token
            // from UserService

            UserDto userDto = authenticationCommons.validateToken(authenticationToken);

            ResponseEntity<ProductResponseDto> response = null;

            if(userDto == null){
                response = new ResponseEntity<>(
                        null,
                        HttpStatus.UNAUTHORIZED
                );
                return response;
            }

            Product product = productService.getSingleProduct(productId);
            ProductResponseDto productResponseDto = convertToProductResponseDto(product);
            response = new ResponseEntity<>(
                    productResponseDto,
                    HttpStatus.OK
            );
            return response;
        }

//    @GetMapping("/products/all")
//    public List<ProductResponseDto> getAllProducts(){
//        List<Product> products = productService.getAllProducts();
//        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
//        for(Product product : products){
//            productResponseDtos.add(convertToProductResponseDto(product));
//        }
//        return productResponseDtos;
//    }

    @GetMapping()
    public ResponseEntity<List<ProductResponseDto>> getAllProducts(
            @RequestParam("pageNumber") int pageNumber,
            @RequestParam("pageSize") int pageSize,
            @RequestParam("sortBy") String sortParam){
        Page<Product> products = productService.getAllProducts(pageNumber, pageSize, sortParam);
        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
        products.forEach(product -> productResponseDtos.add(convertToProductResponseDto(product)));
        return new ResponseEntity<>(productResponseDtos, HttpStatus.OK);
    }


    @PostMapping()
    public ResponseEntity<ProductResponseDto> createNewProduct(@RequestBody ProductResponseDto productRequestDto){
          Product product = productService.addProduct(
                  productRequestDto.getTitle(),
                  productRequestDto.getDescription(),
                  productRequestDto.getImageUrl(),
                  productRequestDto.getCategory(),
                  productRequestDto.getPrice()
          );

           // return convertToProductResponseDto(product);
          ProductResponseDto productResponseDto = convertToProductResponseDto(product);
          return new ResponseEntity<>(productResponseDto, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ProductResponseDto> deleteProduct(@PathVariable("id") long productId)
    throws ProductNotFoundException {
            Product product = productService.deleteProduct(productId);
            ProductResponseDto productResponseDto = convertToProductResponseDto(product);
            return new ResponseEntity<>(productResponseDto, HttpStatus.OK);
    }


    @PatchMapping("{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable("id") Long productId,
                                                            @RequestBody ProductRequestDto productRequestDto)
            throws ProductNotFoundException {
        Product product = productService.updateProduct(productId,
                productRequestDto.getTitle(),
                productRequestDto.getDescription(),
                productRequestDto.getImageUrl(),
                productRequestDto.getCategory(),
                productRequestDto.getPrice() );
        ProductResponseDto productResponseDto = convertToProductResponseDto(product);
        return new ResponseEntity<>(productResponseDto, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<ProductResponseDto> replaceProduct(@PathVariable("id") Long productId,
                                                             @RequestBody ProductRequestDto productRequestDto)
            throws ProductNotFoundException {
        Product product = productService.replaceProduct(productId,
                productRequestDto.getTitle(),
                productRequestDto.getDescription(),
                productRequestDto.getImageUrl(),
                productRequestDto.getCategory(),
                productRequestDto.getPrice() );
        ProductResponseDto productResponseDto = convertToProductResponseDto(product);
        return new ResponseEntity<>(productResponseDto, HttpStatus.OK);
    }

    private ProductResponseDto convertToProductResponseDto(Product product){
        String categoryTitle = product.getCategory().getTitle();
        ProductResponseDto productResponseDto = modelMapper.map(product, ProductResponseDto.class);
        productResponseDto.setCategory(categoryTitle);
        return productResponseDto;
    }

//    // Add Exception Handler
//    @ExceptionHandler(ProductNotFoundException.class)
//    public ResponseEntity<ErrorDto> handleProductNotFoundException (ProductNotFoundException productNotFoundException){
//            ErrorDto errorDto = new ErrorDto();
//            errorDto.setMessage(productNotFoundException.getMessage());
//            return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
//    }


}
