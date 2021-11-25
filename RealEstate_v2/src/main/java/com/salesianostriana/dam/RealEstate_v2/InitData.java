package com.salesianostriana.dam.RealEstate_v2;

import com.salesianostriana.dam.RealEstate_v2.security.PasswordEncoderConfig;
import com.salesianostriana.dam.RealEstate_v2.users.controller.UsuarioController;
import com.salesianostriana.dam.RealEstate_v2.users.dto.CreateUsuarioDTO;
import com.salesianostriana.dam.RealEstate_v2.users.dto.UsuarioDTOConverter;
import com.salesianostriana.dam.RealEstate_v2.users.model.Usuario;
import com.salesianostriana.dam.RealEstate_v2.users.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class InitData {

    private final UsuarioService service;
    private final UsuarioDTOConverter converter;
    private final UsuarioController usuarioController;
    private final PasswordEncoder passwordEncoder;
    private final PasswordEncoderConfig config;


    @PostConstruct
    public void initData () {

        CreateUsuarioDTO usuario = CreateUsuarioDTO.builder()
                .nombre("José Javier")
                .email("josejavier@gmail.com")
                .avatar("sigrbosigt")
                .password("hola3")
                .password2("hola3")
                .username("javi_df")
                .build();
        service.save(usuario);




    }
}
