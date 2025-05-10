package com.example.ejemplothymeleaf.service.impl;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.repository.CrudRepository;

import com.example.ejemplothymeleaf.dao.api.PersonaDaoApi;
import com.example.ejemplothymeleaf.model.Persona;

@ExtendWith(MockitoExtension.class)
public class PersonaServiceImplTest {

    @Mock
    private PersonaDaoApi personaDaoApi;

    @InjectMocks
    private PersonaServiceImpl personaService;

    private Persona persona1;
    private Persona persona2;
    private List<Persona> personas;

    @BeforeEach
    void setUp() {
        persona1 = new Persona();
        persona1.setId(1L);
        persona1.setNombre("Juan");
        persona1.setApellido("Pérez");

        persona2 = new Persona();
        persona2.setId(2L);
        persona2.setNombre("María");
        persona2.setApellido("Gómez");

        personas = Arrays.asList(persona1, persona2);
    }

    @Test
    void testGetDao() {
        CrudRepository<Persona, Long> dao = personaService.getDao();
        assertNotNull(dao);
        assertEquals(personaDaoApi, dao);
    }

    @Test
    void testSave() {
        when(personaDaoApi.save(persona1)).thenReturn(persona1);

        Persona result = personaService.save(persona1);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Juan", result.getNombre());
        verify(personaDaoApi).save(persona1);
    }

    @Test
    void testDelete() {
        doNothing().when(personaDaoApi).deleteById(1L);

        personaService.delete(1L);

        verify(personaDaoApi).deleteById(1L);
    }

    @Test
    void testGetWhenExists() {
        when(personaDaoApi.findById(1L)).thenReturn(Optional.of(persona1));

        Persona result = personaService.get(1L);

        assertNotNull(result);
        assertEquals("Juan", result.getNombre());
        verify(personaDaoApi).findById(1L);
    }

    @Test
    void testGetWhenNotExists() {
        when(personaDaoApi.findById(99L)).thenReturn(Optional.empty());

        Persona result = personaService.get(99L);

        assertNull(result);
        verify(personaDaoApi).findById(99L);
    }

    @Test
    void testGetAll() {
        when(personaDaoApi.findAll()).thenReturn(personas);

        List<Persona> result = personaService.getAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.containsAll(personas));
        verify(personaDaoApi).findAll();
    }

    @Test
    void testGetAllWhenEmpty() {
        when(personaDaoApi.findAll()).thenReturn(List.of());

        List<Persona> result = personaService.getAll();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(personaDaoApi).findAll();
    }
}
