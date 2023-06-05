package com.kinosearch.kinosearchua.controller;

import com.kinosearch.kinosearchua.dto.AuthenticationDTO;
import com.kinosearch.kinosearchua.dto.ErrorDTO;
import com.kinosearch.kinosearchua.dto.UserDTO;
import com.kinosearch.kinosearchua.entity.User;
import com.kinosearch.kinosearchua.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Getter
@Setter
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/api/users")
    public ResponseEntity<?> createUser(@RequestBody @Validated UserDTO userDTO) {
        if (userService.findUserByEmail(userDTO.getEmail()) == null) {
            User user = User.builder()
                    .email(userDTO.getEmail())
                    .nickname(userDTO.getNickname())
                    .password(new BCryptPasswordEncoder().encode(userDTO.getPassword()))
                    .enabled(true)
                    .role("USER")
                    .build();
            userService.save(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        else if (userService.findUserByEmail(userDTO.getEmail()) != null) {
            ErrorDTO errorDTO = ErrorDTO.builder()
                    .errorMessage("Користувач з такою поштою вже зареєстрований, будь ласка увійдіть або відновіть пароль!")
                    .build();
            return new ResponseEntity<ErrorDTO>(errorDTO, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/api/login")
    public ResponseEntity<?> login(@RequestBody @Validated AuthenticationDTO authenticationDTO) {
        if (userService.login(authenticationDTO)) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        ErrorDTO errorDTO = ErrorDTO.builder()
                .errorMessage("Неправильний логін або пароль!")
                .build();
        return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
    }
}
