package com.foodcourt.msusers.domain.spi;

import com.foodcourt.msusers.domain.model.OwnerModel;

import java.util.Optional;

public interface IOwnerPersistancePort {

    OwnerModel saveOwner (OwnerModel ownerModel);
    Optional<OwnerModel> getOwnerById(Long id);
}
