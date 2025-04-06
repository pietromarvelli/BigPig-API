package com.bigpig.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SingInRequest {
    private String name;
    private String surname;
    private String username;
    private String password;
}
