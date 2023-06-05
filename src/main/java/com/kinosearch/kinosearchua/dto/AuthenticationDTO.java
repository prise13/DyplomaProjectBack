package com.kinosearch.kinosearchua.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthenticationDTO {
    private String email;

    private String password;
}
