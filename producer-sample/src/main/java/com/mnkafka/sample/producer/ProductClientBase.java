package com.mnkafka.sample.producer;

import com.mnkafka.sample.domain.Product;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.micronaut.messaging.annotation.Body;

public interface ProductClientBase {
    void sendProduct(@Topic String topic, @KafkaKey String brand, @Body Product product);
}
