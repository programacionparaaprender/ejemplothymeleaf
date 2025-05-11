package com.example.ejemplothymeleaf.service.api;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.ejemplothymeleaf.model.Persona;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class PersonaServiceAPIMockTest {

    @Mock
    private PersonaServiceAPI personaService;

    @Test
    void testSave() {
        Persona persona = new Persona();
        persona.setId(1L);
        persona.setNombre("Juan");
        persona.setApellido("Pérez");
        when(personaService.save(any(Persona.class))).thenReturn(persona);

        Persona saved = personaService.save(persona);

        assertNotNull(saved);
        assertEquals("Juan", saved.getNombre());
        assertEquals("Pérez", saved.getApellido());
    }

    @Test
    void testDelete() {
        doNothing().when(personaService).delete(1L);

        // Llamada al método
        personaService.delete(1L);

        // Verifica que se haya llamado
        verify(personaService, times(1)).delete(1L);
    }

    @Test
    void testGet() {
        Persona persona = new Persona();
        persona.setId(1L);
        persona.setNombre("Maria");
        persona.setApellido("Gomez");
        when(personaService.get(1L)).thenReturn(persona);

        Optional<Persona> result = Optional.of(personaService.get(1L));

        assertTrue(result.isPresent());
        assertEquals("Maria", result.get().getNombre());
    }

    @Test
    void testGetAll() {
        Persona p1 = new Persona();
        p1.setId(1L);
        p1.setNombre("Juan");
        p1.setApellido("Pérez");
        
        Persona p2 = new Persona();
        p2.setId(2L);
        p2.setNombre("Maria");
        p2.setApellido("Gomez");
        
        List<Persona> personas = Arrays.asList(p1, p2);

        when(personaService.getAll()).thenReturn(personas);

        List<Persona> result = personaService.getAll();

        assertEquals(2, result.size());
        assertEquals("Juan", result.get(0).getNombre());
    }
}
