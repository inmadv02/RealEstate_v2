package com.salesianostriana.dam.RealEstate_v2.users.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUsuarioDTO {

    private String nombre;
    private String username;
    private String avatar;
    private String email;
    private String password;
    private String password2;
}
