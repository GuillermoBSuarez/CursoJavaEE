<!DOCTYPE html>
<html>
<head>
<!-- los siguiente metas son para no- cache -->
<meta http-equiv="Expires" content="0">
<meta http-equiv="Last-Modified" content="0">
<meta http-equiv="Cache-Control" content="no-cache, mustrevalidate">
<meta http-equiv="Pragma" content="no-cache"> 
<meta charset="ISO-8859-1">
<title>Crear Producto</title>
<style type="text/css">
body {
	color: purple;
}

h1 {
	text-align: center;
}

div {
	width: 400px;
	margin: 50px auto;
	padding: 15px;
	background-color: smocke;
	border: 1px solid purple; input { display : block;
	margin-bottom: 15px;
}

input {
	color: purple;
	margin: 0 auto; 
	display : block;
	margin-bottom: 15px;
	border: 1px solid purple;
	padding: 7px;
	display: block;
}}
</style>
</head>
<body>
	<div>
		<h1>A�adir nuevo producto</h1>
		<form action="agregar" method="post">
			<input type="text" name="nombre" placeholder="Nombre">
			<input type="text" name="precio" placeholder="Precio"> 
			<input type="text" name="categoria" placeholder="Categor�a"> 
			<input type="submit" value="A�adir">
		</form>
	</div>
</body>
</html>