package com.JavaCrud.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.JavaCrud.bean.Usuario;

public class UsuarioDao {
	
	public static Connection getConnection() throws SQLException  {
		Connection con = null;
		
		try {
			Class.forName("org.postgresql.Driver");	
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","xxxxxxx");
			
			if (!Readydb(con, "cadastros")) {
                Createdb(con, "cadastros");
                con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/cadastros","postgres","xxxxxxx");
                CreateTable(con);
            } else {
            	con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/cadastros","postgres","xxxxxxx");
            	if (!ReadyTable(con)) {
            		CreateTable(con);
                }
            }
			
		} catch(Exception e) {
			System.out.println(e);
		}
		
		return con;
	}
	
	private static boolean Readydb(Connection con, String nomeBanco) throws SQLException {
        Statement stmt = con.createStatement();
        String query = "SELECT 1 FROM pg_database WHERE datname = '" + nomeBanco + "'";
        return stmt.executeQuery(query).next();
    }

    private static void Createdb(Connection con, String nomeBanco) throws SQLException {
        Statement stmt = con.createStatement();
        String query = "CREATE DATABASE " + nomeBanco;
        stmt.executeUpdate(query);
        System.out.println("Banco de dados criado com sucesso: " + nomeBanco);
    }
	
    private static boolean ReadyTable(Connection con) throws SQLException {
        String query = "SELECT 1 FROM information_schema.tables WHERE table_name = 'usuario'";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            return stmt.executeQuery().next();
        }
    }
    
    private static void CreateTable(Connection con) throws SQLException {
        String SqlCreateTable = "CREATE TABLE IF NOT EXISTS usuario ("
    			+ "id SERIAL PRIMARY KEY, "
    			+ "nome VARCHAR(100) NOT NULL, "
    			+ "password VARCHAR(100) NOT NULL, "
    			+ "email VARCHAR(100) NOT NULL, "
    			+ "sexo VARCHAR(100) NOT NULL, "
    			+ "pais VARCHAR(100) NOT NULL"
    			+ ");";
    	
        try (PreparedStatement stmtTabelaUsuario = con.prepareStatement(SqlCreateTable)) {
            stmtTabelaUsuario.executeUpdate();
            System.out.println("Tabela 'usuario' criada com sucesso.");
        }
    }
    
    public static int SalvarUsuario(Usuario u) {
		int status=0;
		
		try {
			Connection con = getConnection();
    		PreparedStatement ps = con.prepareStatement("INSERT INTO usuario (nome, password, email, sexo, pais) VALUES (?,?,?,?,?)");
			ps.setString(1, u.getNome());
			ps.setString(2, u.getPassword());
			ps.setString(3, u.getEmail());
			ps.setString(4, u.getSexo());
			ps.setString(5, u.getPais());
			status= ps.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
    	return status;
    	
    }
    
    public static int DeletarUsuario(Usuario u) {
		int status =0;
    	try {
    		Connection con = getConnection();
    		PreparedStatement ps = con.prepareStatement("DELETE FROM usuario WHERE id=?");
    		ps.setInt(1, u.getId());
    		status=ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e);
		}
    	return status;
    	
    }
    
    public static Usuario getRegistroById(int id) {
		Usuario usuario = null;
		try {
			Connection con = getConnection();
    		PreparedStatement ps = con.prepareStatement("SELECT * FROM usuario WHERE id=?");
    		ps.setInt(1, id);
    		ResultSet rs = ps.executeQuery();
    		
    		while (rs.next()) {
				usuario= new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
    			usuario.setEmail(rs.getString("email"));
    			usuario.setPais(rs.getString("pais"));
    			usuario.setSexo(rs.getString("sexo"));
    		
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
    	
    	return usuario;
    	
    }
    
    public static int updateUsuario(Usuario u ) {
		int status = 0;
		
		try {
			Connection con = getConnection();
    		PreparedStatement ps = con.prepareStatement("UPDATE usuario set nome=?, password=?, email=?, sexo=?, pais=? WHERE id=?");
			ps.setString(1, u.getNome());
			ps.setString(2, u.getPassword());
			ps.setString(3, u.getEmail());
			ps.setString(4, u.getSexo());
			ps.setString(5, u.getPais());
			ps.setInt(6, u.getId());
			status= ps.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
    	return status;
    	
    }
    
    public static List<Usuario> getAllUsuario() throws SQLException {
    	List<Usuario> list = new ArrayList<Usuario>();
    	try {
    		Connection con = getConnection();
    		PreparedStatement ps = con.prepareStatement("SELECT * FROM usuario");
    		ResultSet rs = ps.executeQuery();
    		
    		while(rs.next()) {
    			Usuario usuario = new Usuario();
    			usuario.setId(rs.getInt("id"));
    			usuario.setNome(rs.getString("nome"));
    			usuario.setEmail(rs.getString("email"));
    			usuario.setPais(rs.getString("pais"));
    			usuario.setSexo(rs.getString("sexo"));
    			list.add(usuario);
    		}
    	} catch (Exception e) {
			System.out.println(e);
		}
		return list;
		}
    
    public static int getTotalRecords() {
        int total = 0;
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM usuario");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return total;
    }
    
    public static List<Usuario> getRecords(int start, int total) {
    	List<Usuario> list = new ArrayList<Usuario>();
    	try {
    		Connection con = getConnection();
    		PreparedStatement ps = con.prepareStatement("SELECT * FROM usuario LIMIT ? OFFSET ?");
    		ps.setInt(1, total);
    		ps.setInt(2, start);
    		ResultSet rs = ps.executeQuery();
    		
    		while(rs.next()) {
    			Usuario usuario = new Usuario();
    			usuario.setId(rs.getInt("id"));
    			usuario.setNome(rs.getString("nome"));
    			usuario.setEmail(rs.getString("email"));
    			usuario.setPais(rs.getString("pais"));
    			usuario.setSexo(rs.getString("sexo"));
    			list.add(usuario);
    		}
    		con.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
    	return list;
    }
}

