package com.centroweg.weg.biblioteca.service.livro;

import com.centroweg.weg.biblioteca.dto.livro.LivroRequestDto;
import com.centroweg.weg.biblioteca.dto.livro.LivroResponseDto;
import com.centroweg.weg.biblioteca.mapper.livro.LivroMapper;
import com.centroweg.weg.biblioteca.model.Livro;
import com.centroweg.weg.biblioteca.repository.livro.LivroRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class LivroService {

    private final LivroRepository repository;
    private final LivroMapper mapper;

    public LivroService (LivroRepository repository, LivroMapper mapper){
        this.mapper = mapper;
        this.repository = repository;
    }


    public LivroResponseDto salvar(LivroRequestDto livroRequestDto) throws SQLException {

        Livro livro = mapper.toEntity(livroRequestDto);

        repository.salvarLivro(livro);

        return mapper.toDto(livro);
    }

    public List<LivroResponseDto> listar() throws SQLException{

        return repository.listarLivros()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    public LivroResponseDto buscarPorId(int id) throws SQLException{
        Livro livro = repository.buscarLivroPorId(id);
        return mapper.toDto(livro);
    }

    public LivroResponseDto atualizar(LivroRequestDto livroRequestDto, int id) throws SQLException{
        Livro livroEncontrado = repository.buscarLivroPorId(id);

        if (livroEncontrado == null){
            throw new IllegalArgumentException("Nenhum livro encontrado.");
        }

        if(livroRequestDto.titulo() == null || livroRequestDto.titulo().isBlank()){
            throw new IllegalArgumentException("O titulo do livro não pode ser nulo");
        }

        if(livroRequestDto.autor() == null || livroRequestDto.autor().isBlank()){
            throw new IllegalArgumentException("O nome do autor do livro não pode ser nulo");
        }

        livroEncontrado.setTitulo(livroRequestDto.titulo());
        livroEncontrado.setAutor(livroRequestDto.autor());
        livroEncontrado.setAno_publicacao(livroRequestDto.ano_publicacao());

        repository.atualizarLivro(livroEncontrado);
        return mapper.toDto(livroEncontrado);

    }

    public void deletar(int id) throws SQLException{

        if (repository.buscarLivroPorId(id) == null){
            throw new RuntimeException("O usuário não existe");
        }
        repository.deleteLivro(id);
    }
}
