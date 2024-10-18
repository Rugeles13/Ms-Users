package com.foodcourt.msusers.infrastructure.out.jpa.adapter;

import com.foodcourt.msusers.domain.model.OwnerModel;
import com.foodcourt.msusers.domain.spi.IOwnerPersistancePort;
import com.foodcourt.msusers.infrastructure.exception.OwnerAlreadyExistException;
import com.foodcourt.msusers.infrastructure.out.jpa.entity.OwnerEntity;
import com.foodcourt.msusers.infrastructure.out.jpa.mapper.OwnerEntityMapper;
import com.foodcourt.msusers.infrastructure.out.jpa.repository.IOwnerRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Optional;


public class OwnerAdapter implements IOwnerPersistancePort {

    private final OwnerEntityMapper ownerEntityMapper;
    private final IOwnerRepository ownerRepository;

    public OwnerAdapter(OwnerEntityMapper ownerEntityMapper, IOwnerRepository ownerRepository) {
        this.ownerEntityMapper = ownerEntityMapper;
        this.ownerRepository = ownerRepository;
    }

    @Override
    public OwnerModel saveOwner(OwnerModel ownerModel) {

        OwnerEntity ownerEntity = ownerEntityMapper.toEntity(ownerModel);
        return ownerEntityMapper.toModel(ownerRepository.save(ownerEntity));

    }

    @Override
    public Optional<OwnerModel> getOwnerById(Long id) {
        return ownerRepository.findById(id).map(
                ownerEntityMapper::toModel
        );
    }
}
