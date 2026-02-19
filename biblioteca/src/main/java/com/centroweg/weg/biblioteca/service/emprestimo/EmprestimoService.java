package com.centroweg.weg.biblioteca.service.emprestimo;

import com.centroweg.weg.biblioteca.model.Emprestimo;
import com.centroweg.weg.biblioteca.repository.emprestimo.EmprestimoRepository;
import com.centroweg.weg.biblioteca.repository.livro.LivroRepository;
import com.centroweg.weg.biblioteca.repository.usuario.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Service
public class EmprestimoService {

    private EmprestimoRepository emprestimoRepository;
    private LivroRepository livroRepository;
    private UsuarioRepository usuarioRepository;


    public EmprestimoService (EmprestimoRepository emprestimoRepository, LivroRepository livroRepository, UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
        this.livroRepository = livroRepository;
        this.emprestimoRepository = emprestimoRepository;
    }

    public Emprestimo salvar(Emprestimo emprestimo) throws SQLException {

        try {
            livroRepository.buscarLivroPorId(emprestimo.getLivro_id());
        } catch (RuntimeException e) {
            throw new RuntimeException("Livro com ID " + emprestimo.getLivro_id() + " não existe.");
        }

        try {
            usuarioRepository.buscarUsuarioPorId(emprestimo.getUsuario_id());
        } catch (RuntimeException e) {
            throw new RuntimeException("Usuário com ID " + emprestimo.getUsuario_id() + " não existe.");
        }

        if (emprestimoRepository.livroEstaEmprestado(emprestimo.getLivro_id())) {
            throw new RuntimeException("Este livro já está emprestado e não foi devolvido ainda.");
        }

        return emprestimoRepository.salvarEmprestimo(emprestimo);
    }


    public List<Emprestimo> listar() throws SQLException{
        return emprestimoRepository.listarEmprestimos();
    }

    public Emprestimo buscarPorId(int id) throws SQLException{
        return emprestimoRepository.buscarEmprestimoPorId(id);
    }

    public Emprestimo atualizar(Emprestimo emprestimo, int id) throws SQLException{

        emprestimo.setId(id);
        emprestimoRepository.atualizarEmprestimo(emprestimo);
        return emprestimo;
    }

    public void deletar(int id) throws SQLException{
        if (emprestimoRepository.buscarEmprestimoPorId(id) == null){
            throw new RuntimeException("O empréstimo não existe");
        }
        emprestimoRepository.deleteEmprestimo(id);
    }

    public void devolver(int id, LocalDate devolucao) throws SQLException{

        Emprestimo emprestimo = emprestimoRepository.buscarEmprestimoPorId(id);
        if (emprestimo == null){
            throw new RuntimeException("O empréstimo não existe");
        }
        emprestimoRepository.registrarDevolucao(id, devolucao);
    }

    public List<Emprestimo> listarPorUsuario(int id_usuario) throws SQLException{
        return emprestimoRepository.listarEmprestimosPorIdUsuario(id_usuario);
    }
}
