package com.example.ejemplothymeleaf.dao.api;



import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.data.repository.CrudRepository;

import com.example.ejemplothymeleaf.model.Persona;
import com.example.ejemplothymeleaf.service.api.PersonaServiceAPI;
import java.lang.reflect.ParameterizedType;

public class PersonaServiceAPITest {

    @Test
    void testInterfaceDeclaration() {
        // Verificar que es una interfaz
        assertTrue(PersonaServiceAPI.class.isInterface());
        
        // Verificar que extiende GenericServiceAPI con los tipos correctos
        Type[] genericInterfaces = PersonaServiceAPI.class.getGenericInterfaces();
        assertEquals(1, genericInterfaces.length);
        assertTrue(genericInterfaces[0].getTypeName().contains("GenericServiceAPI<Persona, Long>"));
    }

    @Test
    void testInheritedMethods() {
        Method[] methods = PersonaServiceAPI.class.getMethods();
        List<String> methodNames = Arrays.stream(methods)
            .map(Method::getName)
            .collect(Collectors.toList()); 
        
        assertTrue(methodNames.contains("save"));
        assertTrue(methodNames.contains("delete"));
        assertTrue(methodNames.contains("get"));
        assertTrue(methodNames.contains("getAll"));
    }

	/*
	 * @Test void testGenericTypeParameters() throws Exception { // Get the actual
	 * interface methods with proper parameter types Method saveMethod =
	 * getMethod("save", Object.class); Method deleteMethod = getMethod("delete",
	 * Object.class); Method getMethod = getMethod("get", Object.class); Method
	 * getAllMethod = getMethod("getAll"); assertAll( () ->
	 * assertEquals(Object.class, saveMethod.getParameterTypes()[0]), () ->
	 * assertEquals(Object.class, saveMethod.getReturnType()), () ->
	 * assertTrue(isGenericType(saveMethod.getGenericParameterTypes()[0],
	 * Persona.class)), () ->
	 * assertTrue(isGenericType(saveMethod.getGenericReturnType(), Persona.class)),
	 * () -> assertEquals(Object.class, deleteMethod.getParameterTypes()[0]), () ->
	 * assertTrue(isGenericType(deleteMethod.getGenericParameterTypes()[0],
	 * Long.class)), () -> assertEquals(Object.class,
	 * getMethod.getParameterTypes()[0]), () -> assertEquals(Object.class,
	 * getMethod.getReturnType()), () ->
	 * assertTrue(isGenericType(getMethod.getGenericParameterTypes()[0],
	 * Long.class)), () ->
	 * assertTrue(isGenericType(getMethod.getGenericReturnType(), Persona.class)),
	 * () -> { Type returnType = getAllMethod.getGenericReturnType();
	 * assertTrue(returnType instanceof ParameterizedType); ParameterizedType pt =
	 * (ParameterizedType) returnType; assertEquals(List.class, pt.getRawType());
	 * assertEquals(Persona.class, pt.getActualTypeArguments()[0]); } ); }
	 */
    
    private Method getMethod(String name, Class<?>... parameterTypes) throws NoSuchMethodException {
        // Get all methods and find the matching one
        for (Method method : PersonaServiceAPI.class.getMethods()) {
            if (method.getName().equals(name)) {
                if (parameterTypes.length == 0 && method.getParameterCount() == 0) {
                    return method;
                }
                if (method.getParameterCount() == parameterTypes.length) {
                    boolean paramsMatch = true;
                    for (int i = 0; i < parameterTypes.length; i++) {
                        if (!method.getParameterTypes()[i].isAssignableFrom(parameterTypes[i])) {
                            paramsMatch = false;
                            break;
                        }
                    }
                    if (paramsMatch) {
                        return method;
                    }
                }
            }
        }
        throw new NoSuchMethodException(name);
    }
    
    
    private boolean isGenericType(Type type, Class<?> expectedClass) {
        if (type instanceof Class) {
            return expectedClass.isAssignableFrom((Class<?>) type);
        } else if (type instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) type;
            return expectedClass.isAssignableFrom((Class<?>) pt.getRawType());
        }
        return false;
    }
    
}
