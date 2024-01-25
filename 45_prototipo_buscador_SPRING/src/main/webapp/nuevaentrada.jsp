<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Nueva entrada</title>
	</head>
	<body>
		<form action="agregar" method="POST">
			<input type="text" id="url" name="url" placeholder="url">
			<input type="text" id="tematica" name="tematica" placeholder="temática"> 
			<input type="text" id="descripcion" name="descripcion" placeholder="descripción">
			<input type="submit" value="Agregar">
		</form>
		<br>
	</body>
</html>