package com.centroweg.weg.biblioteca.repository.usuario;

import com.centroweg.weg.biblioteca.model.Usuario;
import com.centroweg.weg.biblioteca.utils.Conexao;
import org.springframework.stereotype.Repository;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Repository
public class UsuarioRepository {

    public Usuario salvarUsuario(Usuario usuario) throws SQLException{

        String sql = """
                INSERT INTO usuario (
                nome, 
                email )
                VALUES (?,?)
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()){
                int idGerado = rs.getInt(1);
                usuario.setId(idGerado);
                return usuario;
            }
        }
        throw new RuntimeException("Não foi possível salvar um usuário no banco de dados");
    }

    public List<Usuario>listarUsuarios() throws SQLException{

        List<Usuario> usuarios = new ArrayList<>();

        String sql = """
                SELECT 
                id,
                nome,
                email
                FROM usuario
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)){

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Usuario usuario = new Usuario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email")
                );

                usuarios.add(usuario);
            }
        }
        return usuarios;
    }

    public Usuario buscarUsuarioPorId(int id) throws SQLException{

        String sql = """
                SELECT 
                id,
                nome,
                email
                FROM usuario
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                int idBanco = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");

                return new Usuario(idBanco, nome, email);
            }
        }
        throw new RuntimeException("O usuário não foi encontrado ou não existe.");
    }

    public void atualizarUsuario (Usuario usuario) throws SQLException{

        String sql = """
                UPDATE usuario
                SET nome = ?,
                    email = ?
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setInt(3, usuario.getId());
            ps.executeUpdate();
        }
    }

    public void deletarUsuario (int id) throws SQLException{

        String sql = """
                DELETE FROM usuario
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
