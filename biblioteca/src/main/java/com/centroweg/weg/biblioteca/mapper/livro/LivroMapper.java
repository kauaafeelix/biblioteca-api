package com.centroweg.weg.biblioteca.mapper.livro;

import com.centroweg.weg.biblioteca.dto.livro.LivroRequestDto;
import com.centroweg.weg.biblioteca.dto.livro.LivroResponseDto;
import com.centroweg.weg.biblioteca.model.Livro;
import org.springframework.stereotype.Component;

@Component
public class LivroMapper {

    public Livro toEntity (LivroRequestDto livroRequestDto){

        return new Livro(
                livroRequestDto.titulo(),
                livroRequestDto.autor(),
                livroRequestDto.anoPublicacao()
        );
    }

    public LivroResponseDto toDto (Livro livro){

        return new LivroResponseDto(
                livro.getId(),
                livro.getTitulo(),
                livro.getAutor(),
                livro.getAno_publicacao()
        );
    }
}
