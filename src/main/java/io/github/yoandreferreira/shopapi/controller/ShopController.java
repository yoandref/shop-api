package io.github.yoandreferreira.shopapi.controller;

import io.github.yoandreferreira.shopapi.dto.ShopDTO;
import io.github.yoandreferreira.shopapi.model.Shop;
import io.github.yoandreferreira.shopapi.model.ShopItem;
import io.github.yoandreferreira.shopapi.repository.ShopRepository;
import io.github.yoandreferreira.shopapi.service.KafkaClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/shop")
@RequiredArgsConstructor
public class ShopController {

    private final ShopRepository shopRepository;
    private final KafkaClient kafkaClient;

    @GetMapping
    public List<ShopDTO> getShop() {
        return this.shopRepository.findAll().stream().map(ShopDTO::convert).collect(Collectors.toList());
    }

    @PostMapping
    public ShopDTO saveShop(@RequestBody ShopDTO shopDTO) {
        shopDTO.setIdentifier(UUID.randomUUID().toString());
        shopDTO.setDateShop(LocalDate.now());
        shopDTO.setStatus("PENDING");

        Shop shop = Shop.convert(shopDTO);
        for(ShopItem shopItem : shop.getItems()) {
            shopItem.setShop(shop);
        }
        shopDTO = ShopDTO.convert(shopRepository.save(shop));
        kafkaClient.sendMessage(shopDTO);
        return shopDTO;
    }

}
