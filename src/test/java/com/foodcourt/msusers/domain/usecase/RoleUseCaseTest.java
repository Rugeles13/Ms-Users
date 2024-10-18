package com.foodcourt.msusers.domain.usecase;

import com.foodcourt.msusers.domain.exceptions.RoleNotFoundException;
import com.foodcourt.msusers.domain.model.RoleModel;
import com.foodcourt.msusers.domain.spi.IRolePersistancePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

class RoleUseCaseTest {

    @Mock
    private IRolePersistancePort rolePersistancePort;

    @InjectMocks
    private RoleUseCase roleUseCase;

    private RoleModel roleModel;

    @BeforeEach
    void setUp() {
        // Inicializar los mocks
        MockitoAnnotations.openMocks(this);

        // Crear un RoleModel de prueba
        roleModel = new RoleModel();
        roleModel.setId(1L);
        roleModel.setName("OWNER");
    }

    @Test
    void getRoleByName_ShouldReturnRole_WhenRoleExists() {
        // Configurar el comportamiento del mock
        when(rolePersistancePort.getRoleByName("OWNER")).thenReturn(Optional.of(roleModel));

        // Ejecutar el método que estamos probando
        RoleModel result = roleUseCase.getRoleByName("OWNER");

        // Verificar el resultado
        assertNotNull(result);
        assertEquals("OWNER", result.getName());
        verify(rolePersistancePort, times(1)).getRoleByName("OWNER");
    }

    @Test
    void getRoleByName_ShouldThrowException_WhenRoleNotFound() {
        // Configurar el comportamiento del mock para retornar vacío
        when(rolePersistancePort.getRoleByName("INVALID_ROLE")).thenReturn(Optional.empty());

        // Ejecutar y verificar que se lanza la excepción esperada
        RoleNotFoundException exception = assertThrows(
                RoleNotFoundException.class,
                () -> roleUseCase.getRoleByName("INVALID_ROLE")
        );

        // Verificar el mensaje de la excepción
        assertEquals("No se encontro el rol", exception.getMessage());
        verify(rolePersistancePort, times(1)).getRoleByName("INVALID_ROLE");
    }
}