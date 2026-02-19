package com.centroweg.weg.biblioteca.service.emprestimo;

import com.centroweg.weg.biblioteca.model.Emprestimo;
import com.centroweg.weg.biblioteca.repository.emprestimo.EmprestimoRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Service
public class EmprestimoService {

    private EmprestimoRepository repository;

    public EmprestimoService (EmprestimoRepository repository){
        this.repository = repository;
    }

    public Emprestimo salvar(Emprestimo emprestimo) throws SQLException {
        return repository.salvarEmprestimo(emprestimo);
    }

    public List<Emprestimo> listar() throws SQLException{
        return repository.listarEmprestimos();
    }

    public Emprestimo buscarPorId(int id) throws SQLException{
        return repository.buscarEmprestimoPorId(id);
    }

    public Emprestimo atualizar(Emprestimo emprestimo, int id) throws SQLException{

        emprestimo.setId(id);
        repository.atualizarEmprestimo(emprestimo);
        return emprestimo;
    }

    public void deletar(int id) throws SQLException{
        if (repository.buscarEmprestimoPorId(id) == null){
            throw new RuntimeException("O empréstimo não existe");
        }
        repository.deleteEmprestimo(id);
    }

    public void devolver(int id, LocalDate devolucao) throws SQLException{

        Emprestimo emprestimo = repository.buscarEmprestimoPorId(id);
        if (emprestimo == null){
            throw new RuntimeException("O empréstimo não existe");
        }
        emprestimo.setData_devolucao(devolucao);
        repository.atualizarEmprestimo(emprestimo);
    }
}
