<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Visualização de Endereços</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <%@ page import="com.JavaCrud.dao.UsuarioDao, com.JavaCrud.bean.Enderecos, java.util.*" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <div class="container mt-5">
        <h1 class="display-4 text-center">Listagem de Endereços</h1>

        <%
        String pageid = request.getParameter("page");
        if (pageid == null) {
            pageid = "1";
        }
        int currentPage = Integer.parseInt(pageid);
        int total = 5;
        int start = (currentPage - 1) * total;

        int totalRecords = UsuarioDao.getTotalRecordsenderecos();
        int totalPages = (int) Math.ceil(totalRecords / (double) total);

        List<Enderecos> list = UsuarioDao.getRecordsenderecos(start, total);
        pageContext.setAttribute("list", list);
        pageContext.setAttribute("currentPage", currentPage);
        pageContext.setAttribute("totalPages", totalPages);
        %>

        <table class="table table-striped">
            <thead>
                <tr>
                    <th>ID do Usuário</th>
                    <th>CEP</th>
                    <th>Rua</th>
                    <th>Estado</th>
                    <th>Cidade</th>
                    <th>Editar</th>
                    <th>Excluir</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="endereco" items="${list}">
                    <tr>
                        <td>${endereco.idUsuario}</td>
                        <td>${endereco.cep}</td>
                        <td>${endereco.rua}</td>
                        <td>${endereco.estado}</td>
                        <td>${endereco.cidade}</td>
                        <td><a href="EditEndereco.jsp?id=${endereco.id}" class="btn btn-warning btn-sm">Editar</a></td>
                        <td><a href="Deletaendereco.jsp?id=${endereco.id}" class="btn btn-danger btn-sm">Excluir</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <nav aria-label="Page navigation example">
            <ul class="pagination">
                <c:forEach var="i" begin="1" end="${totalPages}">
                    <c:set var="activeClass" value="${i == currentPage ? 'active' : ''}"/>
                    <li class="page-item ${activeClass}">
                        <a class="page-link" href="ViewEnderecos.jsp?page=${i}">${i}</a>
                    </li>
                </c:forEach>
            </ul>
        </nav>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
