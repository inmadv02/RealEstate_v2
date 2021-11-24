package com.salesianostriana.dam.RealEstate_v2.dto.propietario;

import com.salesianostriana.dam.RealEstate_v2.users.model.Usuario;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PropietarioDTOConverter {

    public GetPropietarioDTO propietarioToGetPropietarioDTO(Usuario p) {

        List <String> lista = new ArrayList<>();

        for(int i = 0; i < p.getViviendas().size(); i++){

            lista.add(p.getViviendas().get(i).getTitulo());

        }
        return  GetPropietarioDTO
                .builder()
                .id(p.getId())
                .viviendas(lista)
                .email(p.getEmail())
                .nombre(p.getNombre())
                .avatar(p.getAvatar())
                .rol(p.getRol().name())
                .build();
    }


    public GetPropietarioViviendaDto propietarioToGetPropietarioViviendaDto(Usuario p){

        List<String> direccion= new ArrayList<>();

        for (int i = 0;i<p.getViviendas().size();i++){
            direccion.add(p.getViviendas().get(i).getDireccion());
        }
        return GetPropietarioViviendaDto
                .builder()
                .id(p.getId())
                .nombre(p.getNombre())
                .apellidos(p.getApellidos())
                .direccion(p.getDireccion())
                .email(p.getEmail())
                .telefono(p.getTelefono())
                .direccionVivienda(direccion)
                .build();
    }
}
