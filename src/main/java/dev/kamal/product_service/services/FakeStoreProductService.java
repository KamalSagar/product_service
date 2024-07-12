package dev.kamal.product_service.services;

import dev.kamal.product_service.dtos.FakeStoreDto;
import dev.kamal.product_service.dtos.ProductResponseDto;
import dev.kamal.product_service.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FakeStoreProductService implements ProductService {

    public RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ProductResponseDto getSingleProduct(int productId) {

        FakeStoreDto fakeStoreDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + productId,
                FakeStoreDto.class
        );

        return fakeStoreDto.toProductResponseDto();
    }

    @Override
    public ProductResponseDto addProduct(
            String title,
            String description,
            String imageUrl,
            String category,
            double price) {

        FakeStoreDto fakeStoreDto = new FakeStoreDto();
        fakeStoreDto.setTitle(title);
        fakeStoreDto.setDescription(description);
        fakeStoreDto.setImage(imageUrl);
        fakeStoreDto.setPrice(price);

        FakeStoreDto response = restTemplate.postForObject(
                "https://fakestoreapi.com/products/",
                fakeStoreDto,
                FakeStoreDto.class
        );

        return fakeStoreDto.toProductResponseDto();
    }
}

