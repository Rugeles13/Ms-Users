package com.foodcourt.msusers.aplication.handler.impl;

import com.foodcourt.msusers.aplication.dto.request.OwnerRequest;
import com.foodcourt.msusers.aplication.handler.IOwnerHandler;
import com.foodcourt.msusers.aplication.mapper.request.OwnerRequestMapper;
import com.foodcourt.msusers.domain.api.IOwnerServicePort;
import com.foodcourt.msusers.domain.api.IRoleServicePort;
import com.foodcourt.msusers.domain.model.OwnerModel;
import com.foodcourt.msusers.domain.model.RoleModel;
import com.foodcourt.msusers.domain.model.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.Role;

@Service

@Transactional
public class OwnerHandlerImplement implements IOwnerHandler {

    private final IOwnerServicePort ownerServicePort;
    private final OwnerRequestMapper ownerRequestMapper;

    public OwnerHandlerImplement(IOwnerServicePort ownerServicePort, OwnerRequestMapper ownerRequestMapper) {
        this.ownerServicePort = ownerServicePort;
        this.ownerRequestMapper = ownerRequestMapper;
    }


    @Override
    public OwnerModel saveOwner(OwnerRequest ownerRequest) {
        OwnerModel ownerModel = ownerRequestMapper.toOwnerModel(ownerRequest);

        // Llama al servicio de persistencia para guardar el owner, asignándole el rol de "OWNER"
        return ownerServicePort.saveOwner(ownerModel, UserRole.OWNER.toString());
    }
}
