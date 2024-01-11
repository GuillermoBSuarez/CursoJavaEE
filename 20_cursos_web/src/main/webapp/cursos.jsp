<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="model.Curso,java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<center>
		<h1>Cursos cuyo precio máximo es ${param.precio}</h1>
		<br>
		<table border="1">
			<tr>
				<th>Nombre</th>
				<th>Duración</th>
				<th>Precio</th>
			</tr>
			<%--recuperamos el arraylist de cursos que está en un atributo de petición,
			lo recorremos y mostramos una fila por cada curso --%>
			<%ArrayList<Curso> cursos=(ArrayList<Curso>)request.getAttribute("cursos"); 
			for(Curso c:cursos){%>
				<tr>
					<td><%=c.getNombre() %></td>
					<td><%=c.getDuracion() %></td>
					<td><%=c.getPrecio() %></td>
				</tr>
			<%} %>
		</table>
		<br>
		<a href="menu.html">volver</a>
	</center>
</body>
</html>