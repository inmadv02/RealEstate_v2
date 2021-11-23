package com.salesianostriana.dam.RealEstate_v2.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
public class LoginDTO {

    private String email;
    private String password;
}
