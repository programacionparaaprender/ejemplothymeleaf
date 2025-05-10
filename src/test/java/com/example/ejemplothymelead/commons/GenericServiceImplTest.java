package com.example.ejemplothymelead.commons;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.repository.CrudRepository;

import com.example.ejemplothymeleaf.commons.GenericServiceImpl;

@ExtendWith(MockitoExtension.class)
public class GenericServiceImplTest {

    @Mock
    private CrudRepository<TestEntity, Long> crudRepository;

    @InjectMocks
    private TestGenericServiceImpl genericService;

    private TestEntity testEntity;
    private List<TestEntity> testEntities;

    // Implementación concreta para testing
    private static class TestGenericServiceImpl extends GenericServiceImpl<TestEntity, Long> {
        private CrudRepository<TestEntity, Long> repository;

        @Override
        public CrudRepository<TestEntity, Long> getDao() {
            return repository;
        }

        public void setRepository(CrudRepository<TestEntity, Long> repository) {
            this.repository = repository;
        }
    }

    // Clase de entidad para testing
    private static class TestEntity implements Serializable {
        private Long id;
        private String name;

        public TestEntity(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        public Long getId() { return id; }
        public String getName() { return name; }
    }

    @BeforeEach
    void setUp() {
        testEntity = new TestEntity(1L, "Test Entity");
        testEntities = new ArrayList<>();
        testEntities.add(testEntity);
        testEntities.add(new TestEntity(2L, "Test Entity 2"));

        genericService.setRepository(crudRepository);
    }

    @Test
    void testSave() {
        when(crudRepository.save(testEntity)).thenReturn(testEntity);

        TestEntity result = genericService.save(testEntity);

        assertNotNull(result);
        assertEquals(testEntity, result);
        verify(crudRepository).save(testEntity);
    }

    @Test
    void testDelete() {
        doNothing().when(crudRepository).deleteById(1L);

        genericService.delete(1L);

        verify(crudRepository).deleteById(1L);
    }

    @Test
    void testGetWhenEntityExists() {
        when(crudRepository.findById(1L)).thenReturn(Optional.of(testEntity));

        TestEntity result = genericService.get(1L);

        assertNotNull(result);
        assertEquals(testEntity, result);
        verify(crudRepository).findById(1L);
    }

    @Test
    void testGetWhenEntityNotExists() {
        when(crudRepository.findById(99L)).thenReturn(Optional.empty());

        TestEntity result = genericService.get(99L);

        assertNull(result);
        verify(crudRepository).findById(99L);
    }

    @Test
    void testGetAll() {
        Iterable<TestEntity> iterable = testEntities;
        when(crudRepository.findAll()).thenReturn(iterable);

        List<TestEntity> result = genericService.getAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.containsAll(testEntities));
        verify(crudRepository).findAll();
    }

    @Test
    void testGetAllWhenEmpty() {
        when(crudRepository.findAll()).thenReturn(new ArrayList<>());

        List<TestEntity> result = genericService.getAll();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(crudRepository).findAll();
    }
}
