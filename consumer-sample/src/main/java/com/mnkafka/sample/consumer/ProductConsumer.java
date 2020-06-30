package com.mnkafka.sample.consumer;

import com.mnkafka.sample.consumer.jpa.ProductDao;
import com.mnkafka.sample.domain.Product;
import io.micronaut.messaging.annotation.SendTo;
import io.micronaut.configuration.kafka.annotation.*;

@KafkaListener(offsetReset = OffsetReset.EARLIEST)
public class ProductConsumer {

    private final ProductRepository productRepository;

    public ProductConsumer(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    //TODO: completion + gutter
    @Topic("test-products")
    @Topic("")
    @SendTo("test-product-quantities")
    public Integer receive(
            @KafkaKey String brand,
            Product product) {
        System.out.println("Got Product - " + product.getName() + " by " + brand);

        ProductDao productDao = new ProductDao().setName(product.getName())
                .setSku(product.getSku())
                .setBrand(product.getBrand())
                .setQuantity(product.getQuantity());
        productRepository.save(productDao);

        return product.getQuantity();
    }

    @Topic("test-product-quantities")
    @SendTo("${datasources.default.driverClassName}")
    public void updateQuantity(
            @KafkaKey String brand,
            Integer quantity) {
        System.out.println("Added " + quantity + " to the brand: " + brand);
    }
}

