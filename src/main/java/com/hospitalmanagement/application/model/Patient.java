package com.hospitalmanagement.application.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
public class Patient  implements UserDetails {

    @Id
    @GeneratedValue
    Integer id;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "national_id")
    String nationalId;

    @Column(name = "email")
    String email;

    @Column(name = "address")
    String address;

    @Column(name = "password")
    String password;

    @ManyToOne
    @JoinColumn(name = "role")
    Role role;

    @OneToOne
    @JoinColumn(name = "room_id",nullable = true)
    Room room;

    public Patient(String firstName,String lastName,String nationalId,String address){
        this.firstName  = firstName;
        this.lastName = lastName;
        this.nationalId = nationalId;
        this.address = address;
    }
    public Patient(){

    }

    public String getFullName(){
        return this.firstName + " " + this.lastName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.getRoleName().toString()));
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
