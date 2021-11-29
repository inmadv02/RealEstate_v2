package com.salesianostriana.dam.RealEstate_v2.services;

import com.salesianostriana.dam.RealEstate_v2.dto.interesa.CreateInteresaDTO;
import com.salesianostriana.dam.RealEstate_v2.dto.interesa.InteresaDTOConverter;
import com.salesianostriana.dam.RealEstate_v2.model.Interesa;
import com.salesianostriana.dam.RealEstate_v2.repositories.InteresaRepository;
import com.salesianostriana.dam.RealEstate_v2.services.base.BaseService;
import com.salesianostriana.dam.RealEstate_v2.users.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class InteresaService extends BaseService<Interesa, Long, InteresaRepository> {

    private final InteresaDTOConverter converter;

    public Interesa addInteresa (@RequestBody CreateInteresaDTO dto, Usuario propietario) {

        Interesa interesa = dtoConverter.createViviendaDtoToVivienda(dto);

        vivienda.addToPropietario(propietario);

        this.save(vivienda);

        return vivienda;
    }
}
