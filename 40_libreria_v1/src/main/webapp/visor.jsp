<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Temas</title>
</head>
<body>
	<center>
		Seleccione tema:
		<select>
			<option value="0">-Todos-</option>
			<c:forEach var = "t" items="${requestScope.temas}">
				<option value = "${t.idTema}">${t.tema}</option>
			</c:forEach>
		</select>
	</center>
</body>
</html>