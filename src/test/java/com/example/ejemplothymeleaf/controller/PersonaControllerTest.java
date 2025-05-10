package com.example.ejemplothymeleaf.controller;


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import com.example.ejemplothymeleaf.model.Persona;
import com.example.ejemplothymeleaf.service.impl.PersonaServiceImpl;


@ExtendWith(MockitoExtension.class)
public class PersonaControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PersonaServiceImpl personaServiceAPI;

    @Mock
    private Model model;

    @InjectMocks
    private PersonaController personaController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(personaController).build();
    }

    @Test
    public void testIndex() throws Exception {
        // Preparar datos de prueba
        Persona persona1;
        persona1 = new Persona();
        persona1.setId(1L);
        persona1.setNombre("Juan");
        persona1.setApellido("Perez");
        
        Persona persona2 = new Persona();
        persona2.setId(2L);
        persona2.setNombre("Maria");
        persona2.setApellido("Gomez");
        
        List<Persona> personas = Arrays.asList(persona1, persona2);

        // Configurar mock
        when(personaServiceAPI.getAll()).thenReturn(personas);

        // Ejecutar y verificar
        mockMvc.perform(get("/"))
               .andExpect(status().isOk())
               .andExpect(view().name("index"))
               .andExpect(model().attribute("list", hasSize(2)));
    }

    @Test
    public void testShowSaveWithId() throws Exception {
        // Preparar datos de prueba
        Long id = 1L;
        Persona persona = new Persona();
        persona.setId(id);
        persona.setNombre("Juan");
        persona.setApellido("Perez");
        // Configurar mock
        when(personaServiceAPI.get(id)).thenReturn(persona);

        // Ejecutar y verificar
        mockMvc.perform(get("/save/{id}", id))
               .andExpect(status().isOk())
               .andExpect(view().name("save"))
               .andExpect(model().attribute("persona", persona));
    }

    @Test
    public void testShowSaveWithoutId() throws Exception {
        // Ejecutar y verificar
        mockMvc.perform(get("/save/0"))
               .andExpect(status().isOk())
               .andExpect(view().name("save"))
               .andExpect(model().attribute("persona", instanceOf(Persona.class)));
    }

    @Test
    public void testSave() throws Exception {
        // Preparar datos de prueba
        Persona persona = new Persona();
        persona.setId(1L);
        persona.setNombre("Juan");
        persona.setApellido("Perez");

        // Ejecutar y verificar
        mockMvc.perform(post("/save")
                .flashAttr("persona", persona))
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrl("/"));

        // Verificar interacción con el servicio
        verify(personaServiceAPI).save(persona);
    }

    @Test
    public void testDelete() throws Exception {
        // Preparar datos de prueba
        Long id = 1L;

        // Ejecutar y verificar
        mockMvc.perform(get("/delete/{id}", id))
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrl("/"));

        // Verificar interacción con el servicio
        verify(personaServiceAPI).delete(id);
    }
}
