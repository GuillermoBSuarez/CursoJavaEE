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
		<%int num=Integer.parseInt(request.getParameter("numero")); %>
		<h1>Tabla del <%=num%></h1>
		<table border="1">
			<%for(int i=1;i<=10;i++){ %>
				<tr>
					<td><%=i%>X<%=num%></td>
					<td><%=i*num%></td>
				</tr>
			<%} %>
		</table>
	</center>
	<br>
	<a href="numero.html">Volver</a>
</body>
</html>