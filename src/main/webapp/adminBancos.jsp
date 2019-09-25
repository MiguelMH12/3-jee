<%@page import="com.everis.poo.model.Banco"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="./BancosController" method="POST">
		<input type="text" name ="id" placeholder="Solo si se quiere modificar"/>
		<br/>
		<input type="text" name ="nombre" placeholder="Nombre del banco..."/>
		<br/>
		<textarea rows="10" cols="100" name="direccion" placeholder="Direccion..."></textarea>
		<br/>
		<input type="submit" value="Guardar/Actualizar"/>
		<input type="reset" value="Limpiar"/>
	</form>
	<%
		List<Banco> bancos = (List<Banco>) request.getAttribute("listaBancos");
	%>
	<% if(bancos != null && bancos.size() > 0){ %>
	<table>
		<thead>
			<tr>
				<td>Id Bancos</td>
				<td>Nombre</td>
				<td>Direccion</td>
				<td>Eliminar?</td>
			</tr>
		</thead>
		<tbody>
			<% for (Banco banco : bancos){ %>
			<tr>
				<td><%= banco.getIdBanco() %></td>
				<td><%= banco.getNombre() %></td>
				<td><%= banco.getDireccion() %></td>
				<td><a href=<%="./BancosController?idBancoEliminar="+banco.getIdBanco() %>>
				<button type="button">Eliminar</button>
				</a></td>
			</tr>
			<% } %>
		</tbody>
	</table>
	<% }%>
</body>
</html>