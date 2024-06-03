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
    <%@ page import ="com.JavaCrud.bean.Usuario , com.JavaCrud.dao.UsuarioDao"%>
    
    <%
    String id = request.getParameter("id");
    Usuario usuario = UsuarioDao.getRegistroById(Integer.parseInt(id));
    %>

    <div class="container mt-5">
        <h1 class="display-4 text-center">Editar Usuário</h1>
        <form action="UpdateUser.jsp" method="post" class="mt-4">
            <input type="hidden" name="id" value="<%=usuario.getId()%>" />
            <div class="form-group">
                <label for="nome">Nome</label>
                <input type="text" class="form-control" id="nome" name="nome" value="<%=usuario.getNome()%>">
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" class="form-control" id="email" name="email" value="<%=usuario.getEmail()%>">
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" class="form-control" id="password" name="password" value="<%=usuario.getPassword()%>">
            </div>
            <div class="form-group">
                <label>Sexo</label><br>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="sexo" id="masculino" value="Masculino" <%= "Masculino".equals(usuario.getSexo()) ? "checked" : "" %>>
                    <label class="form-check-label" for="masculino">Masculino</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="sexo" id="feminino" value="Feminino" <%= "Feminino".equals(usuario.getSexo()) ? "checked" : "" %>>
                    <label class="form-check-label" for="feminino">Feminino</label>
                </div>
            </div>
            <div class="form-group">
                <label for="pais">País</label>
                <input type="text" class="form-control" id="pais" name="pais" value="<%=usuario.getPais()%>">
            </div>
            <button type="submit" class="btn btn-primary">Atualizar</button>
        </form>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
