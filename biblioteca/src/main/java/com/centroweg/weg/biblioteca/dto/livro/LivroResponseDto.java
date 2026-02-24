package com.centroweg.weg.biblioteca.dto.livro;

public record LivroResponseDto(
        int id,
        String titulo,
        String autor,
        int anoPublicacao
) {
}
