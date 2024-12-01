package com.example.perfectuserservice.services;

import com.example.perfectuserservice.exceptions.UserAlreadyPresentException;
import com.example.perfectuserservice.exceptions.UserNotFoundException;
import com.example.perfectuserservice.exceptions.WrongPasswordException;
import com.example.perfectuserservice.models.Session;
import com.example.perfectuserservice.models.User;
import com.example.perfectuserservice.repositories.SessionRepository;
import com.example.perfectuserservice.repositories.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.channels.CancelledKeyException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {
    UserRepository userRepository;
    SessionRepository sessionRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    SecretKey key = Jwts.SIG.HS256.key().build();
    public AuthService(UserRepository userRepository,SessionRepository sessionRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    public Boolean signUp(String email, String password) {
        if(userRepository.findByEmail(email).isPresent()) {
            throw new UserAlreadyPresentException("User with email "+email+" already exists");
        }
        else{
            User user = new User();
            user.setEmail(email);
            user.setPassword(bCryptPasswordEncoder.encode(password));
            userRepository.save(user);
            return true;
        }

    }
    public String login(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()){
            throw new UserNotFoundException("User with email "+email+" not signed up");
        }
        boolean matches = bCryptPasswordEncoder.matches(password, userOptional.get().getPassword());
        if(matches) {
            String token = getJwtToken(userOptional.get().getId(),new ArrayList<>(),userOptional.get().getEmail());
            Session session = new Session();
            session.setToken(token);
            session.setUser(userOptional.get());
            Calendar calendar = Calendar.getInstance();
            Date currentDate = calendar.getTime();
            calendar.add(Calendar.DAY_OF_MONTH,3);
            Date datePlus3Days = calendar.getTime();
            session.setExpiringAt(datePlus3Days);
            sessionRepository.save(session);
            return token;

        }
        else{
            throw new WrongPasswordException("User with email "+email+" entered wrong credentials");
        }
    }
    private String getJwtToken(Long userId, List<String> roles,String email) {
        Map<String,Object> dataInJwt = new HashMap<>();
        dataInJwt.put("user_id",userId);
        dataInJwt.put("roles",roles);
        dataInJwt.put("email",email);
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH,3);
        Date datePlus3Days = calendar.getTime();
        String token = Jwts.builder()
                .claims(dataInJwt)
                .issuer("Bilal.dev")
                .expiration(datePlus3Days)
                .issuedAt(new Date())
                .signWith(key)
                .compact();

        return token;
    }
    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token);
        }
        catch (Exception e){
            return false;
        }
        return true;

    }
}
