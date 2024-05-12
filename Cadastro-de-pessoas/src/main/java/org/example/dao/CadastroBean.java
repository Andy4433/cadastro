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

    public String cadastrar() {
        try (Connection conexao = new ConexaoPostgreSQL().conectar()) {

            // Criar tabela se não existir
            String sqlTabelaUsuario = "CREATE TABLE IF NOT EXISTS tabela_usuario (id SERIAL PRIMARY KEY, nome VARCHAR(100), idade INTEGER, genero VARCHAR(1))";
            try (PreparedStatement stmtTabelaUsuario = conexao.prepareStatement(sqlTabelaUsuario)) {
                stmtTabelaUsuario.executeUpdate();

            }


            String sqlUsuario = "INSERT INTO tabela_usuario (nome, idade, genero) VALUES (?, ?, ?)";
            try (PreparedStatement stmtUsuario = conexao.prepareStatement(sqlUsuario, PreparedStatement.RETURN_GENERATED_KEYS)) {
                stmtUsuario.setString(1, helloBean.getName());
                stmtUsuario.setInt(2, helloBean.getAge());
                stmtUsuario.setString(3, helloBean.getGender());
                stmtUsuario.executeUpdate();

                // Obter o id gerado para a tabela_usuario
                ResultSet generatedKeys = stmtUsuario.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int idUsuario = generatedKeys.getInt(1);
                    // Agora você pode usar o idUsuario para outras operações
                } else {
                    throw new SQLException("Falha ao obter o ID gerado para tabela_usuario.");
                }
            }
            // Obter o id gerado para a tabela_usuario
            int idUsuario;
            try (PreparedStatement stmtIdUsuario = conexao.prepareStatement("SELECT LASTVAL()");
                 ResultSet rsIdUsuario = stmtIdUsuario.executeQuery()) {
                rsIdUsuario.next();
                idUsuario = rsIdUsuario.getInt(1);
            }

            // Criar tabela_endereco se não existir
            String sqlTabelaEndereco = "CREATE TABLE IF NOT EXISTS tabela_endereco (id SERIAL PRIMARY KEY, id_usuario INTEGER REFERENCES tabela_usuario(id), cep VARCHAR(10), rua VARCHAR(100), estado VARCHAR(2), cidade VARCHAR(100))";
            try (PreparedStatement stmtTabelaEndereco = conexao.prepareStatement(sqlTabelaEndereco)) {
                stmtTabelaEndereco.executeUpdate();
            }

            // Inserir dados na tabela_endereco
            String sqlEndereco = "INSERT INTO tabela_endereco (id_usuario, cep, rua, estado, cidade) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stmtEndereco = conexao.prepareStatement(sqlEndereco)) {
                stmtEndereco.setInt(1, idUsuario);
                stmtEndereco.setString(2, helloBean.getCep());
                stmtEndereco.setString(3, helloBean.getRua());
                stmtEndereco.setString(4, helloBean.getEstado());
                stmtEndereco.setString(5, helloBean.getCidade());
                stmtEndereco.executeUpdate();
            }

            // Redirecionar para página dados.xhtml em caso de sucesso
            FacesContext.getCurrentInstance().getExternalContext().redirect("dados.xhtml");
            return "sucesso";
        } catch (IOException e) {
            e.printStackTrace();
            return "erro";
        } catch (SQLException e) {
            e.printStackTrace();
            return "erro";
        }
    }
}

