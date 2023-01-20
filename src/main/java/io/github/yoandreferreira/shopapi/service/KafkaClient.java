package io.github.yoandreferreira.shopapi.service;

import io.github.yoandreferreira.shopapi.dto.ShopDTO;
import org.springframework.stereotype.Service;

@Service
public interface KafkaClient {
    void sendMessage(ShopDTO msg);
}
