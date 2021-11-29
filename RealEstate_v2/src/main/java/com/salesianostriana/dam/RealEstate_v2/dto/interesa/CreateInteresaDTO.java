package com.salesianostriana.dam.RealEstate_v2.dto.interesa;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateInteresaDTO {


    private String mensaje;
    private Date createdDate;
}
