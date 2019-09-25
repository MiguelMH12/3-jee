package com.everis.poo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.everis.jee.util.ConexionMySQL;
import com.everis.poo.model.Cliente;

public class ClienteDAO {
	//Asi se trabaja orientado a la interfaz List, por si se quiere solo cambiar el tipo de objeto que construye y solo se implementan metodos de la interfaz pero dependiendo de la definicion de cada clase de la interfaz
	//Operado diamante para especificar que almacena la lista, se puede omitir el segundo desde Java 7
	//private static List<Cliente> clientes = new ArrayList<>();//por ahora, al conectar a BD no sera necesario
	
	private ConexionMySQL conexion = new ConexionMySQL();
	
	public List<Cliente> listar(){
		String sql = "SELECT * FROM cliente ORDER BY nombre";
		List<Cliente> listaClientes = new ArrayList<Cliente>();
		try {
			conexion.conectar();
			ResultSet rs = conexion.ejecutarConsulta(sql);
			while(rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setIdCliente(rs.getInt("idcliente"));
				cliente.setAmaterno(rs.getString("amaterno"));
				cliente.setApaterno(rs.getString("apaterno"));
				cliente.setNombre(rs.getString("nombre"));
				cliente.setRfc(rs.getString("rfc"));
				listaClientes.add(cliente);
			}
			conexion.desconectar();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return listaClientes;
	}
	
	public void insertar(Cliente cliente) {
		String sql = "INSERT INTO cliente(nombre, apaterno, amaterno, rfc, idbanco) VALUES ('"+cliente.getNombre()+"','"+cliente.getApaterno()+"','"+cliente.getAmaterno()+"','"+cliente.getRfc()+"',1)";
		try {
			conexion.conectar();
			conexion.ejecutar(sql);
			conexion.desconectar();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public Cliente buscar(int idCliente) {
		String sql = "SELECT * FROM cliente WHERE idcliente = "+idCliente;
		try {
			Cliente cliente = new Cliente();
			conexion.conectar();
			ResultSet rs = conexion.ejecutarConsulta(sql);
			if(rs.next()) {
				cliente.setIdCliente(rs.getInt("idBanco"));
				cliente.setNombre(rs.getString("nombre"));
				cliente.setApaterno(rs.getString("apaterno"));
				cliente.setAmaterno(rs.getString("amaterno"));
				cliente.setRfc(rs.getString("rfc"));
				conexion.desconectar();
				return cliente;
			}
			conexion.desconectar();
			return null;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
	//se regresa boolean si se necesita confirmar si se hizo bien o no
	public boolean actualizar (Cliente cliente) {
		String sql = "UPDATE cliente SET nombre = '"+cliente.getNombre()+"', apaterno = '"+cliente.getApaterno()+"', amaterno = '"+cliente.getAmaterno()+"', rfc = '"+cliente.getRfc()+"' WHERE idcliente = '"+cliente.getIdCliente()+"'";
		try {
			conexion.conectar();
			conexion.ejecutar(sql);
			conexion.desconectar();
			return true;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
			
		}
	}
	
	public boolean eliminar(int idCliente) {
		String sql = "DELETE FROM cliente WHERE idcliente = '"+idCliente+"'";
		try {
			conexion.conectar();
			conexion.ejecutar(sql);
			conexion.desconectar();
			return true;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}
}
