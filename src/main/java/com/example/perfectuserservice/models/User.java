package com.example.perfectuserservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
@Entity
@Data
@JsonDeserialize(as = User.class)
public class User extends BaseModel{
    private String email;
    private String password;
    @ManyToMany
    @JsonIgnore
    private Set<Role> roles = new HashSet<>();
}
