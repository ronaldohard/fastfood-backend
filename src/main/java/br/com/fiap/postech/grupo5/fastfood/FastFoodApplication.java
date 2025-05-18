package br.com.fiap.postech.grupo5.fastfood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "br.com.fiap.postech.grupo5.fastfood")
@EntityScan(basePackages = "br.com.fiap.postech.grupo5.fastfood.model")
public class FastFoodApplication {
    public static void main(String[] args) {
        SpringApplication.run(FastFoodApplication.class, args);
    }
}
