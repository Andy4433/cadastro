<%@ page import="com.JavaCrud.dao.UsuarioDao"%>
<jsp:useBean id="u" class="com.JavaCrud.bean.Usuario"></jsp:useBean>
<jsp:setProperty property="*" name="u"/>

<%
	UsuarioDao.DeletarUsuario(u);
	response.sendRedirect("ViewUsuarios.jsp");
%>