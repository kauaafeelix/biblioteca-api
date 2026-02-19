package com.centroweg.weg.biblioteca.service.livro;

import com.centroweg.weg.biblioteca.model.Livro;
import com.centroweg.weg.biblioteca.repository.livro.LivroRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class LivroService {

    private LivroRepository repository;

    public LivroService (LivroRepository repository){
        this.repository = repository;
    }


    public Livro salvar(Livro livro) throws SQLException {
        return repository.salvarLivro(livro);
    }

    public List<Livro> listar() throws SQLException{
        return repository.listarLivros();
    }

    public Livro buscarPorId(int id) throws SQLException{
        return repository.buscarLivroPorId(id);
    }

    public Livro atualizar(Livro livro, int id) throws SQLException{

        livro.setId(id);
        repository.atualizarLivro(livro);
        return livro;
    }

    public void deletar(int id) throws SQLException{
        if (repository.buscarLivroPorId(id) == null){
            throw new RuntimeException("O usuário não existe");
        }
        repository.deleteLivro(id);
    }
}
