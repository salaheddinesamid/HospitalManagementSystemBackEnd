package com.hospitalmanagement.application.departments.staff_management;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Roles,Integer> {
    Roles findByRoleName(RoleName roleName);
}
