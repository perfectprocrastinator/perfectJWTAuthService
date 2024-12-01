package com.example.perfectuserservice.controllers;

import com.example.perfectuserservice.dtos.LoginRequestDTO;
import com.example.perfectuserservice.dtos.LoginResponseDTO;
import com.example.perfectuserservice.dtos.RequestStatus;
import com.example.perfectuserservice.dtos.SignUpRequestDTO;
import com.example.perfectuserservice.dtos.SignUpResponseDTO;
import com.example.perfectuserservice.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
//    @GetMapping()
//    public GetAllUsersDTO getAllUsers(){
//
//    }
    @PostMapping("/sign_up")
    public ResponseEntity<SignUpResponseDTO> signUp(@RequestBody SignUpRequestDTO signUpRequestDTO) {
        SignUpResponseDTO signUpResponseDTO = new SignUpResponseDTO();
        if(authService.signUp(signUpRequestDTO.getEmail(), signUpRequestDTO.getPassword())){
            signUpResponseDTO.setRequestStatus(RequestStatus.SUCCESS);
            ResponseEntity<SignUpResponseDTO> response = new ResponseEntity<>(signUpResponseDTO, HttpStatus.OK);
            return response;
        }
        else{
            signUpResponseDTO.setRequestStatus(RequestStatus.FAILED);
            ResponseEntity<SignUpResponseDTO> response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            return response;
        }

    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        String token= authService.login(loginRequestDTO.getEmail(), loginRequestDTO.getPassword());
        loginResponseDTO.setRequestStatus(RequestStatus.SUCCESS);
        MultiValueMap<String,String> headerMap = new LinkedMultiValueMap<>();
        headerMap.add("Authorization", "Bearer "+token);
        ResponseEntity<LoginResponseDTO> response = new ResponseEntity<>(
                loginResponseDTO,
                headerMap,
                HttpStatus.OK
        );
        return response;
    }
    @GetMapping("/validate")
    public ResponseEntity validate(@RequestParam("token") String JSONtoken) {
        if(authService.validateToken(JSONtoken)){
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }
}
