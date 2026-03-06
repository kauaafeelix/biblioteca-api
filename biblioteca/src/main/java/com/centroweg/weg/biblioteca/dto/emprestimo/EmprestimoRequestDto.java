package com.centroweg.weg.biblioteca.dto.emprestimo;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record EmprestimoRequestDto(
        @Positive
        int livro_id,
        @Positive
        int usuario_id,

        @PastOrPresent
        @NotBlank
        LocalDate data_emprestimo,

        @FutureOrPresent
        LocalDate data_devolucao
) {
}
