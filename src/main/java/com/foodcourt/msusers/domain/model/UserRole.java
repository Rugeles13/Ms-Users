package com.foodcourt.msusers.domain.model;

public enum UserRole {

    ADMIN("ROLE_ADMIN"),
    CLIENT("ROLE_CLIENT"),
    OWNER("ROLE_OWNER"),
    EMPLOYEE("ROLE_EMPLOYEE");

    private final String roleName;

    UserRole(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return roleName;
    }
}
