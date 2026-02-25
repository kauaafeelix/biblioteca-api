package com.centroweg.weg.biblioteca.service.emprestimo;

import com.centroweg.weg.biblioteca.dto.emprestimo.EmprestimoRequestDto;
import com.centroweg.weg.biblioteca.dto.emprestimo.EmprestimoResponseDto;
import com.centroweg.weg.biblioteca.mapper.emprestimo.EmprestimoMapper;
import com.centroweg.weg.biblioteca.model.Emprestimo;
import com.centroweg.weg.biblioteca.model.Usuario;
import com.centroweg.weg.biblioteca.repository.emprestimo.EmprestimoRepository;
import com.centroweg.weg.biblioteca.repository.livro.LivroRepository;
import com.centroweg.weg.biblioteca.repository.usuario.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Service
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;
    private final LivroRepository livroRepository;
    private final UsuarioRepository usuarioRepository;
    private final EmprestimoMapper mapper;


    public EmprestimoService (
            EmprestimoRepository emprestimoRepository,
            LivroRepository livroRepository,
            UsuarioRepository usuarioRepository,
            EmprestimoMapper mapper
    ){
        this.usuarioRepository = usuarioRepository;
        this.livroRepository = livroRepository;
        this.emprestimoRepository = emprestimoRepository;
        this.mapper = mapper;
    }

    public EmprestimoResponseDto salvar(EmprestimoRequestDto emprestimoRequestDto) throws SQLException {

        Emprestimo emprestimo = mapper.toEntity(emprestimoRequestDto);

        try {
            livroRepository.buscarLivroPorId(emprestimo.getLivro_id());
        } catch (RuntimeException e) {
            throw new RuntimeException("Livro com ID " + emprestimo.getLivro_id() + " não existe.");
        }

        try {
            usuarioRepository.buscarUsuarioPorId(emprestimo.getUsuario_id());
        } catch (RuntimeException e) {
            throw new RuntimeException("Usuário com ID " + emprestimo.getUsuario_id() + " não existe.");
        }

        if (emprestimoRepository.livroEstaEmprestado(emprestimo.getLivro_id())) {
            throw new RuntimeException("Este livro já está emprestado e não foi devolvido ainda.");
        }

        emprestimoRepository.salvarEmprestimo(emprestimo);

        return mapper.toDto(emprestimo);

    }


    public List<EmprestimoResponseDto> listar() throws SQLException{
        return emprestimoRepository
                .listarEmprestimos()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    public EmprestimoResponseDto buscarPorId(int id) throws SQLException{

        Emprestimo emprestimo = emprestimoRepository.buscarEmprestimoPorId(id);
        return mapper.toDto(emprestimo);
    }

    public EmprestimoResponseDto atualizar(EmprestimoRequestDto emprestimoRequestDto, int id) throws SQLException{

        Emprestimo emprestimoEncontrado = emprestimoRepository.buscarEmprestimoPorId(id);

        if (emprestimoEncontrado == null){
            throw new IllegalArgumentException("Nenhum emprestimo foi encontrado.");
        }

        emprestimoEncontrado.setLivro_id(emprestimoRequestDto.livro_id());
        emprestimoEncontrado.setUsuarioId(emprestimoRequestDto.livro_id());
        emprestimoEncontrado.setData_emprestimo(emprestimoRequestDto.data_emprestimo());
        emprestimoEncontrado.setData_devolucao(emprestimoRequestDto.data_devolucao());

        return mapper.toDto(emprestimoEncontrado);
    }

    public void deletar(int id) throws SQLException{
        if (emprestimoRepository.buscarEmprestimoPorId(id) == null){
            throw new RuntimeException("O empréstimo não existe");
        }
        emprestimoRepository.deleteEmprestimo(id);
    }

    public void devolver(int id, LocalDate devolucao) throws SQLException{

        Emprestimo emprestimo = emprestimoRepository.buscarEmprestimoPorId(id);
        if (emprestimo == null){
            throw new RuntimeException("O empréstimo não existe");
        }
        emprestimoRepository.registrarDevolucao(id, devolucao);
    }

    public List<EmprestimoResponseDto> listarPorUsuario(int id_usuario) throws SQLException{

        return emprestimoRepository.listarEmprestimosPorIdUsuario(id_usuario)
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}
