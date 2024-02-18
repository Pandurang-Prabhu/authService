package com.authentication.auth.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Builder
public class LoginModel {

    private String userName;

    private String password;


}
