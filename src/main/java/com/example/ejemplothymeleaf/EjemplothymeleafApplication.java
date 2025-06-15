package com.example.ejemplothymeleaf;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.ejemplothymeleaf.model.Product;
import com.example.ejemplothymeleaf.repository.ProductRepository;

@SpringBootApplication
public class EjemplothymeleafApplication {

	public static void main(String[] args) {
		SpringApplication.run(EjemplothymeleafApplication.class, args);
	}

	@Bean
    public CommandLineRunner loadData(ProductRepository repository) {
        return args -> {
            // Datos de ejemplo
            repository.save(new Product("Laptop", 999.99, 10));
            repository.save(new Product("Mouse", 19.99, 50));
            repository.save(new Product("Keyboard", 49.99, 30));
            repository.save(new Product("Monitor", 199.99, 15));
        };
    }
}
