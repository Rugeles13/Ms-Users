package com.foodcourt.msusers.domain.usecase;

import com.foodcourt.msusers.domain.api.IOwnerServicePort;
import com.foodcourt.msusers.domain.exceptions.OwnerNotFoundException;
import com.foodcourt.msusers.domain.exceptions.RoleNotFoundException;
import com.foodcourt.msusers.domain.model.OwnerModel;
import com.foodcourt.msusers.domain.model.RoleModel;
import com.foodcourt.msusers.domain.spi.IOwnerPersistancePort;
import com.foodcourt.msusers.domain.spi.IRolePersistancePort;
import com.foodcourt.msusers.domain.validations.ValidationService;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.management.relation.Role;

public class OwnerUseCase implements IOwnerServicePort {


    private final IOwnerPersistancePort ownerPersistancePort;
    private final ValidationService validationService;
    private final IRolePersistancePort rolePersistancePort;
    private final PasswordEncoder passwordEncoder;


    public OwnerUseCase(IOwnerPersistancePort ownerPersistancePort, ValidationService validationService, IRolePersistancePort rolePersistancePort, PasswordEncoder passwordEncoder) {
        this.ownerPersistancePort = ownerPersistancePort;
        this.validationService = validationService;
        this.rolePersistancePort = rolePersistancePort;
        this.passwordEncoder = passwordEncoder;
    }



    @Override
    public OwnerModel saveOwner(OwnerModel ownerModel, String roleName) {

        //El Owner debe ser mayor de edad
        if (!validationService.isAdult(ownerModel.getDateOfBirth())) {
            throw new IllegalArgumentException("Owner must be an adult");
        }

        RoleModel role = rolePersistancePort.getRoleByName(roleName).orElseThrow(
                () -> new RoleNotFoundException("No se encuentra el rol")
        );

        ownerModel.setRole(role);

        String encodedPassword = passwordEncoder.encode(ownerModel.getPassword());
        ownerModel.setPassword(encodedPassword);

        return ownerPersistancePort.saveOwner(ownerModel);
    }

    @Override
    public OwnerModel getOwnerById(Long id) {
        return ownerPersistancePort.getOwnerById(id).orElseThrow(
                () -> new OwnerNotFoundException("El propietario con ese Id no existe")
        );
    }
}
