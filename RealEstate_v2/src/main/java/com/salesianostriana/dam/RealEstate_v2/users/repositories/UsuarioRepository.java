package com.salesianostriana.dam.RealEstate_v2.users.repositories;

import com.salesianostriana.dam.RealEstate_v2.users.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    Optional<Usuario> findUserByEmail(String email);
}
