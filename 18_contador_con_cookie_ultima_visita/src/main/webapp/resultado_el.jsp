<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<center>
	<%-- --%>
		
		<h1>Has entrado <%=session.getAttribute("contador") %> veces</h1>
		<h2>Número total de visitas a mi página:<%=application.getAttribute("global") %></h2>
		<h3>Fecha de tu anterior visita: ${!empty cookie.fecha?cookie.fecha.value:"primera visita"}</h3>
		<br>
		<a href="inicio.html">Volver</a>
	</center>
</body>
</html>