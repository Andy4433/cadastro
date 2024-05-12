package org.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexaoPostgreSQL {

    public Connection conectar() throws SQLException {
        Connection conexao = null;
        try {
            // Registrar o driver JDBC
            Class.forName("org.postgresql.Driver");

            // Conectar ao banco de dados "postgres"
            conexao = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/cadastros",
                    "postgres", "xxxxxxx");

            if (!existeBancoDeDados(conexao, "cadastros")) {
                criarBancoDeDados(conexao, "cadastros");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            try {
                throw e;
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
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
