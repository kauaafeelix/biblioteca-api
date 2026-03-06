package com.centroweg.weg.biblioteca.dto.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioRequestDto(

        @NotBlank (message = "O nome do usuário é obrigatório.")
        @Size (max = 255, message = "O nome do usuário deve conter no máximo 255 caracteres.")
        String nome,

        @Email(message = "O email do usuário deve ser um endereço de email válido.")
        @NotBlank(message = "O email do usuário é obrigatório.")
        String email
) {
}
