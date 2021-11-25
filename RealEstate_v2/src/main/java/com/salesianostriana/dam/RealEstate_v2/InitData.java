package com.salesianostriana.dam.RealEstate_v2;

import com.salesianostriana.dam.RealEstate_v2.security.PasswordEncoderConfig;
import com.salesianostriana.dam.RealEstate_v2.users.controller.UsuarioController;
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

        Usuario usuario = Usuario.builder()
                .nombre("José Javier")
                .apellidos("Domínguez Rafael")
                .email("josejavier@gmail.com")
                .avatar("sigrbosigt")
                .password("hola3")
                .build();

        passwordEncoder.encode(usuario.getPassword());

        config.passwordEncoder().encode(usuario.getPassword());
        service.save(usuario);



    }
}
