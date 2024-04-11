package com.hospitalmanagement.application.models;


import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
public class Roles {
    @Id
    @GeneratedValue
    private Integer id;
    @Enumerated(EnumType.STRING)
    RoleName roleName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }

    public Roles(RoleName roleName){
        this.roleName = roleName;
    }
    public String getRoleName(){
        return roleName.toString();
    }

}

