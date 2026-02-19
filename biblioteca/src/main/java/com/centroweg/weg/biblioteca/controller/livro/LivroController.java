package com.centroweg.weg.biblioteca.controller.livro;

import com.centroweg.weg.biblioteca.model.Livro;
import com.centroweg.weg.biblioteca.service.livro.LivroService;
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
    public Livro save (@RequestBody Livro livro){
        try{
            return service.salvar(livro);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping
    public List<Livro> findAll(){
        try{
            return service.listar();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public Livro findById (@PathVariable int id) {
        try {
            return service.buscarPorId(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{id}")
    public Livro updateLivro(@PathVariable int id, @RequestBody Livro livro){
        try{
            return service.atualizar(livro, id);
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
