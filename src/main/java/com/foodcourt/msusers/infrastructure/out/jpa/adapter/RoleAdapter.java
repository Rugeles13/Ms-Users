package com.foodcourt.msusers.infrastructure.out.jpa.adapter;

import com.foodcourt.msusers.domain.model.RoleModel;
import com.foodcourt.msusers.domain.spi.IRolePersistancePort;
import com.foodcourt.msusers.infrastructure.out.jpa.mapper.RoleEntityMapper;
import com.foodcourt.msusers.infrastructure.out.jpa.repository.IRoleRepository;
import lombok.RequiredArgsConstructor;

import javax.management.relation.Role;
import java.util.Optional;


public class RoleAdapter implements IRolePersistancePort {

    private final IRoleRepository roleRepository;
    private final RoleEntityMapper roleEntityMapper;

    public RoleAdapter(IRoleRepository roleRepository, RoleEntityMapper roleEntityMapper) {
        this.roleRepository = roleRepository;
        this.roleEntityMapper = roleEntityMapper;
    }


    @Override
    public Optional<RoleModel> getRoleByName(String name) {
        return roleRepository.findByName(name).map(
                roleEntityMapper::toModel
        );
    }
}
