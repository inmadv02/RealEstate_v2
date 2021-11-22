package com.salesianostriana.dam.RealEstate_v2.users.services;

import com.salesianostriana.dam.RealEstate_v2.services.base.BaseService;
import com.salesianostriana.dam.RealEstate_v2.users.model.Usuario;
import com.salesianostriana.dam.RealEstate_v2.users.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService extends BaseService<Usuario, Long, UsuarioRepository> {

    public Optional<Usuario> findByEmail(String email){
        return this.repository.findUserByEmail(email);
    }
}
