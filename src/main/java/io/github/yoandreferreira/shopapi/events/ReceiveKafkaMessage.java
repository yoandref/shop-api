package io.github.yoandreferreira.shopapi.events;

import io.github.yoandreferreira.shopapi.dto.ShopDTO;
import io.github.yoandreferreira.shopapi.model.Shop;
import io.github.yoandreferreira.shopapi.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReceiveKafkaMessage {

    private final ShopRepository shopRepository;
    private static final String SHOP_TOPIC_EVENT_NAME = "SHOP_TOPIC_EVENT";

    @KafkaListener(topics = SHOP_TOPIC_EVENT_NAME, groupId = "group")
    public void listenShopEvents(ShopDTO shopDTO) {
        try {
            log.info("Status da compra recebida no tópico: {}.", shopDTO.getIdentifier());
            Shop shop = this.shopRepository.findByIdentifier(shopDTO.getIdentifier());
            shop.setStatus(shopDTO.getStatus());
            shopRepository.save(shop);
        } catch (Exception e) {
            log.info("Erro no processamento da compra {}.", shopDTO.getIdentifier());
        }
    }

}
