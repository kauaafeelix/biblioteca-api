package com.centroweg.weg.biblioteca.controller.usuario;

import com.centroweg.weg.biblioteca.dto.usuario.UsuarioRequestDto;
import com.centroweg.weg.biblioteca.dto.usuario.UsuarioResponseDto;
import com.centroweg.weg.biblioteca.model.Usuario;
import com.centroweg.weg.biblioteca.service.usuario.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public UsuarioResponseDto save (@RequestBody UsuarioRequestDto usuarioRequestDto){
        try{
            return service.salvar(usuarioRequestDto);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping
    public List<UsuarioResponseDto> findAll(){
        try{
            return service.listar();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public UsuarioResponseDto findById (@PathVariable int id) {
        try {
            return service.buscarPorId(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{id}")
    public UsuarioResponseDto updateUsuario(@PathVariable int id, @RequestBody UsuarioRequestDto usuarioRequestDto){
        try{
            return service.atualizar(usuarioRequestDto, id);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar o usuário com ID: "+id+ " || "+e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id){
        try{
            service.deletar(id);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar o usuário com ID: "+id+ " || "+e.getMessage());
        }
    }
}