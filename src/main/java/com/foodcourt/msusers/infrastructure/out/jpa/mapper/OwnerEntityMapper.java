package com.foodcourt.msusers.infrastructure.out.jpa.mapper;

import com.foodcourt.msusers.domain.model.OwnerModel;
import com.foodcourt.msusers.infrastructure.out.jpa.entity.OwnerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OwnerEntityMapper {

    OwnerEntity toEntity (OwnerModel ownerModel);
    OwnerModel toModel (OwnerEntity ownerEntity);
}
