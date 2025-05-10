package com.example.ejemplothymelead.commons;


import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.ejemplothymeleaf.commons.GenericServiceAPI;

@ExtendWith(MockitoExtension.class)
public class GenericServiceAPITest {

    @Mock
    private GenericServiceAPI<TestEntity, Long> service;

    private TestEntity testEntity;
    private List<TestEntity> testEntities;

    @BeforeEach
    void setUp() {
        testEntity = new TestEntity(1L, "Test");
        testEntities = Arrays.asList(
            testEntity,
            new TestEntity(2L, "Test 2")
        );
    }

    @Test
    void testSave() {
        when(service.save(any(TestEntity.class))).thenReturn(testEntity);

        TestEntity result = service.save(testEntity);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(service).save(testEntity);
    }

    @Test
    void testDelete() {
        doNothing().when(service).delete(anyLong());

        service.delete(1L);

        verify(service).delete(1L);
    }

    @Test
    void testGet() {
        when(service.get(1L)).thenReturn(testEntity);

        TestEntity result = service.get(1L);

        assertNotNull(result);
        assertEquals("Test", result.getName());
        verify(service).get(1L);
    }

    @Test
    void testGetAll() {
        when(service.getAll()).thenReturn(testEntities);

        List<TestEntity> results = service.getAll();

        assertNotNull(results);
        assertEquals(2, results.size());
        verify(service).getAll();
    }

    // Clase de prueba interna para usar como tipo genérico
    private static class TestEntity {
        private Long id;
        private String name;

        public TestEntity(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        public Long getId() { return id; }
        public String getName() { return name; }
    }
}