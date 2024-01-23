<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- API de JQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<title>Temas</title>
</head>
<body>
	<center>
		Seleccione tema:
		<select id="listaTemas" onchange="getLibrosTema()">
			<option value="0">-Todos-</option>
			<c:forEach var = "t" items="${requestScope.temas}">
				<option value = "${t.idTema}">${t.tema}</option>
			</c:forEach>
		</select>

		<br><br><br>
		
		<div id="tabla"></div>

		<br><br><br>
		
		<div id="carrito"></div>

		<script type="text/javascript">

		/* Ahora el código javascript recibe un DTO, no una entidad, y en el DTO no hay propiedad idTema sino todo un objeto Tema. */
		
			function getLibrosTema(){
				$.get(	"FrontController",
					  	{"operation":"doLibrosTema", "idTema":$("#listaTemas").val()},
					  	function(data){
						  	// Añadimos la columna Tema
						  	var tabla = "<table border='1'><tr><th>ISBN</th><th>Título</th><th>Autor</th><th>Precio</th><th>Páginas</th><th>Tema</th><th></th></tr>";
							$.each(data, function(i, p){
								// Añadimos el tema, que es una propiedad (tema) tendro de otra propiedad (TemaDTO) del objeto p.
								tabla += "<tr><td>"+p.isbn+"</td><td>"+p.titulo+"</td><td>"+p.autor+"</td><td>"+p.precio+"</td><td>"+p.paginas+"</td><td>"+p.temaDTO.tema+"</td><td><a href='#' onclick='agregar("+p.isbn+")'>Agregar</a></td></tr>";
							});
							tabla += "</table>";
							$("#tabla").html(tabla);}
				);
			}
								
			function agregar(isbn){
				var params = {"operation":"doAgregar", "isbn":isbn};
				pintarCarrito(params);
			}
			
			function quitar(pos){
				var params = {"operation":"doQuitar", "posicion":pos};
				pintarCarrito(params);
			}
			
			function pintarCarrito(params){
				$.get( "FrontController", params, function(data){
					
					// Añadimos la columna Tema
					var carrito = "<table border='1'><tr><th>ISBN</th><th>Título</th><th>Autor</th><th>Precio</th><th>Páginas</th><th>Tema</th><th></th></tr>";
					$.each(data, function(i, p){
						// Añadimos el tema, que es una propiedad (tema) tendro de otra propiedad (TemaDTO) del objeto p.
						carrito += "<tr><td>"+p.isbn+"</td><td>"+p.titulo+"</td><td>"+p.autor+"</td><td>"+p.precio+"</td><td>"+p.paginas+"</td><td>"+p.temaDTO.tema+"</td><td><a href='#' onclick='quitar("+i+")'>Quitar</a></td></tr>";
					});
					carrito += "</table>";
					$("#carrito").html(carrito);}
				)
			}
		</script>
	</center>
</body>
</html>