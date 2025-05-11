package com.example.ejemplothymeleaf.service.api;


import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import com.example.ejemplothymeleaf.commons.GenericServiceAPI;
import com.example.ejemplothymeleaf.model.Persona;

class PersonaServiceAPITest {

    @Test
    void testPersonaServiceAPI_ShouldExtendGenericServiceAPI() {
        
        Type[] genericInterfaces = PersonaServiceAPI.class.getGenericInterfaces();
        assertEquals(1, genericInterfaces.length, "Debe extender una interfaz");
        
        ParameterizedType pt = (ParameterizedType) genericInterfaces[0];
        assertEquals(GenericServiceAPI.class, pt.getRawType(), "Debe extender GenericServiceAPI");
        assertEquals(Persona.class, pt.getActualTypeArguments()[0], "Primer tipo genérico debe ser Persona");
        assertEquals(Long.class, pt.getActualTypeArguments()[1], "Segundo tipo genérico debe ser Long");
    }

    @Test
    void testPersonaServiceAPI_ShouldDeclareCorrectMethods() {

        Method[] methods = PersonaServiceAPI.class.getMethods();

        assertAll(
            () -> assertTrue(Arrays.stream(methods)
                    .anyMatch(m -> m.getName().equals("save")
                            && m.getParameterTypes()[0].equals(Persona.class))),
            () -> assertTrue(Arrays.stream(methods)
                    .anyMatch(m -> m.getName().equals("delete")
                            && m.getParameterTypes()[0].equals(Long.class))),
            () -> assertTrue(Arrays.stream(methods)
                    .anyMatch(m -> m.getName().equals("get")
                            && m.getParameterTypes()[0].equals(Long.class))),
            () -> assertTrue(Arrays.stream(methods)
                    .anyMatch(m -> m.getName().equals("getAll")))
        );
    }

}
