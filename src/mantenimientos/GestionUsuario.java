package mantenimientos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.BeanUsuario;
import conexiones.ConnMySQL;
import interfaces.InterfaceUsuario;

public class GestionUsuario implements InterfaceUsuario {
	@Override
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
			System.out.println("Error en GestionUsuario.validarLogin: " + e.getMessage());
		} 
		return usuario;
	}

	@Override
	public ArrayList<BeanUsuario> listarUsuarios() {
		BeanUsuario usuario = null;
		ArrayList<BeanUsuario> listaUsuarios = null;
		Connection conn= null;
		PreparedStatement stmt = null;
		String sql = null;
		
		try {
			conn = ConnMySQL.getConexion();
			sql = "SELECT * FROM usuarios";
			stmt = conn.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				usuario = new BeanUsuario();
				usuario.setUid(Integer.parseInt(rs.getString("uid")));
				usuario.setName(rs.getString("name"));
				usuario.setLastName(rs.getString("last_name"));
				usuario.setEmail(rs.getString("email"));
				usuario.setPhoneNumber(rs.getString("phone_number"));
				
				listaUsuarios.add(usuario);
			}
		} catch(Exception e) {
			System.out.println("Error en GestionUsuario.listarUsuarios: " + e.getMessage());
		}
		return listaUsuarios;
	}

	@Override
	public boolean registrarUsuario(BeanUsuario usuario) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = null;
		
		boolean seRegistro = false;
		
		try {
			conn = ConnMySQL.getConexion();
			sql = "INSERT INTO usuarios (name,last_name,email,phone_number,pswrd) VALUES(?,?,?,?,?);";
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1,usuario.getName());
			stmt.setString(2,usuario.getLastName());
			stmt.setString(3,usuario.getEmail());
			stmt.setString(4,usuario.getPhoneNumber());
			stmt.setString(5,usuario.getPsw());
			
			seRegistro = stmt.executeUpdate() > 0;
			stmt.close();
			conn.close();
		} catch(SQLException e) {
			System.out.println("Error SQL en GestionUsuario.registrarUsuario: " + e.getMessage());
			System.out.println("Codigo de Error: " + e.getErrorCode());
		} catch(Exception e) {
			System.out.println("Error en GestionUsuario.registrarUsuario: " + e.getMessage());
		}
		return seRegistro;
	}

	@Override
	public boolean eliminarUsuario() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean actualizarUsuario() {
		// TODO Auto-generated method stub
		return false;
	}
 }
