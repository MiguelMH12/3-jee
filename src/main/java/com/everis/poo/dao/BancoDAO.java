package com.everis.poo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.everis.jee.util.ConexionMySQL;
import com.everis.poo.model.Banco;

public class BancoDAO {

	//se tiene que quitar esta cosa
	private static List<Banco> bancos = new ArrayList<>();
	
	private ConexionMySQL conexion = new ConexionMySQL();
	
	public List<Banco> listar(){
		String sql = "SELECT * FROM banco";
		List<Banco> listaBancos = new ArrayList<Banco>();
		try {
			conexion.conectar();
			ResultSet rs = conexion.ejecutarConsulta(sql);
			while(rs.next()) {
				Banco banco = new Banco();
				banco.setIdBanco(rs.getInt("idBanco"));
				banco.setNombre(rs.getString("nombre"));
				banco.setDireccion(rs.getString("direccion"));
				listaBancos.add(banco);
			}
			conexion.desconectar();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return listaBancos;
	}
	
	public void insertar(Banco banco) {
		String sql = "INSERT INTO banco(nombre, direccion) VALUES ('"+banco.getNombre()+"','"+banco.getDireccion()+"')";
		try {
			conexion.conectar();
			conexion.ejecutar(sql);
			conexion.desconectar();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public Banco buscar(int idBanco) {
		String sql = "SELECT * FROM banco WHERE idbanco = "+idBanco;
		try {
			Banco banco = new Banco();
			conexion.conectar();
			ResultSet rs = conexion.ejecutarConsulta(sql);
			if(rs.next()) {
				banco.setIdBanco(rs.getInt("idBanco"));
				banco.setNombre(rs.getString("nombre"));
				banco.setDireccion(rs.getString("direccion"));
				conexion.desconectar();
				return banco;
			}
			conexion.desconectar();
			return null;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
	//se regresa boolean si se necesita confirmar si se hizo bien o no
	public boolean actualizar (Banco banco) {
		String sql = "UPDATE banco SET nombre = '"+banco.getNombre()+"', direccion = '"+banco.getDireccion()+"' WHERE idbanco = '"+banco.getIdBanco()+"'";
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
	
	public boolean eliminar(int idBanco) {
		String sql = "DELETE FROM banco WHERE idbanco = '"+idBanco+"'";
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
