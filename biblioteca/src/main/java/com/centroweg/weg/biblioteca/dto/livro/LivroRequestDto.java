package com.centroweg.weg.biblioteca.dto.livro;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LivroRequestDto(

        @NotBlank (message = "O título do livro é obrigatório.")
        @Size(max = 255, message = "O título do livro deve conter no máximo 255 caracteres.")
        String titulo,

        @NotBlank (message = "O autor do livro é obrigatório.")
        @Size(max = 255, message = "O autor do livro deve conter no máximo 255 caracteres.")
        String autor,

        @NotBlank (message = "O ano de publicação do livro é obrigatório.")
        int ano_publicacao
        ) {
}
