package org.example.dao;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.example.dao.ConexaoPostgreSQL;
import org.example.HelloBean;

@ManagedBean(name = "CadastroBean")
@RequestScoped
public class CadastroBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private HelloBean helloBean = new HelloBean();

    // Getters e Setters do helloBean (remova-os se não forem necessários)

    public String cadastrar(int age, String name, String gender, String cep, int num, String rua, String estado, String cidade) {
        try (Connection conexao = new ConexaoPostgreSQL().conectar()) {

            // Criar tabela se não existir
            String sqlTabelaUsuario = "CREATE TABLE IF NOT EXISTS tabela_usuario (id SERIAL PRIMARY KEY, nome VARCHAR(100), idade INTEGER, genero VARCHAR(100) )";
            try (PreparedStatement stmtTabelaUsuario = conexao.prepareStatement(sqlTabelaUsuario)) {
                stmtTabelaUsuario.executeUpdate();

            }


            String sqlUsuario = "INSERT INTO tabela_usuario (nome, idade, genero) VALUES (?, ?, ?)";
            try (PreparedStatement stmtUsuario = conexao.prepareStatement(sqlUsuario, PreparedStatement.RETURN_GENERATED_KEYS)) {
                System.out.println("ola");
                stmtUsuario.setString(1, name);
                stmtUsuario.setInt(2, age);
                stmtUsuario.setString(3, gender);
                stmtUsuario.executeUpdate();

                // Obter o id gerado para a tabela_usuario
                ResultSet generatedKeys = stmtUsuario.getGeneratedKeys();
                int idUsuario;
                if (generatedKeys.next()) {
                    idUsuario = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Falha ao obter o ID gerado para tabela_usuario.");
                }

                String sqlTabelaEndereco = "CREATE TABLE IF NOT EXISTS tabela_endereco (id SERIAL PRIMARY KEY, id_usuario INTEGER REFERENCES tabela_usuario(id), cep VARCHAR(10), rua VARCHAR(100), estado VARCHAR(2), cidade VARCHAR(100))";
                try (PreparedStatement stmtTabelaEndereco = conexao.prepareStatement(sqlTabelaEndereco)) {
                    stmtTabelaEndereco.executeUpdate();
                }

                // Inserir dados na tabela_endereco
                String sqlEndereco = "INSERT INTO tabela_endereco (id_usuario, cep, rua, estado, cidade) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement stmtEndereco = conexao.prepareStatement(sqlEndereco)) {
                    stmtEndereco.setInt(1, idUsuario);
                    stmtEndereco.setString(2, cep);
                    stmtEndereco.setString(3, rua);
                    stmtEndereco.setString(4, estado);
                    stmtEndereco.setString(5, cidade);
                    stmtEndereco.executeUpdate();
                }

                // Redirecionar para página dados.xhtml em caso de sucesso
                FacesContext.getCurrentInstance().getExternalContext().redirect("dados.xhtml");
                return "sucesso";
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            return "erro";
        }
    }
}

