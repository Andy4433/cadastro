<%@ page import="com.JavaCrud.dao.UsuarioDao" %>
<jsp:useBean id="u" class="com.JavaCrud.bean.Enderecos" scope="request" />
<jsp:setProperty property="*" name="u"/>
<%
	int i = UsuarioDao.updateEndereco(u);
	response.sendRedirect("ViewEnderecos.jsp");
%>