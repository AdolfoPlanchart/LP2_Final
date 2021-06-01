package net.proyecto.interfaz;

import java.util.ArrayList;

import net.proyecto.entidad.Usuario;

public interface UsuarioDAO {
	public ArrayList<Usuario> listarUsuarios();
	public boolean registrarUsuario(Usuario usuario);
	public boolean eliminarUsuario();
	public boolean actualizarUsuario();
	public Usuario validarLogin(String email, String psw);
}
