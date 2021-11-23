package com.salesianostriana.dam.RealEstate_v2.users.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetUsuarioDTO {

    private String nombre;
    private String email;
    private String avatar;
    private String rol;
}
