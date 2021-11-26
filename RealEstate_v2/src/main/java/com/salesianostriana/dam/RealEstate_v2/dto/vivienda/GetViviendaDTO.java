package com.salesianostriana.dam.RealEstate_v2.dto.vivienda;

import com.salesianostriana.dam.RealEstate_v2.model.Tipo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class GetViviendaDTO {

    private Long id;
    private String titulo, descripcion, avatar;
    private String direccion, latlng, provincia, poblacion, codigoPostal;
    private int numHabitaciones, numBanios;
    private double metrosCuadrados, precio;
    private Tipo tipo;

    private boolean tienePiscina, tieneAscensor, tieneGaraje;

    private String propietario;
    private String inmobiliaria;
}