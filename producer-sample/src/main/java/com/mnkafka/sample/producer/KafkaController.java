package com.mnkafka.sample.producer;

import javax.inject.Inject;

import com.mnkafka.sample.domain.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micronaut.http.annotation.*;

@SuppressWarnings("SpellCheckingInspection")
@Controller("/products")
public class KafkaController {
    private static final String kek = "asdasdasd";

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaController.class);

    @Inject
    private ProductClient productClient;

    @Post
    public String send(@Body Product product) {
        String message = String.format("Message sending to partition: %s, name: %s", product.getBrand(), product.getName());
        LOGGER.debug(message);
//        productClient.sendProduct(product.getBrand(), product);
        productClient.sendProduct("test-products", product.getBrand(), product);
        productClient.sendProduct("kekekekekek", product.getBrand(), product);
        productClient.sendProduct("aaaaa", product.getBrand(), product);
        productClient.sendProduct("bbbb", product.getBrand(), product);
        productClient.sendProduct((("cccc")), product.getBrand(), product);
        productClient.sendProduct("cccc" + "dddd", product.getBrand(), product);
        productClient.sendProduct(kek, product.getBrand(), product);
        //TODO: reference in user's interface client
        productClient.sendProduct("", product.getBrand(), product);
        return message;
    }

    @Get
    public String index(){
        return "something";
    }
}
