package com.salesianostriana.dam.RealEstate_v2.users.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class GetUsuarioDTO {

    private UUID id;
    private String nombre;
    private String email;
    private String avatar;
    private String rol;
}
