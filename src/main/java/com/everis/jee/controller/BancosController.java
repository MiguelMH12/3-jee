package com.everis.jee.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.everis.poo.model.Banco;
import com.everis.poo.model.Cliente;
import com.everis.poo.service.BancoService;

/**
 * Servlet implementation class BancosController
 */
public class BancosController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static BancoService servicio = new BancoService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BancosController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * peticion hecha directamente desde la URL del navegador
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idEliminar = request.getParameter("idBancoEliminar");
		if(idEliminar != null) {
			int idBanco = Integer.parseInt(idEliminar);
			servicio.eliminar(idBanco);
		}
		List<Banco> bancos = servicio.listar();
		//para mandar parametro
		request.setAttribute("listaBancos", bancos);
		RequestDispatcher rd = request.getRequestDispatcher("adminBancos.jsp");
		rd.forward(request, response);
	}

	/**
	 * No se puede enviar directamente en navegador, se debe usar un cliente http
	 * (Postman)
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		//para recibir parametro
		int id = 0;
		if(!request.getParameter("id").equals("")) {
			id = Integer.parseInt(request.getParameter("id"));
		}
		String nombre = request.getParameter("nombre");
		String direccion = request.getParameter("direccion");
		
		Banco banco = new Banco();
		banco.setNombre(nombre);
		banco.setDireccion(direccion);/*falta terminar modificar*/
		banco.setIdBanco(id);
		
		Banco actualizar = servicio.buscar(id);
		boolean exito;
		if(actualizar != null) {
			exito = servicio.actualizar(banco);
		}else {
			exito = servicio.insertar(banco);
		}
		if(exito) {
			doGet(request, response);
		}
		else {
			response.getWriter().append(" No se pudo efectuar la operacion ");
		}
		
	}

}
