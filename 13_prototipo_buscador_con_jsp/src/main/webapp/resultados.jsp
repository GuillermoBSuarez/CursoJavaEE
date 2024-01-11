<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.List,model.Resultado"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%List<Resultado> resultados=(List<Resultado>)request.getAttribute("paginas"); 
	if(resultados!=null){
		for(Resultado r:resultados){%>
			<h2><a href="<%=r.getUrl()%>"><%=r.getUrl()%></a></h2>
			<h4><%=r.getDescripcion() %></h4>
			<hr>
		<%}%>
	<%} %>
	<br>
	<a href="buscar.html">Volver</a>
</body>
</html>