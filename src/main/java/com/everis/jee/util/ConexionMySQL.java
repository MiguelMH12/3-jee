package com.everis.jee.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionMySQL {
	
	private String url = "jdbc:mysql://tgconsulting.online:3306/academiaeveris?serverTimezone=UTC";
	private String usuario = "academiaeveris";
	private String password = "academiaeveris";
	private Connection conexion;
	private Statement sentencia;
	
	public boolean conectar() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection(url, usuario, password);
			sentencia = conexion.createStatement();
			return true;
		}catch(Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}
	
	public ResultSet ejecutarConsulta(String sql) throws SQLException {
		return sentencia.executeQuery(sql);
	}
	
	public int ejecutar(String sql) throws SQLException {
		return sentencia.executeUpdate(sql);
	}
	
	public boolean desconectar() {
		try {
			sentencia.close();
			conexion.close();
			return true;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}
}
