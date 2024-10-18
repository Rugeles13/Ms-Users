package com.foodcourt.msusers.domain.spi;


import com.foodcourt.msusers.domain.model.RoleModel;
import java.util.Optional;

public interface IRolePersistancePort {

    Optional<RoleModel> getRoleByName(String name);

}
