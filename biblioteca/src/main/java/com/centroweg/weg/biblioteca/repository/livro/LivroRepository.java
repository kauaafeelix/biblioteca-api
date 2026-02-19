package com.centroweg.weg.biblioteca.repository.livro;

import com.centroweg.weg.biblioteca.model.Livro;
import com.centroweg.weg.biblioteca.utils.Conexao;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class LivroRepository {

    public Livro salvarLivro (Livro livro) throws SQLException{

        String sql = """
                INSERT INTO livro (
                titulo,
                autor,
                ano_publicacao )
                VALUES (?,?,?)
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

            ps.setString(1, livro.getTitulo());
            ps.setString(2, livro.getAutor());
            ps.setInt(3, livro.getAnoPublicacao());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()){
                int idGerado = rs.getInt(1);
                livro.setId(idGerado);
                return livro;
            }

        }
        throw new RuntimeException("Livro não pôde ser salvo no banco de dados.");
    }

    public List<Livro> listarLivros () throws SQLException{

        List<Livro> livros = new ArrayList<>();

        String sql = """
                SELECT 
                id,
                titulo,
                autor,
                ano_publicacao
                FROM livro
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)){

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Livro livro = new Livro(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getInt("ano_publicacao")
                        );
                livros.add(livro);
            }
        }
        return livros;
    }

    public Livro buscarLivroPorId(int id) throws SQLException{

        String sql = """
                SELECT 
                id,
                titulo,
                autor,
                ano_publicacao
                FROM livro
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                int idBanco = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                int anoPublicacao = rs.getInt("ano_publicacao");

                return new Livro(idBanco, titulo, autor, anoPublicacao);
            }
        }
        throw new RuntimeException("O livro não foi encontrado ou não existe.");
    }

    public void atualizarLivro (Livro livro) throws SQLException{

        String sql = """
                UPDATE livro
                SET titulo = ?
                    autor = ?
                    ano_publicacao = ?
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, livro.getTitulo());
            ps.setString(2, livro.getAutor());
            ps.setInt(3, livro.getAnoPublicacao());
            ps.setInt(4, livro.getId());
            ps.executeUpdate();
        }
    }

    public void deleteLivro (int id) throws SQLException{
        String sql = """
                DELETE FROM livro
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
