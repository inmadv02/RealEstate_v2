package com.salesianostriana.dam.RealEstate_v2;

import com.salesianostriana.dam.RealEstate_v2.model.Tipo;
import com.salesianostriana.dam.RealEstate_v2.model.Vivienda;
import com.salesianostriana.dam.RealEstate_v2.security.PasswordEncoderConfig;
import com.salesianostriana.dam.RealEstate_v2.services.ViviendaService;
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
    private final ViviendaService viviendaService;


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

        Vivienda vivienda = Vivienda.builder()
                .titulo("Casa 1")
                .descripcion("Descripcion")
                .precio(234567.78)
                .tipoVivienda(Tipo.VENTA)
                .build();

        Vivienda vivienda2 = Vivienda.builder()
                .titulo("Casa 2")
                .descripcion("Descripcion")
                .precio(2354567.78)
                .tipoVivienda(Tipo.ALQUILER)
                .build();

        Vivienda vivienda3 = Vivienda.builder()
                .titulo("Casa 3")
                .descripcion("Descripcion")
                .precio(56767.78)
                .tipoVivienda(Tipo.ALQUILER)
                .build();

        viviendaService.save(vivienda);
        viviendaService.save(vivienda2);
        viviendaService.save(vivienda3);


    }
}
