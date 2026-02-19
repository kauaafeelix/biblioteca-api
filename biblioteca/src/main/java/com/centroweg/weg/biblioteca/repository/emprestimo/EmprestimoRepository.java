package com.centroweg.weg.biblioteca.repository.emprestimo;

import com.centroweg.weg.biblioteca.model.Emprestimo;
import com.centroweg.weg.biblioteca.utils.Conexao;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
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

            ps.setInt(1, emprestimo.getLivro_id());
            ps.setInt(2, emprestimo.getUsuario_id());
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
            id,
            livro_id,
            usuario_id,
            data_emprestimo,
            data_devolucao
            FROM emprestimo e
            """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)){

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Emprestimo emprestimo = new Emprestimo(
                        rs.getInt("id"),
                        rs.getInt("livro_id"),
                        rs.getInt("usuario_id"),
                        rs.getObject("data_emprestimo", LocalDate.class),
                        rs.getObject("data_devolucao", LocalDate.class)
                );
                emprestimos.add(emprestimo);
            }
        }
        return emprestimos;
    }



    public List<Emprestimo> listarEmprestimosPorIdUsuario(int usuarioId)throws SQLException{

        List<Emprestimo> emprestimos = new ArrayList<>();

        String sql = """
            SELECT
            e.id,
            e.livro_id,
            e.usuario_id,
            e.data_emprestimo,
            e.data_devolucao
        FROM emprestimo e
        WHERE e.usuario_id = ?
            """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, usuarioId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Emprestimo emprestimo = new Emprestimo(
                        rs.getInt("id"),
                        rs.getInt("livro_id"),
                        rs.getInt("usuario_id"),
                        rs.getObject("data_emprestimo", LocalDate.class),
                        rs.getObject("data_devolucao", LocalDate.class)
                );
                emprestimos.add(emprestimo);
            }
        }
        return emprestimos;
    }


    public Emprestimo buscarEmprestimoPorId(int id) throws SQLException{

        String sql = """
            SELECT
            e.id,
            e.livro_id,
            e.usuario_id,
            e.data_emprestimo,
            e.data_devolucao
            FROM emprestimo e
            """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)){

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Emprestimo emprestimo = new Emprestimo(
                        rs.getInt("id"),
                        rs.getInt("livro_id"),
                        rs.getInt("usuario_id"),
                        rs.getObject("data_emprestimo", LocalDate.class),
                        rs.getObject("data_devolucao", LocalDate.class)
                );
                return emprestimo;
            }
        }
        throw new RuntimeException("O empréstimo não foi encontrado ou não existe.");
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
            ps.setInt(1, emprestimo.getLivro_id());
            ps.setInt(2, emprestimo.getUsuario_id());
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

            ps.setDate(1, Date.valueOf(dataDevolucao));
            ps.setInt(2, idEmprestimo);
            ps.executeUpdate();
        }
    }

    public boolean livroEstaEmprestado(int livroId) throws SQLException {
        String sql = """
            SELECT COUNT(*) AS total
            FROM emprestimo
            WHERE livro_id = ? AND data_devolucao IS NULL
            """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, livroId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("total") > 0;
            }
        }
        return false;
    }


}
