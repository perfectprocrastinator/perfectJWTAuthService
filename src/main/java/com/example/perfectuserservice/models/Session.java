package com.example.perfectuserservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.Date;
@Entity
@Data
public class Session extends BaseModel{
    private String token;
    private Date expiringAt;
    @ManyToOne
    private User user;
    private SessionStatus sessionStatus;
}
