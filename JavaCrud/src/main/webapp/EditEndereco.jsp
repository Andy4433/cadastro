<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Editar Usuário</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <%@ page import ="com.JavaCrud.bean.Enderecos , com.JavaCrud.dao.UsuarioDao"%>
    
    <%
    String id = request.getParameter("id");
    Enderecos endereco = UsuarioDao.getRegistroByIdEndereco(Integer.parseInt(id));
    %>

    <div class="container mt-5">
        <h1 class="display-4 text-center">Editar Endereço</h1>
        <form action="UpdateEndereco.jsp" method="post" class="mt-4">
            <input type="hidden" name="id" value="<%= endereco.getId() %>">

            <div class="form-group">
                <label for="cep">CEP</label>
                <input type="text" class="form-control" id="cep" name="cep" value="<%= endereco.getCep() %>" required>
            </div>
            <div class="form-group">
                <label for="rua">Rua</label>
                <input type="text" class="form-control" id="rua" name="rua" value="<%= endereco.getRua() %>" required>
            </div>
            <div class="form-group">
                <label for="estado">Estado</label>
                <input type="text" class="form-control" id="estado" name="estado" value="<%= endereco.getEstado() %>" required>
            </div>
            <div class="form-group">
                <label for="cidade">Cidade</label>
                <input type="text" class="form-control" id="cidade" name="cidade" value="<%= endereco.getCidade() %>" required>
            </div>
            
            <button type="submit" class="btn btn-primary">Atualizar</button>
        </form>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>