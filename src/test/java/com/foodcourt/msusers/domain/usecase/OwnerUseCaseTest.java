package com.foodcourt.msusers.domain.usecase;

import com.foodcourt.msusers.domain.api.IOwnerServicePort;
import com.foodcourt.msusers.domain.exceptions.RoleNotFoundException;
import com.foodcourt.msusers.domain.model.OwnerModel;
import com.foodcourt.msusers.domain.model.RoleModel;
import com.foodcourt.msusers.domain.spi.IOwnerPersistancePort;
import com.foodcourt.msusers.domain.spi.IRolePersistancePort;
import com.foodcourt.msusers.domain.validations.ValidationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class OwnerUseCaseTest {

    @Mock
    private IOwnerPersistancePort ownerPersistencePort;

    @Mock
    private IRolePersistancePort rolePersistencePort;

    @Mock
    private ValidationService validationService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private OwnerUseCase ownerUseCase;

    private OwnerModel ownerModel;
    private RoleModel roleModel;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Inicializar el OwnerModel de prueba
        ownerModel = new OwnerModel();
        ownerModel.setName("John");
        ownerModel.setLastName("Doe");
        ownerModel.setEmail("john.doe@example.com");
        ownerModel.setPassword("plainPassword");
        ownerModel.setDateOfBirth(new Date());  // Asume que es mayor de edad

        // Inicializar el RoleModel de prueba
        roleModel = new RoleModel();
        roleModel.setId(1L);
        roleModel.setName("OWNER");
    }

    @Test
    void saveOwner_shouldEncryptPasswordAndSaveOwner() {
        // Configurar mocks
        when(validationService.isAdult(any(Date.class))).thenReturn(true);
        when(rolePersistencePort.getRoleByName(anyString())).thenReturn(Optional.of(roleModel));
        when(passwordEncoder.encode(anyString())).thenReturn("encryptedPassword");
        when(ownerPersistencePort.saveOwner(any(OwnerModel.class))).thenReturn(ownerModel);

        // Ejecutar el caso de uso
        OwnerModel savedOwner = ownerUseCase.saveOwner(ownerModel, "OWNER");

        // Verificar que se encriptó la contraseña
        assertThat(savedOwner.getPassword()).isEqualTo("encryptedPassword");

        // Verificar que se llamó al método de guardar
        verify(ownerPersistencePort).saveOwner(ownerModel);
    }

    @Test
    void saveOwner_shouldThrowExceptionIfNotAdult() {
        // Configurar mocks
        when(validationService.isAdult(any(Date.class))).thenReturn(false);

        // Ejecutar el caso de uso y verificar que lanza la excepción
        assertThrows(IllegalArgumentException.class, () -> ownerUseCase.saveOwner(ownerModel, "OWNER"));

        // Verificar que no se intentó guardar al Owner
        verify(ownerPersistencePort, never()).saveOwner(any(OwnerModel.class));
    }

    @Test
    void saveOwner_shouldThrowExceptionIfRoleNotFound() {
        // Configurar mocks
        when(validationService.isAdult(any(Date.class))).thenReturn(true);
        when(rolePersistencePort.getRoleByName(anyString())).thenReturn(Optional.empty());

        // Ejecutar el caso de uso y verificar que lanza la excepción de rol no encontrado
        assertThrows(RoleNotFoundException.class, () -> ownerUseCase.saveOwner(ownerModel, "OWNER"));

        // Verificar que no se intentó guardar al Owner
        verify(ownerPersistencePort, never()).saveOwner(any(OwnerModel.class));
    }

}