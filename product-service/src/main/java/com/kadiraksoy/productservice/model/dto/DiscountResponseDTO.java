package com.kadiraksoy.productservice.model.dto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class DiscountResponseDTO {
    private float newPrice;
    private float oldPrice;
    private String code;
}