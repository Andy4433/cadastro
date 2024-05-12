package org.example.dao;

import jakarta.annotation.PostConstruct;
import org.example.HelloBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class DadosBean implements Serializable {
    private List<HelloBean> dadosList;

    @PostConstruct
    public void init() {
        dadosList = carregarDadosDoBanco();
    }

    private List<HelloBean> carregarDadosDoBanco() {
        List<HelloBean> dados = new ArrayList<>();
        // Aqui você faz a conexão com o banco e executa a consulta para obter os dados

        try {
            Connection conexao = new ConexaoPostgreSQL().conectar();
            String sql = "SELECT name, gender, age, cep, rua, estado, cidade FROM tabela_usuario INNER JOIN tabela_endereco ON tabela_usuario.id = tabela_endereco.id_usuario";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                HelloBean dado = new HelloBean();
                dado.setName(rs.getString("name"));
                dado.setGender(rs.getString("gender"));
                dado.setAge(rs.getInt("age"));
                dado.setCep(rs.getString("cep"));
                dado.setRua(rs.getString("rua"));
                dado.setEstado(rs.getString("estado"));
                dado.setCidade(rs.getString("cidade"));
                dados.add(dado);
            }

            rs.close();
            stmt.close();
            conexao.close();

        } catch (SQLException e) {
            e.printStackTrace();
            // Tratamento de exceção conforme necessário
        }

        return dados;
    }

    // Getters e Setters

    public List<HelloBean> getDadosList() {
        return dadosList;
    }

    public void setDadosList(List<HelloBean> dadosList) {
        this.dadosList = dadosList;
    }
}
