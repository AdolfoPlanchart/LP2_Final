package interfaces;

import java.util.ArrayList;

import beans.BeanUsuario;

public interface InterfaceUsuario {
	public ArrayList<BeanUsuario> listarUsuarios();
	public boolean registrarUsuario(BeanUsuario usuario);
	public boolean eliminarUsuario();
	public boolean actualizarUsuario();
	public BeanUsuario validarLogin(String email, String psw);
}
