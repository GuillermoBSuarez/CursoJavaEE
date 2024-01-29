<%@page import="model.Producto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="model.Producto, java.util.List"%>
<%@taglib uri="jakarta.tags.core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<!-- los siguiente metas son para no- cache -->
<meta http-equiv="Expires" content="0">
<meta http-equiv="Last-Modified" content="0">
<meta http-equiv="Cache-Control" content="no-cache, mustrevalidate">
<meta http-equiv="Pragma" content="no-cache"> 

<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" >
</head>
<body>
	<h2>Productos de ${param.categoria} </h2>
	<table border="1">
		<tr>
			<th>Nombre</th>
			<th>Precio</th>
		</tr>
		<c:forEach var="pr" items="${productos}">
			<tr>
				<td>${pr.nombre}</td>
				<td>${pr.precio}</td>
			</tr>
		</c:forEach>
	</table>
	<a href="toMenu">Volver a home</a>
</body>
</html> 

