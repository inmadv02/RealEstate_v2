package com.salesianostriana.dam.RealEstate_v2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class InteresaPK {

    private Long vivienda_id;

    private Long interesado_id;
}
