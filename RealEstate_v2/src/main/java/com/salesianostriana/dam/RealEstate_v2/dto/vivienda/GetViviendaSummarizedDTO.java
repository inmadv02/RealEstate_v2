package com.salesianostriana.dam.RealEstate_v2.dto.vivienda;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetViviendaSummarizedDTO {

    private Long id;
    private String titulo, descripcion, avatar;
    private double precio;
    private int interesas;
}
