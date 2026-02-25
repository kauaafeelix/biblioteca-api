package com.centroweg.weg.biblioteca.service.usuario;


import com.centroweg.weg.biblioteca.dto.usuario.UsuarioRequestDto;
import com.centroweg.weg.biblioteca.dto.usuario.UsuarioResponseDto;
import com.centroweg.weg.biblioteca.mapper.usuario.UsuarioMapper;
import com.centroweg.weg.biblioteca.model.Usuario;
import com.centroweg.weg.biblioteca.repository.usuario.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    private final UsuarioMapper mapper;

    public UsuarioService (UsuarioRepository repository, UsuarioMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }


    public UsuarioResponseDto salvar(UsuarioRequestDto usuarioRequestDto) throws SQLException{
        Usuario usuario = mapper.toEntity(usuarioRequestDto);

        repository.salvarUsuario(usuario);

        return mapper.toDto(usuario);
    }

    public List<UsuarioResponseDto>listar() throws SQLException{

        return repository.listarUsuarios()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    public UsuarioResponseDto buscarPorId(int id) throws SQLException{
        Usuario usuario = repository.buscarUsuarioPorId(id);
        return mapper.toDto(usuario);
    }

    public UsuarioResponseDto atualizar(UsuarioRequestDto usuarioRequestDto, int id) throws SQLException{

        Usuario usuarioEncontrado = repository.buscarUsuarioPorId(id);

        if (usuarioEncontrado == null){
            throw new IllegalArgumentException("Nenhum usuário encontrado. ");
        }
        if (usuarioRequestDto.nome().isBlank() || usuarioRequestDto.email().isBlank()){
            throw new IllegalArgumentException("O nome ou email não pode ser nulo. ");
        }

        usuarioEncontrado.setNome(usuarioEncontrado.getNome());
        usuarioEncontrado.setEmail(usuarioEncontrado.getEmail());

        return mapper.toDto(usuarioEncontrado);
    }

    public void deletar(int id) throws SQLException{
        if (repository.buscarUsuarioPorId(id) == null){
            throw new RuntimeException("O usuário não existe");
        }
        repository.deletarUsuario(id);
    }
}
