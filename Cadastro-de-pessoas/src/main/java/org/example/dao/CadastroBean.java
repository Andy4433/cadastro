package org.example.dao;

import org.example.HelloBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.IOException;
import java.sql.*;

@ManagedBean(name = "CadastroBean")
@SessionScoped
public class CadastroBean {
    @ManagedProperty(value="#{HelloBean}")
    private HelloBean helloBean;

    public HelloBean getHelloBean() {
        return helloBean;
    }

    public void setHelloBean(HelloBean helloBean) {
        this.helloBean = helloBean;
    }

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

