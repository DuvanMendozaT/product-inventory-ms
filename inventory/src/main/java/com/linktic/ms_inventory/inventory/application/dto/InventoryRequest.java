package com.linktic.ms_inventory.inventory.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InventoryRequest {

    @JsonProperty("ProductId")
    @NotNull
    Long productId;

    @JsonProperty("Quantity")
    @NotNull
    @Min(0)
    int quantity;
}
