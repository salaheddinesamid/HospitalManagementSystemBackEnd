package com.hospitalmanagement.application.repository;

import com.hospitalmanagement.application.model.Role;
import com.hospitalmanagement.application.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findByRoleName(RoleName roleName);
}
