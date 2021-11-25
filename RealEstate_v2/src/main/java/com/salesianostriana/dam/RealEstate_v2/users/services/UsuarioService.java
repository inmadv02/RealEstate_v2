package com.salesianostriana.dam.RealEstate_v2.users.services;

import com.salesianostriana.dam.RealEstate_v2.services.base.BaseService;
import com.salesianostriana.dam.RealEstate_v2.users.dto.CreateUsuarioDTO;
import com.salesianostriana.dam.RealEstate_v2.users.model.RolUsuario;
import com.salesianostriana.dam.RealEstate_v2.users.model.Usuario;
import com.salesianostriana.dam.RealEstate_v2.users.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service("userDetailsService")
@RequiredArgsConstructor
public class UsuarioService extends BaseService<Usuario, UUID, UsuarioRepository> implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.repository.findUserByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException(email + " no encontrado"));
    }

    public Usuario save(CreateUsuarioDTO createUserDTO) {
        if(createUserDTO.getPassword().contentEquals(createUserDTO.getPassword2())) {
            Usuario usuario = Usuario.builder()
                    .password(passwordEncoder.encode(createUserDTO.getPassword()))
                    .avatar(createUserDTO.getAvatar())
                    .nombre(createUserDTO.getNombre())
                    .email(createUserDTO.getEmail())
                    .rol(RolUsuario.ADMIN)
                    .build();
            return save(usuario);

        } else {
            return null;
        }
    }

    public Usuario savePropietario(CreateUsuarioDTO createUserDTO) {
        if(createUserDTO.getPassword().contentEquals(createUserDTO.getPassword2())) {
            Usuario usuario = Usuario.builder()
                    .password(passwordEncoder.encode(createUserDTO.getPassword()))
                    .avatar(createUserDTO.getAvatar())
                    .nombre(createUserDTO.getNombre())
                    .email(createUserDTO.getEmail())
                    .rol(RolUsuario.PROPIETARIO)
                    .build();
            return save(usuario);

        } else {
            return null;
        }
    }

    public Usuario saveGestor(CreateUsuarioDTO createUserDTO) {
        if(createUserDTO.getPassword().contentEquals(createUserDTO.getPassword2())) {
            Usuario usuario = Usuario.builder()
                    .password(passwordEncoder.encode(createUserDTO.getPassword()))
                    .avatar(createUserDTO.getAvatar())
                    .nombre(createUserDTO.getNombre())
                    .email(createUserDTO.getEmail())
                    .rol(RolUsuario.GESTOR)
                    .build();
            return save(usuario);

        } else {
            return null;
        }
    }

    public Optional<Usuario> usuariosPorRol(RolUsuario rolUsuario) {
        return repository.findUsuarioByRol(rolUsuario);
    }
}
