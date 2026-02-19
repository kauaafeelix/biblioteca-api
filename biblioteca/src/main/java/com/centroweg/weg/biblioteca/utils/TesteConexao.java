package com.centroweg.weg.biblioteca.utils;

import java.sql.SQLException;

public class TesteConexao {
    public static void main(String[] args) {

        try{
            Conexao.conectar();
            System.out.println("Conexão bem sucedida!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao se conectar ao banco de dados.");
        }
    }
}
