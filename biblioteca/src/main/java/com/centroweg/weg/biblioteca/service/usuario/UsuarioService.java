package com.centroweg.weg.biblioteca.service.usuario;


import com.centroweg.weg.biblioteca.model.Usuario;
import com.centroweg.weg.biblioteca.repository.usuario.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UsuarioService {

    private UsuarioRepository repository;

    public UsuarioService (UsuarioRepository repository){
        this.repository = repository;
    }


    public Usuario salvar(Usuario usuario) throws SQLException{
        return repository.salvarUsuario(usuario);
    }

    public List<Usuario>listar() throws SQLException{
        return repository.listarUsuarios();
    }

    public Usuario buscarPorId(int id) throws SQLException{
        return repository.buscarUsuarioPorId(id);
    }

    public Usuario atualizar(Usuario usuario, int id) throws SQLException{

        usuario.setId(id);
        repository.atualizarUsuario(usuario);
        return usuario;
    }

    public void deletar(int id) throws SQLException{
        if (repository.buscarUsuarioPorId(id) == null){
            throw new RuntimeException("O usuário não existe");
        }
        repository.deletarUsuario(id);
    }
}
