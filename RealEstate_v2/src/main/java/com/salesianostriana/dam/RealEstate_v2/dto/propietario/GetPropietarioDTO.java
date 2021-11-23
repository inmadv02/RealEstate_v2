package com.salesianostriana.dam.RealEstate_v2.dto.propietario;

import com.salesianostriana.dam.RealEstate_v2.model.Vivienda;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@SuperBuilder
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class GetPropietarioDTO {

    private Long id;
    private String nombre;
    private String apellidos;
    private String email;
    private String telefono;
    private String avatar;
    private List <String> viviendas;




}