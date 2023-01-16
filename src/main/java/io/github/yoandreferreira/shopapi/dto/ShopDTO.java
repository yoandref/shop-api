package io.github.yoandreferreira.shopapi.dto;

import io.github.yoandreferreira.shopapi.model.ShopItem;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ShopDTO {
    private String identifier;
    private String status;
    private LocalDate dateShop;
    private List<ShopItemDTO> items;
}
