package com.centroweg.weg.biblioteca.mapper.usuario;

import com.centroweg.weg.biblioteca.dto.usuario.UsuarioRequestDto;
import com.centroweg.weg.biblioteca.dto.usuario.UsuarioResponseDto;
import com.centroweg.weg.biblioteca.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public Usuario toEntity (UsuarioRequestDto usuarioRequestDto){

        return new Usuario(
                usuarioRequestDto.nome(),
                usuarioRequestDto.email()
        );
    }

    public UsuarioResponseDto toDto (Usuario usuario){

        return new UsuarioResponseDto(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail()
        );
    }
}
