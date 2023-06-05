package com.kinosearch.kinosearchua.service;

import com.kinosearch.kinosearchua.dto.AuthenticationDTO;
import com.kinosearch.kinosearchua.entity.User;
import com.kinosearch.kinosearchua.repository.UserRepo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Setter
@Getter
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public void save(User user) {
        userRepo.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepo.findUserByEmail(email);
    }

    @Override
    public boolean login(AuthenticationDTO authenticationDTO) {
        User user = userRepo.findUserByEmail(authenticationDTO.getEmail());
        if (user != null) {
            return new BCryptPasswordEncoder().matches(authenticationDTO.getPassword(), user.getPassword());
        }
        return false;
    }
}
