package com.hospitalmanagement.application.repositories;

import com.hospitalmanagement.application.models.Roles;
import com.hospitalmanagement.application.models.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Roles,Integer> {
    Roles findByRoleName(RoleName roleName);
}
