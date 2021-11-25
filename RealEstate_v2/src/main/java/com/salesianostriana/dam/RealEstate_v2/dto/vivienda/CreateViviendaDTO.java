package com.salesianostriana.dam.RealEstate_v2.dto.vivienda;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateViviendaDTO {

    private String titulo, descripcion, avatar;
    private String ubicacion, zona;
    private int numHabitaciones, numBanios;
    private double metrosCuadrados, precio;
    private String tipo;

    private boolean tienePiscina, tieneAscensor, tieneGaraje;

    @Column(nullable = true)
    private Long inmobiliaria;
}
