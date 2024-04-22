package com.example.securitytestspring.Controller;

import com.example.securitytestspring.Model.User;
import com.example.securitytestspring.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody @Valid User user){
        authService.register(user);
        return ResponseEntity.ok("User has been added : \n" + user);
    }

    @PostMapping("/login")
    public ResponseEntity login(){

        return ResponseEntity.ok(("welcome back!"));
    }

    @PostMapping("/logout")
    public ResponseEntity logout(){

        return ResponseEntity.ok("Bye!");
    }


    @GetMapping("/get")
    public ResponseEntity findAllUser(){

        return ResponseEntity.ok(authService.getAll());
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(String username){
        authService.delete(username);
        return ResponseEntity.ok("user has been deleted");
    }




}
