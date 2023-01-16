package io.github.yoandreferreira.shopapi.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopItemDTO {
    private String productIdentifier;
    private Integer amount;
    private Float price;

}
