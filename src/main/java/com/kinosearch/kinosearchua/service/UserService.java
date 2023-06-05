package com.kinosearch.kinosearchua.service;

import com.kinosearch.kinosearchua.dto.AuthenticationDTO;
import com.kinosearch.kinosearchua.entity.User;

public interface UserService {
    public void save(User user);
    public User findUserByEmail(String email);
    public boolean login(AuthenticationDTO authenticationDTO);
}
