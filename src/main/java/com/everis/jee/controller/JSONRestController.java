package com.everis.jee.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.everis.poo.model.Cliente;
import com.everis.poo.service.ClienteService;
import com.google.gson.Gson;

/**
 * Servlet implementation class JSONRestController
 */
public class JSONRestController extends HttpServlet {
	//HTTPServlet implementala interfaz Serialization
	//Identificador para el tipo de objeto HttpServlet (sello)
	private static final long serialVersionUID = 1L;
    static ClienteService servicio = new ClienteService();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JSONRestController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		List<Cliente> clientes = servicio.listar();
    	
    	//Convertir este objeto a formato JSON
    	Gson gson = new Gson();
    	String json = gson.toJson(clientes);  //Convierte el objeto a JSON
    	
    	response.getWriter().append(json); //Lo mandamos como salida
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idEliminarDato = request.getParameter("idEliminar");  //En la vista enviamos como parametro, recibimos como parametro
		if(idEliminarDato != null) {
			int idEliminarEntero = Integer.parseInt(idEliminarDato);
			boolean exito = servicio.eliminar(idEliminarEntero);
			response.getWriter().append(exito + "");
			return;
		}
		
		/**
		 * Leer el request body de la peticion
		 * payload(json)
		 */
		
		StringBuilder sb = new StringBuilder(); //Concatenar cadena
		//Flujo de entrada que llega por la peticion http
		BufferedReader reader = request.getReader();
		String linea;
		while((linea = reader.readLine())  != null){
			sb.append(linea);   //pega cada linea al sb
		}
		String json = sb.toString();
		Gson gson = new Gson();
		Cliente cliente = gson.fromJson(json, Cliente.class);  //Convertimos el json a tipo cliente
		
		//Antes del gson, enviamos como string normales
		// TODO Auto-generated method stub
//		//Pasamos los valores del usuario
//		String nombre = request.getParameter("nombre");
//		String apaterno = request.getParameter("apaterno");
//		String amaterno = request.getParameter("amaterno");
//		String rfc = request.getParameter("rfc");
//		
//		Cliente cliente = new Cliente();
//		cliente.setNombre(nombre);
//		cliente.setApaterno(apaterno);
//		cliente.setAmaterno(amaterno);
//		cliente.setRfc(rfc);
		
		//INSERTAR/ACTUALIZAR
		if(cliente.getIdCliente() <= 0) {  //Si no existe el cliente lo inserta
			boolean exito = servicio.insertar(cliente);
			response.getWriter().append(exito + "");
		}else {
			boolean exito = servicio.actualizar(cliente);
			response.getWriter().append(exito + "");
		}
		
	}
}
