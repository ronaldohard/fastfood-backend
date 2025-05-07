package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "app.domain.model")
public class FastFoodApplication {
    public static void main(String[] args) {
        SpringApplication.run(FastFoodApplication.class, args);
    }
}
