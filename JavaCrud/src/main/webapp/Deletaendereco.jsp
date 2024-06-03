<%@ page import="com.JavaCrud.dao.UsuarioDao"%>
<jsp:useBean id="u" class="com.JavaCrud.bean.Enderecos"></jsp:useBean>
<jsp:setProperty property="*" name="u"/>

<%
	UsuarioDao.DeletarEndereco(u);
	response.sendRedirect("ViewEnderecos.jsp");
%>