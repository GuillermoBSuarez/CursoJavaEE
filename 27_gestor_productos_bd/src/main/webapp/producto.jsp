<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Producto</title>
</head>
<body>
	<center>
		<h3>Nombre: ${requestScope.producto.nombre}</h3>
		<h3>Nombre: ${requestScope.producto.categoria}</h3>
		<h3>Nombre: ${requestScope.producto.precio}</h3>
	</center>
	<br>
	<a href="FrontController?operation=toInicio">Volver</a>
</body>
</html>