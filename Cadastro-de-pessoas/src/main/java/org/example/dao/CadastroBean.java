package org.example.dao;

import org.example.HelloBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@ManagedBean
@SessionScoped
public class CadastroBean {
    private HelloBean helloBean;

    public String cadastrar() {
        try {
            ConexaoPostgreSQL conexaoPostgreSQL = new ConexaoPostgreSQL();
            Connection conexao = conexaoPostgreSQL.conectar();

            conexao.close();

            FacesContext.getCurrentInstance().getExternalContext().redirect("dados.xhtml");
            return "sucesso";
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            return "erro";
        }
    }

    // Getters e Setters para o HelloBean
}