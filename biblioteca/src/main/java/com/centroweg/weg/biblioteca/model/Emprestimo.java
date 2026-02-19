package com.centroweg.weg.biblioteca.model;

import java.time.LocalDate;

public class Emprestimo {

    private int id;
    private int idLivro;
    private int idUsuario;
    private LocalDate data_emprestimo;
    private LocalDate data_devolucao;


    public Emprestimo(int id, int idLivro, int idUsuario, LocalDate data_emprestimo, LocalDate data_devolucao) {
        this.id = id;
        this.idLivro = idLivro;
        this.idUsuario = idUsuario;
        this.data_emprestimo = data_emprestimo;
        this.data_devolucao = data_devolucao;
    }

    public Emprestimo(int idLivro, int idUsuario, LocalDate data_emprestimo, LocalDate data_devolucao) {
        this.idLivro = idLivro;
        this.idUsuario = idUsuario;
        this.data_emprestimo = data_emprestimo;
        this.data_devolucao = data_devolucao;
    }

    public Emprestimo(int id, int idLivro, int idUsuario, LocalDate data_emprestimo) {
        this.id = id;
        this.idLivro = idLivro;
        this.idUsuario = idUsuario;
        this.data_emprestimo = data_emprestimo;
    }

    public Emprestimo(int idLivro, int idUsuario, LocalDate data_emprestimo) {
        this.idLivro = idLivro;
        this.idUsuario = idUsuario;
        this.data_emprestimo = data_emprestimo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public LocalDate getData_emprestimo() {
        return data_emprestimo;
    }

    public void setData_emprestimo(LocalDate data_emprestimo) {
        this.data_emprestimo = data_emprestimo;
    }

    public LocalDate getData_devolucao() {
        return data_devolucao;
    }

    public void setData_devolucao(LocalDate data_devolucao) {
        this.data_devolucao = data_devolucao;
    }
}
