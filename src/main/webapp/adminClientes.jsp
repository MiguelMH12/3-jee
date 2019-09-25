<%@page import="com.everis.poo.model.Cliente"%>
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
	<form action="./ClientesController" method="POST">
		<input type="text" name ="id" placeholder="Id del cliente..." required/>
		<br/>
		<input type="text" name ="nombre" placeholder="Nombre del cliente..." required/>
		<br/>
		<input type="text" name ="apaterno" placeholder="Apellido Paterno..." required/>
		<br/>
		<input type="text" name ="amaterno" placeholder="Apellido Materno..." required/>
		<br/>
		<input type="text" name ="rfc" placeholder="RFC..."/>
		<br/>
		<input type="submit" value="Guardar/Actualizar"/>
		<input type="reset" value="Limpiar"/>
	</form>
	<%
		List<Cliente> clientes = (List<Cliente>) request.getAttribute("listaClientes");
	%>
	<% if(clientes != null && clientes.size() > 0){ %>
	<table>
		<thead>
			<tr>
				<td>Id Cliente</td>
				<td>Nombre</td>
				<td>Apellido Paterno</td>
				<td>Apellido Materno</td>
				<td>RFC</td>
				<td>Eliminar?</td>
			</tr>
		</thead>
		<tbody>
			<% for (Cliente cliente : clientes){ %>
			<tr>
				<td><%= cliente.getIdCliente() %></td>
				<td><%= cliente.getNombre() %></td>
				<td><%= cliente.getApaterno() %></td>
				<td><%= cliente.getAmaterno() %></td>
				<td><%= cliente.getRfc() %></td>
				<td><a href=<%="./ClientesController?idClienteEliminar="+cliente.getIdCliente() %>>
				<button type="button">Eliminar</button>
				</a></td>
			</tr>
			<% } %>
		</tbody>
	</table>
	<% }%>
</body>
</html>