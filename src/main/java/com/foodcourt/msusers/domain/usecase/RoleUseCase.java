package com.foodcourt.msusers.domain.usecase;

import com.foodcourt.msusers.domain.api.IRoleServicePort;
import com.foodcourt.msusers.domain.exceptions.RoleNotFoundException;
import com.foodcourt.msusers.domain.model.RoleModel;
import com.foodcourt.msusers.domain.spi.IRolePersistancePort;


public class RoleUseCase implements IRoleServicePort {

    private final IRolePersistancePort rolePersistancePort;

    public RoleUseCase(IRolePersistancePort rolePersistancePort) {
        this.rolePersistancePort = rolePersistancePort;
    }


    @Override
    public RoleModel getRoleByName(String name) {
        return rolePersistancePort.getRoleByName(name).orElseThrow(
                () -> new RoleNotFoundException("No se encontro el rol")
        );
    }
}
