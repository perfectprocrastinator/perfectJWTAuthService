package com.example.perfectuserservice.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
/*
This class is created because there are some dependencies which are not provided out of the box
Even if you include those dependencies in pom.xml spring doesn't create bean objects and asks
us to create those objects manually.
 The error message "Could not autowire. No beans of 'BCryptPasswordEncoder' type found." indicates that Spring is unable to find a BCryptPasswordEncoder bean in your application context to inject where you need it.

This is because Spring Security requires you to manually define a BCryptPasswordEncoder bean if you want to use it for password encoding, and it isn't automatically provided out of the box.

Solution:
You need to define a BCryptPasswordEncoder bean in your @Configuration class to make it available for autowiring.
*/
@Configuration

public class ApplicationConfiguration {
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
