package com.salesianostriana.dam.RealEstate_v2.services;

import com.salesianostriana.dam.RealEstate_v2.model.Vivienda;
import com.salesianostriana.dam.RealEstate_v2.repositories.ViviendaRepository;
import com.salesianostriana.dam.RealEstate_v2.services.base.BaseService;

import java.util.List;

public class ViviendaService extends BaseService<Vivienda, Long, ViviendaRepository> {

    public List<Vivienda> findTop10ViviendaOrderByInteresas (){

        return repository.top5ViviendasInteresas();

    }
}
