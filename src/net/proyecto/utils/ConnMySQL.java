package net.proyecto.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnMySQL {
	public static Connection getConexion() throws ClassNotFoundException {
		Connection conn = null;
		try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Proyecto_LP22?" + 
												   "user=root&password=mysql");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Error en: " + e.getMessage());
			}
		return conn;
	}
}
