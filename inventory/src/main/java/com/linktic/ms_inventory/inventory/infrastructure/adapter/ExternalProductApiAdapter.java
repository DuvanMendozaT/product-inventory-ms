package com.linktic.ms_inventory.inventory.infrastructure.adapter;

import com.linktic.ms_inventory.inventory.application.Constants;
import com.linktic.ms_inventory.inventory.domain.exeption.ExternalClientException;
import com.linktic.ms_inventory.inventory.domain.exeption.ExternalServerException;
import com.linktic.ms_inventory.inventory.domain.model.ProductModel;
import com.linktic.ms_inventory.inventory.domain.port.ExternalProductApiPort;
import com.linktic.ms_inventory.inventory.infrastructure.adapter.dto.ProductResponse;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

@Component
public class ExternalProductApiAdapter implements ExternalProductApiPort {

    private final WebClient webClient;

    public ExternalProductApiAdapter(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("http://product-app:8081").build();
    }

    @Override
    public ProductModel getProductDetails(Long externalId, String apiKey) {
        ProductResponse response = webClient.get()
                .uri("/products/{id}", externalId)
                .header(Constants.HEADER_API_KEY, apiKey)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse ->
                        clientResponse.bodyToMono(String.class)
                                .flatMap(error -> Mono.error(new ExternalClientException(error))))
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse ->
                        clientResponse.bodyToMono(String.class)
                                .flatMap(error -> Mono.error(new ExternalServerException(error))))
                .bodyToMono(ProductResponse.class)
                .timeout(Duration.ofSeconds(3))
                .retryWhen(Retry.fixedDelay(3, Duration.ofMillis(5000)))
                .block();

        if (response == null) throw new ExternalServerException(Constants.EMPTY_RESPONSE);

        return ProductModel.builder()
                .id(response.getProduct().getId())
                .name(response.getProduct().getName())
                .price(response.getProduct().getPrice())
                .build();
    }
}