<%@ page import="com.JavaCrud.dao.UsuarioDao" %>
<jsp:useBean id="u" class="com.JavaCrud.bean.Usuario" scope="request" />
<jsp:setProperty property="*" name="u"/>
<%
int i = UsuarioDao.updateUsuario(u);
response.sendRedirect("ViewUsuarios.jsp");
%>
