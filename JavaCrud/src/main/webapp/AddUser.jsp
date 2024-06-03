<%@ page import="com.JavaCrud.dao.UsuarioDao"  %>
<jsp:useBean id="u" class="com.JavaCrud.bean.Usuario"></jsp:useBean>
<jsp:setProperty property="*" name="u"/>
<% 
	int i = UsuarioDao.SalvarUsuario(u);

	if(i>0){
		response.sendRedirect("addUsersuccessfully.jsp");
	}else{
		response.sendRedirect("addUserunsuccessfully.jsp");
	}
%>