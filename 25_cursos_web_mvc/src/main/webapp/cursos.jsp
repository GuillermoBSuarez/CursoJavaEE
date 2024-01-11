<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="model.Curso,java.util.ArrayList"%>
<%@taglib uri="jakarta.tags.core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<!-- los siguiente metas son para no- cache -->
<meta http-equiv="Expires" content="0">
<meta http-equiv="Last-Modified" content="0">
<meta http-equiv="Cache-Control" content="no-cache, mustrevalidate">
<meta http-equiv="Pragma" content="no-cache"> 
<body>
	<div style=center>
		<h1>Cursos cuyo precio máximo es ${param.precio}</h1>
		<br>
		<table border="1">
			<tr>
				<th>Nombre</th>
				<th>Duración</th>
				<th>Precio</th>
			</tr>
			
			<c:forEach var="c" items="${requestScope.cursos}">
				<tr>
					<td>${c.nombre}</td>
					<td>${c.duracion}</td>
					<td>${c.precio}</td>
				</tr>
			</c:forEach>
		</table>
		<br>
		<a href="FrontController?operation=toInicio">volver</a>
	</div>
</body>
</html>