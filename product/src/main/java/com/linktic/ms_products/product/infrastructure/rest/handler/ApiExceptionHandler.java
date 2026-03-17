package com.linktic.ms_products.product.infrastructure.rest.handler;

import com.linktic.ms_products.product.application.Constants;
import com.linktic.ms_products.product.application.dto.api.JsonApiData;
import com.linktic.ms_products.product.application.dto.api.JsonApiResponse;
import com.linktic.ms_products.product.domain.exeption.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<JsonApiResponse> handleProductNotFound(
            ProductNotFoundException ex
    ) {
        JsonApiData body = JsonApiData.builder()
                .status(Constants.OPERATION_FAILED)
                .message(ex.getMessage())
                .build();

        JsonApiResponse response = JsonApiResponse.builder()
                .data(body)
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}
