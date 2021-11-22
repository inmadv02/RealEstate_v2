package com.salesianostriana.dam.RealEstate_v2.dto.inmobiliaria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class GetInmobiliariaDTO {

    private Long id;

    private String nombre, telefono, email;
    private List<String> viviendas;
}
