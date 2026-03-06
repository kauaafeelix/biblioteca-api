package com.centroweg.weg.biblioteca.controller.livro;

import com.centroweg.weg.biblioteca.dto.livro.LivroRequestDto;
import com.centroweg.weg.biblioteca.dto.livro.LivroResponseDto;
import com.centroweg.weg.biblioteca.model.Livro;
import com.centroweg.weg.biblioteca.service.livro.LivroService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/livro")
public class LivroController {

    private LivroService service;

    public LivroController(LivroService service) {
        this.service = service;
    }

    @PostMapping
    public LivroResponseDto save (@Valid  @RequestBody LivroRequestDto livroRequestDto){
        try{
            return service.salvar(livroRequestDto);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping
    public List<LivroResponseDto> findAll(){
        try{
            return service.listar();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public LivroResponseDto findById (@PathVariable int id) {
        try {
            return service.buscarPorId(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{id}")
    public LivroResponseDto updateLivro(@PathVariable int id, @Valid @RequestBody LivroRequestDto livroRequestDto){
        try{
            return service.atualizar(livroRequestDto, id);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar o livro com ID: "+id+ " || "+e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id){
        try{
            service.deletar(id);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar o livro com ID: "+id+ " || "+e.getMessage());
        }
    }
}
