package com.foodcourt.msusers.domain.api;

import com.foodcourt.msusers.domain.model.OwnerModel;

public interface IOwnerServicePort {

    OwnerModel saveOwner (OwnerModel ownerModel, String roleName);
    OwnerModel getOwnerById(Long id);

}
