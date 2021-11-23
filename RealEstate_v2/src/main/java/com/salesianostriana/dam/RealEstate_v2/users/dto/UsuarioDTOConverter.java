package com.salesianostriana.dam.RealEstate_v2.users.dto;

import com.salesianostriana.dam.RealEstate_v2.users.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioDTOConverter {

    public GetUsuarioDTO usuarioTOGetUsuarioDTO(Usuario usuario){
        return GetUsuarioDTO.builder()
                .nombre(usuario.getNombre())
                .email(usuario.getEmail())
                .avatar(usuario.getAvatar())
                .rol(usuario.getRol().name())
                .build();
    }
}
