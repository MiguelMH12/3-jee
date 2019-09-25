package com.everis.jee.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.everis.poo.model.Cliente;
import com.everis.poo.service.ClienteService;

/**
 * Servlet implementation class ClientesController
 */
public class ClientesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static ClienteService servicio = new ClienteService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientesController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idEliminar = request.getParameter("idClienteEliminar");
		if(idEliminar != null) {
			int idBanco = Integer.parseInt(idEliminar);
			servicio.eliminar(idBanco);
		}
		List<Cliente> lista = servicio.listar();
		request.setAttribute("listaClientes", lista);
		RequestDispatcher rd = request.getRequestDispatcher("adminClientes.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		String nombre = request.getParameter("nombre");
		String apaterno = request.getParameter("apaterno");
		String amaterno = request.getParameter("amaterno");
		String rfc = request.getParameter("rfc");
		
		Cliente cliente = new Cliente();
		
		cliente.setIdCliente(id);
		cliente.setNombre(nombre);
		cliente.setApaterno(apaterno);
		cliente.setAmaterno(amaterno);
		cliente.setRfc(rfc);
		
		Cliente actualizar = servicio.buscar(id);
		boolean exito;
		if(actualizar != null) {
			exito = servicio.actualizar(cliente);
		}else {
			exito = servicio.insertar(cliente);
		}
		if(exito) {
			doGet(request, response);
		}
		else {
			response.getWriter().append(" No se pudo efectuar la operacion ");
		}
	}

}
