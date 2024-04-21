package com.example.securitytestspring.Service;

import com.example.securitytestspring.Api.ApiException;
import com.example.securitytestspring.Model.User;
import com.example.securitytestspring.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;
    public void register(User user){
        user.setRole("CUSTOMER");
        String hashPassword = new BCryptPasswordEncoder().encode(user.getPassword());

        user.setPassword(hashPassword);
        user.setPassword(hashPassword);
        authRepository.save(user);
    }




    public void delete(String username){
        User user = authRepository.findUserByUsername(username);
        if (user == null){
            throw new ApiException("user not found");
        }
        authRepository.delete(user);

    }

    public List<User> getAll() {
        return authRepository.findAll();
    }
}
