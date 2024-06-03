<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index Page</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f9f9f9;
        margin: 0;
        padding: 0;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
    }
    .container {
        text-align: center;
        background-color: #ffffff;
        padding: 20px;
        border-radius: 10px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }
    h1 {
        color: #333;
    }
    .button {
        display: inline-block;
        margin: 10px;
        padding: 10px 20px;
        font-size: 16px;
        color: #fff;
        background-color: #28a745;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        text-decoration: none;
    }
    .button-red {
        background-color: #dc3545;
    }
    .button:hover {
        background-color: #218838;
    }
    .button-red:hover {
        background-color: #c82333;
    }
</style>
</head>
<body>
    <div class="container">
        <h1>Bem-vindo ao Sistema de Cadastro de Usuários</h1>
        <a href="Usuarioform.html" class="button">Criar Novo Usuário</a>
        <a href="ViewUsuarios.jsp" class="button button-red">Consultar Usuários</a>
        <a href="ViewEnderecos.jsp" class="button button-red">Consultar endereço</a>
    </div>
</body>
</html>
