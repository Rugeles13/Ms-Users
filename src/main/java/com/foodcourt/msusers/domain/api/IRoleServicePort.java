package com.foodcourt.msusers.domain.api;

import com.foodcourt.msusers.domain.model.RoleModel;


public interface IRoleServicePort {

    RoleModel getRoleByName(String name);

}
