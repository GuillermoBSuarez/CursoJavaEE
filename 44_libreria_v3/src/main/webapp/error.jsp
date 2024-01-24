<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<!-- los siguiente metas son para no- cache -->
<meta http-equiv="Expires" content="0">
<meta http-equiv="Last-Modified" content="0">
<meta http-equiv="Cache-Control" content="no-cache, mustrevalidate">
<meta http-equiv="Pragma" content="no-cache"> 
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h3>El usuario <%=request.getParameter("usuario") %> con contraseña 
	<%=request.getParameter("password") %>, no existe</h3>
	<br><br>
	<a href="FrontController?operation=toLogin">Volver</a>
</body>
</html>