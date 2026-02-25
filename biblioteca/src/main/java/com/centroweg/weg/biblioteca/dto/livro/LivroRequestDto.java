package com.centroweg.weg.biblioteca.dto.livro;

public record LivroRequestDto(
        String titulo,
        String autor,
        int ano_publicacao
        ) {
}
