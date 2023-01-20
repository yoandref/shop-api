package io.github.yoandreferreira.shopapi.events;

import io.github.yoandreferreira.shopapi.dto.ShopDTO;
import io.github.yoandreferreira.shopapi.service.KafkaClient;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaClientImpl implements KafkaClient {

    private final KafkaTemplate<String, ShopDTO> kafkaTemplate;
    private static final String SHOP_TOPIC_NAME = "SHOP_TOPIC";

    @Override
    public void sendMessage(ShopDTO msg) {
        this.kafkaTemplate.send(SHOP_TOPIC_NAME, msg);
    }
}
