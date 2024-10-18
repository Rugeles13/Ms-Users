package com.foodcourt.msusers.infrastructure.out.jpa.mapper;

import com.foodcourt.msusers.domain.model.RoleModel;
import com.foodcourt.msusers.infrastructure.out.jpa.entity.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleEntityMapper {

    RoleModel toModel (RoleEntity roleEntity);
    RoleEntity toEntity (RoleModel roleModel);
}
