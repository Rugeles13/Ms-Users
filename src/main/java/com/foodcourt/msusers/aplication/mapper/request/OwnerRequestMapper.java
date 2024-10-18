package com.foodcourt.msusers.aplication.mapper.request;

import com.foodcourt.msusers.aplication.dto.request.OwnerRequest;
import com.foodcourt.msusers.domain.model.OwnerModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OwnerRequestMapper {

    OwnerModel toOwnerModel (OwnerRequest ownerRequest);

}
