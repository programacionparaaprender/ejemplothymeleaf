package com.example.ejemplothymeleaf;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EjemplothymeleafApplicationTest {

    @Test
    void contextLoads() {
    	//requiere que se este ejecutando la base de datos
        assertDoesNotThrow(() -> EjemplothymeleafApplication.main(new String[]{}));
    }
}