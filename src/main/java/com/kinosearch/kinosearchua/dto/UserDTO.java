package com.kinosearch.kinosearchua.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserDTO {

    private String email;

    private String nickname;

    private String password;

}
