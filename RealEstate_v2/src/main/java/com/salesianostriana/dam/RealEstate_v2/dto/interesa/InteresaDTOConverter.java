package com.salesianostriana.dam.RealEstate_v2.dto.interesa;

import com.salesianostriana.dam.RealEstate_v2.model.Interesa;
import com.salesianostriana.dam.RealEstate_v2.users.model.Usuario;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
public class InteresaDTOConverter {

    public Interesa create(GetInteresaDTO i) {
        return Interesa.builder()
                .interesado(new Usuario(i.g))
                .createdDate(LocalDateTime.now())
                .mensaje(i.getMensaje())
                .build();
    }
}
