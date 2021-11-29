package com.salesianostriana.dam.RealEstate_v2.users.services;

import com.salesianostriana.dam.RealEstate_v2.model.Inmobiliaria;
import com.salesianostriana.dam.RealEstate_v2.services.InmobiliariaService;
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
    private final InmobiliariaService inmobiliariaService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.repository.findUserByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException(email + " no encontrado"));
    }

    // TODO ¿Seguro que estos 3 métodos no se podrían refactorizar para tener un único método más genérico y más fácil de mantener?

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
                    .inmobiliaria(inmobiliariaService.findById(createUserDTO.getInmobiliaria_id()).get())
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
