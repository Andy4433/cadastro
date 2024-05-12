package org.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexaoPostgreSQL {

    public Connection conectar() throws SQLException {
        System.out.println("Início PostgreSQLMySQL");
        Connection conexao = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/Cadastros",
                "postgres", "admin");

        try {
            if (!existeBancoDeDados(conexao, "Cadastros")) {
                criarBancoDeDados(conexao, "Cadastros");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return conexao;
    }

    private boolean existeBancoDeDados(Connection conexao, String nomeBanco) throws SQLException {
        Statement stmt = conexao.createStatement();
        String query = "SELECT 1 FROM pg_database WHERE datname = '" + nomeBanco + "'";
        return stmt.executeQuery(query).next();
    }

    private void criarBancoDeDados(Connection conexao, String nomeBanco) throws SQLException {
        Statement stmt = conexao.createStatement();
        String query = "CREATE DATABASE " + nomeBanco;
        stmt.executeUpdate(query);
        System.out.println("Banco de dados criado com sucesso: " + nomeBanco);
    }
}