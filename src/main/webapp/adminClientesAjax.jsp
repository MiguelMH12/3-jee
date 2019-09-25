<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Clientes AJAX</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script> 
	//Cuando termina de cargar lapagina actual, se ejecuta la funcion anonima
	$(document).ready(function(){
			//alert("Hola Mundo");
			$.ajax({
					url : "http://localhost:8080/3-jee/clientes",
					success : function(respuesta){
						//alert(clientes); //Imprime el json de clientes
						var clientes = JSON.parse(respuesta);
						$.each(clientes, function(i){  //For
							//alert(clientes[i].nombre); Es castroso
							console.log(clientes[i].nombre);  //Se muestran en consola
							$('#comboClientes').append('<option value="'+clientes[i].idCliente+'">'+clientes[i].nombre+'</option>');
						}) //each
					}  //success
			})  //ajax
			$('#btnguardar').click(function(){
				//Crea objetos asignando lo que venga del campo de texto
				var cliente = {
								nombre : $('#txtnombre').val(),
								apaterno : $('#txtapaterno').val(),
								amaterno : $('#txtamaterno').val(),
								idCliente : $('#txtidcliente').val(),  //el objeto tiene nombre de JSONRestController > INSERTAR/ACTUALIZAR
								rfc : $('#txtrfc').val()
						      }
			    //Convierte el objeto cliente de javascript a cadena JSON
			    var json = JSON.stringify(cliente);
			    $.ajax({
			    	url : "http://localhost:8080/3-jee/clientes",
			    	type: "post",
			    	dataType : "json",
			    	contentType : "application/json",
			    	data : json, //cadena json
					success : function(respuesta){
						alert(respuesta);
					}
				})  //ajax
				    
			})  //click guardar
			
			$('#btneliminar').click(function(){
				var idCliente = $('#comboClientes').val();  //Obtenemos el valor del id del combo
				
			    $.ajax({
			    	url : "http://localhost:8080/3-jee/clientes?idEliminar=" + idCliente,  //Enviamos como parametro
			    	type: "post",
			    	contentType : "application/",
					success : function(respuesta){
						alert(respuesta);
					}
			    }) //ajax eliminar
			}) //click eliminar
		});  //ready
</script>
</head>
<body>
	<select id="comboClientes">
		<option value="0">Selecciona un cliente</option>
	</select>
	<br/>
	<input type="button" value="Eliminar" id="btneliminar"/>
	<br/>
	<input type="number" id="txtidcliente" value="0" placeholder="ID Cliente ..."/>
	<br/>
	<input type="text" id="txtnombre" placeholder="Nombre ..."/>
	<br/>
	<input type="text" id="txtapaterno" placeholder="A Paterno ..."/>
	<br/>
	<input type="text" id="txtamaterno" placeholder="A materno ..."/>
	<br/>
	<input type="text" id="txtrfc" placeholder="RFC ..."/>
	<br/>
	<input type="button" value="Guardar" id="btnguardar"/>
	
</body>
</html>