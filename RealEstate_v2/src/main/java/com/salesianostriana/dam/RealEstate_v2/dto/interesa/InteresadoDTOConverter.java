package com.salesianostriana.dam.RealEstate_v2.dto.interesa;


import com.salesianostriana.dam.RealEstate_v2.users.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class InteresadoDTOConverter {


    public Usuario createInteresadoDTOToInteresado(CreateInteresaDTO c){
        return Usuario
                .builder()
                .nombre(c.getNombre())
                .apellidos(c.getApellidos())
                .direccion(c.getDireccion())
                .avatar(c.getAvatar())
                .email(c.getEmail())
                .telefono(c.getTelefono())
                .build();
    }
    

}
