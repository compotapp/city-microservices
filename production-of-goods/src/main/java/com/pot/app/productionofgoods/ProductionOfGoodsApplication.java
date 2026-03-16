package com.pot.app.productionofgoods;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProductionOfGoodsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductionOfGoodsApplication.class, args);
    }

}
