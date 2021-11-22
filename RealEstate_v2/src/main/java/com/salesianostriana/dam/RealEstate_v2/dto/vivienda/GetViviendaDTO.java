package com.salesianostriana.dam.RealEstate_v2.dto.vivienda;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class GetViviendaDTO {

    private Long id;
    private String titulo, descripcion, avatar;
    private String ubicacion, zona;
    private int numHabitaciones, numBanios;
    private double metrosCuadrados, precio;
    private String tipo;

    private boolean tienePiscina, tieneAscensor, tieneGaraje;

    private String propietario;
    private String inmobiliaria;
}