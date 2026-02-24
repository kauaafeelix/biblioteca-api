package com.centroweg.weg.biblioteca.mapper.emprestimo;


import com.centroweg.weg.biblioteca.dto.emprestimo.EmprestimoRequestDto;
import com.centroweg.weg.biblioteca.dto.emprestimo.EmprestimoResponseDto;
import com.centroweg.weg.biblioteca.model.Emprestimo;
import org.springframework.stereotype.Component;

@Component
public class EmprestimoMapper {

    public Emprestimo toEntity (EmprestimoRequestDto emprestimoRequestDto){

        return new Emprestimo(
                emprestimoRequestDto.livro_id(),
                emprestimoRequestDto.usuario_id(),
                emprestimoRequestDto.data_emprestimo(),
                emprestimoRequestDto.data_devolucao()
        );
    }

    public EmprestimoResponseDto toDto (Emprestimo emprestimo){

        return new EmprestimoResponseDto(
                emprestimo.getId(),
                emprestimo.getLivro_id(),
                emprestimo.getUsuario_id(),
                emprestimo.getData_emprestimo(),
                emprestimo.getData_devolucao()
        );
    }
}
