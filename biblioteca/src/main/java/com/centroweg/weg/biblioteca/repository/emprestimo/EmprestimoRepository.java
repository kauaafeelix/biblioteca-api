package com.centroweg.weg.biblioteca.repository.emprestimo;

import com.centroweg.weg.biblioteca.model.Emprestimo;
import com.centroweg.weg.biblioteca.utils.Conexao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoRepository {

    public Emprestimo salvarEmprestimo (Emprestimo emprestimo) throws SQLException {

        String sql = """
                INSERT INTO emprestimo (
                livro_id,
                usuario_id,
                data_emprestimo )
                VALUES (?,?,?)
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

            ps.setInt(1, emprestimo.getIdLivro());
            ps.setInt(2, emprestimo.getIdUsuario());
            ps.setObject(3, emprestimo.getData_emprestimo());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()){
                int idGerado = rs.getInt(1);
                emprestimo.setId(idGerado);
                return emprestimo;
            }

        }
        throw new RuntimeException("Emprestimo não pôde ser salvo no banco de dados.");
    }

    public List<Emprestimo> listarEmprestimos () throws SQLException{

        List<Emprestimo> emprestimos = new ArrayList<>();

        String sql = """
                SELECT 
                livro_id,
                usuario_id,
                data_emprestimo 
                FROM emprestimo
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)){

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Emprestimo emprestimo = new Emprestimo(
                        rs.getInt("livro_id"),
                        rs.getInt("usuario_id"),
                        rs.getObject("data_emprestimo", LocalDate.class)
                );
                emprestimos.add(emprestimo);
            }
        }
        return emprestimos;
    }

    public Emprestimo buscarEmprestimoPorId(int id) throws SQLException{

        String sql = """
                SELECT 
                id,
                livro_id,
                usuario_id,
                data_emprestimo
                FROM emprestimo
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                int idBanco = rs.getInt("id");
                int livroId = rs.getInt("livro_id");
                int usuarioId = rs.getInt("usuario_id");
                java.time.LocalDate dataEmprestimo = rs.getObject("data_emprestimo", java.time.LocalDate.class);

                return new Emprestimo(idBanco, livroId, usuarioId, dataEmprestimo);
            }
        }
        throw new RuntimeException("O emprestimo não foi encontrado ou não existe.");
    }

    public void atualizarEmprestimo (Emprestimo emprestimo) throws SQLException{

        String sql = """
                UPDATE emprestimo
                SET livro_id = ?,
                    usuario_id = ?,
                    data_emprestimo = ?
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, emprestimo.getIdLivro());
            ps.setInt(2, emprestimo.getIdUsuario());
            ps.setObject(3, emprestimo.getData_emprestimo());
            ps.setInt(4, emprestimo.getId());
            ps.executeUpdate();
        }
    }

    public void deleteEmprestimo (int id) throws SQLException{
        String sql = """
                DELETE FROM emprestimo
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public void registrarDevolucao(int idEmprestimo, LocalDate dataDevolucao) throws SQLException {

        String sql = """
            UPDATE emprestimo
            SET data_devolucao = ?
            WHERE id = ?
            """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setObject(1, dataDevolucao);
            ps.setInt(2, idEmprestimo);
            ps.executeUpdate();
        }
    }

}
