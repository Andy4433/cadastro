package org.example.dao;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.example.dao.ConexaoPostgreSQL;
import org.example.HelloBean;

@ManagedBean(name = "CadastroBean")
public class CadastroBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private HelloBean helloBean = new HelloBean();

    // Getters e Setters do helloBean (remova-os se não forem necessários)

    public String cadastrar() {
        try {
            ConexaoPostgreSQL conexaoPostgreSQL = new ConexaoPostgreSQL();
            Connection conexao = conexaoPostgreSQL.conectar();

            // Faça o que precisar com o HelloBean
            System.out.println("Dados do HelloBean:");
            String sqlUsuario = "INSERT INTO tabela_usuario (nome, idade, genero) VALUES (?, ?, ?)";
            PreparedStatement stmtUsuario = conexao.prepareStatement(sqlUsuario);
            stmtUsuario.setString(1, helloBean.getName());
            stmtUsuario.setInt(2, helloBean.getAge());
            stmtUsuario.setString(3, helloBean.getGender());
            stmtUsuario.executeUpdate();
            stmtUsuario.close();

            // Obter o id gerado para a tabela_usuario
            int idUsuario = 0;
            PreparedStatement stmtIdUsuario = conexao.prepareStatement("SELECT LASTVAL()");
            ResultSet rsIdUsuario = stmtIdUsuario.executeQuery();
            if (rsIdUsuario.next()) {
                idUsuario = rsIdUsuario.getInt(1);
            }
            rsIdUsuario.close();
            stmtIdUsuario.close();

            // Inserir dados na tabela_endereco
            String sqlEndereco = "INSERT INTO tabela_endereco (id_usuario, cep, rua, estado, cidade) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmtEndereco = conexao.prepareStatement(sqlEndereco);
            stmtEndereco.setInt(1, idUsuario);
            stmtEndereco.setString(2, helloBean.getCep());
            stmtEndereco.setString(3, helloBean.getRua());
            stmtEndereco.setString(4, helloBean.getEstado());
            stmtEndereco.setString(5, helloBean.getCidade());
            stmtEndereco.executeUpdate();
            stmtEndereco.close();

            conexao.close();

            FacesContext.getCurrentInstance().getExternalContext().redirect("dados.xhtml");
            return "sucesso";
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            return "erro";
        }
    }
}
