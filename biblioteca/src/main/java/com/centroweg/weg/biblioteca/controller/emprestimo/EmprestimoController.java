package com.centroweg.weg.biblioteca.controller.emprestimo;

import com.centroweg.weg.biblioteca.dto.emprestimo.EmprestimoRequestDto;
import com.centroweg.weg.biblioteca.dto.emprestimo.EmprestimoResponseDto;
import com.centroweg.weg.biblioteca.model.Emprestimo;
import com.centroweg.weg.biblioteca.service.emprestimo.EmprestimoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/emprestimo")
public class EmprestimoController {

    private EmprestimoService service;

    public EmprestimoController(EmprestimoService service) {
        this.service = service;
    }

    @PostMapping
    public EmprestimoResponseDto save (@RequestBody EmprestimoRequestDto emprestimoRequestDto){
        try{
            return service.salvar(
                    emprestimoRequestDto
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping
    public List<EmprestimoResponseDto> findAll(){
        try{
            return service.listar();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public EmprestimoResponseDto findById (@PathVariable int id) {
        try {
            return service.buscarPorId(
                    id
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{usuarioId}/usuario")
    public List<EmprestimoResponseDto> listarPorUsuario(@PathVariable int usuarioId) {
        try {
            return service.listarPorUsuario(
                    usuarioId
            );
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar os empréstimos do usuário com ID: " + usuarioId + " || " + e.getMessage());
        }

    }

    @PutMapping("/{id}")
    public EmprestimoResponseDto updateEmprestimo(@PathVariable int id, @RequestBody EmprestimoRequestDto emprestimoRequestDto){
        try{
            return service.atualizar(
                    emprestimoRequestDto,
                    id
            );
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar o empréstimo com ID: "+id+ " || "+e.getMessage());
        }
    }

    @PutMapping ("/devolucao/{id}")
    public void devolver(@PathVariable int id, @RequestBody EmprestimoRequestDto emprestimoRequestDto){
        try{
            service.devolver(
                    id,
                    emprestimoRequestDto.data_devolucao()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao devolver o empréstimo com ID: "+id+ " || "+e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id){
        try{
            service.deletar(
                    id
            );
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar o empréstimo com ID: "+id+ " || "+e.getMessage());
        }
    }
}
