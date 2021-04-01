package mantenimientos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import beans.BeanUsuario;
import conexiones.ConnMySQL;

public class GestionUsuario {
	public BeanUsuario validarLogin(String email, String psw){
		BeanUsuario usuario = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = null;
		
		try {
			conn = ConnMySQL.getConexion();
			sql = "SELECT * FROM usuarios WHERE email = ? AND pswrd = ?;";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, email);
			stmt.setString(2, psw);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				System.out.println(rs.getString("name"));
				usuario = new BeanUsuario();
				usuario.setName(rs.getString("name"));
				usuario.setLastName(rs.getString("last_name"));
			}
		} catch(Exception e) {
			System.out.println("Error en GestionUsuario: " + e.getMessage());
		} 
		return usuario;
	}
 }
