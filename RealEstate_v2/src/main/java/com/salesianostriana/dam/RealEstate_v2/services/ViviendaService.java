package com.salesianostriana.dam.RealEstate_v2.services;

import com.salesianostriana.dam.RealEstate_v2.dto.vivienda.CreateViviendaDTO;
import com.salesianostriana.dam.RealEstate_v2.dto.vivienda.ViviendaDTOConverter;
import com.salesianostriana.dam.RealEstate_v2.model.Vivienda;
import com.salesianostriana.dam.RealEstate_v2.repositories.ViviendaRepository;
import com.salesianostriana.dam.RealEstate_v2.services.base.BaseService;
import com.salesianostriana.dam.RealEstate_v2.users.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ViviendaService extends BaseService<Vivienda, Long, ViviendaRepository> {

    private final ViviendaDTOConverter dtoConverter;

    public List<Vivienda> findTop10ViviendaOrderByInteresas (){

        return repository.top5ViviendasInteresas();

    }

    public Vivienda addVivienda (@RequestBody CreateViviendaDTO dto, Usuario propietario) {

        Vivienda vivienda = dtoConverter.createViviendaDtoToVivienda(dto);

        vivienda.addToPropietario(propietario);

        this.save(vivienda);

        return vivienda;
    }
}
