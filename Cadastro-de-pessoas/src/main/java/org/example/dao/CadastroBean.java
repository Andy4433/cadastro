package org.example.dao;

import org.example.HelloBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@ManagedBean(name = "CadastroBean")
@SessionScoped
public class CadastroBean {
    @Inject
    private HelloBean helloBean;

    public String cadastrar() {
        try {
            ConexaoPostgreSQL conexaoPostgreSQL = new ConexaoPostgreSQL();
            Connection conexao = conexaoPostgreSQL.conectar();

            // Faça o que precisar com o HelloBean
            System.out.println("Dados do HelloBean:");
            System.out.println("Nome: " + helloBean.getName());
            System.out.println("Gênero: " + helloBean.getGender());
            System.out.println("Idade: " + helloBean.getAge());
            System.out.println("CEP: " + helloBean.getCep());
            System.out.println("Número: " + helloBean.getNum());
            System.out.println("Rua: " + helloBean.getRua());
            System.out.println("Estado: " + helloBean.getEstado());
            System.out.println("Cidade: " + helloBean.getCidade());

            conexao.close();

            FacesContext.getCurrentInstance().getExternalContext().redirect("dados.xhtml");
            return "sucesso";
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            return "erro";
        }
    }
}

