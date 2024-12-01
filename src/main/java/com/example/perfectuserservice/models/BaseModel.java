package com.example.perfectuserservice.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.processing.Generated;
import java.util.Date;
@MappedSuperclass
@Getter
@Setter
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
