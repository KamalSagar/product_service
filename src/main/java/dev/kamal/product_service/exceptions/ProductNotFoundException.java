package dev.kamal.product_service.exceptions;

public class ProductNotFoundException extends Exception{

    public ProductNotFoundException(String message){
        super(message);
    }
}
