package com.example.perfectuserservice.dtos;

import lombok.Data;

@Data
public class SignUpRequestDTO {
    private String email;
    private String password;
}
