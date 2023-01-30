package io.github.yoandreferreira.shopapi.dto;

import io.github.yoandreferreira.shopapi.model.Shop;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ShopDTO {

    private String identifier;
    private String status;
    private String buyerIdentifier;
    private LocalDate dateShop;
    private List<ShopItemDTO> items;

    public static ShopDTO convert(Shop shop) {
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setIdentifier(shop.getIdentifier());
        shopDTO.setBuyerIdentifier(shop.getBuyerIdentifier());
        shopDTO.setDateShop(shop.getDateShop());
        shopDTO.setStatus(shop.getStatus());
        shopDTO.setItems(shop.getItems().stream().map(ShopItemDTO::convert).collect(Collectors.toList()));
        return shopDTO;
    }
}
