package com.foodcourt.msusers.aplication.handler;

import com.foodcourt.msusers.aplication.dto.request.OwnerRequest;
import com.foodcourt.msusers.domain.model.OwnerModel;

public interface IOwnerHandler {

    OwnerModel saveOwner (OwnerRequest ownerRequest);
}
