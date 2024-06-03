<%@ page import="com.JavaCrud.dao.UsuarioDao"  %>
<jsp:useBean id="u" class="com.JavaCrud.bean.Usuario"></jsp:useBean>
<jsp:setProperty property="*" name="u"/>
<jsp:useBean id="v" class="com.JavaCrud.bean.Enderecos"></jsp:useBean>
<jsp:setProperty property="*" name="v"/>

<% 
	int i = UsuarioDao.SalvarUsuario(u, v);

	if(i>0){
		response.sendRedirect("addUsersuccessfully.jsp");
	}else{
		response.sendRedirect("addUserunsuccessfully.jsp");
	}
%>