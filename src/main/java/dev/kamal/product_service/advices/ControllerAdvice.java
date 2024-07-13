package dev.kamal.product_service.advices;

import dev.kamal.product_service.dtos.ErrorDto;
import dev.kamal.product_service.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    // Add Exception Handler
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDto> handleProductNotFoundException (ProductNotFoundException productNotFoundException){
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(productNotFoundException.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }
}
