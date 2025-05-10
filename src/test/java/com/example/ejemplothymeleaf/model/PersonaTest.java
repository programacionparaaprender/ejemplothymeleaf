package com.example.ejemplothymeleaf.model;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class PersonaTest {

    @Test
    void testConstructorVacio() {
        // Act
        Persona persona = new Persona();
        
        // Assert
        assertThat(persona).isNotNull();
        assertThat(persona.getId()).isNull();
        assertThat(persona.getNombre()).isNull();
        assertThat(persona.getApellido()).isNull();
        assertThat(persona.getTelefono()).isNull();
        assertThat(persona.getDireccion()).isNull();
    }

    @Test
    void testConstructorCompleto() {
        // Arrange
        Long id = 1L;
        String nombre = "Juan";
        String apellido = "Pérez";
        String telefono = "555-1234";
        String direccion = "Calle Falsa 123";
        
        // Act
        Persona persona = new Persona(id, nombre, apellido, telefono, direccion);
        
        // Assert
        assertThat(persona).isNotNull();
        assertThat(persona.getId()).isEqualTo(id);
        assertThat(persona.getNombre()).isEqualTo(nombre);
        assertThat(persona.getApellido()).isEqualTo(apellido);
        assertThat(persona.getTelefono()).isEqualTo(telefono);
        assertThat(persona.getDireccion()).isEqualTo(direccion);
    }

    @Test
    void testSettersAndGetters() {
        // Arrange
        Persona persona = new Persona();
        Long id = 2L;
        String nombre = "María";
        String apellido = "Gómez";
        String telefono = "555-5678";
        String direccion = "Avenida Siempreviva 742";
        
        // Act
        persona.setId(id);
        persona.setNombre(nombre);
        persona.setApellido(apellido);
        persona.setTelefono(telefono);
        persona.setDireccion(direccion);
        
        // Assert
        assertThat(persona.getId()).isEqualTo(id);
        assertThat(persona.getNombre()).isEqualTo(nombre);
        assertThat(persona.getApellido()).isEqualTo(apellido);
        assertThat(persona.getTelefono()).isEqualTo(telefono);
        assertThat(persona.getDireccion()).isEqualTo(direccion);
    }

    @Test
    void testEqualsAndHashCode() {
        // Arrange
        Persona persona1 = new Persona(1L, "Juan", "Pérez", "555-1234", "Calle 1");
        Persona persona2 = new Persona(1L, "Juan", "Pérez", "555-1234", "Calle 1");
        Persona persona3 = new Persona(2L, "María", "Gómez", "555-5678", "Calle 2");
        
        // Assert
        assertThat(persona1).isEqualTo(persona2);
        assertThat(persona1).isNotEqualTo(persona3);
        assertThat(persona1.hashCode()).isEqualTo(persona2.hashCode());
        assertThat(persona1.hashCode()).isNotEqualTo(persona3.hashCode());
    }

    @Test
    void testToString() {
        // Arrange
        Persona persona = new Persona(1L, "Juan", "Pérez", "555-1234", "Calle 1");
        
        // Act
        String toStringResult = persona.toString();
        
        // Assert
        assertThat(toStringResult).contains("Juan");
        assertThat(toStringResult).contains("Pérez");
        assertThat(toStringResult).contains("555-1234");
    }
}