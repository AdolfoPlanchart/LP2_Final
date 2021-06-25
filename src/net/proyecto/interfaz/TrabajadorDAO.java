package net.proyecto.interfaz;

import java.util.List;

import net.proyecto.entidad.Menu;
import net.proyecto.entidad.Trabajador;

public interface TrabajadorDAO {
	/* Inicar Sesion */
	public Trabajador inicarSesion(String login, String psw);
	
	/* Traer menus */
	public List<Menu> getMenus(int cod_cargo);
	
	/* Listar todos los Trabajadores */
	public List<Trabajador> listarTrabajadores();
	
	/* Listar Trabajadores por cargo */
	public List<Trabajador> listarTrabajadores(int cod_cargo);
	
	/* Registrar Trabajador */
	public int registrarTrabajador(Trabajador bean);
	
	/* Actualizar Trabajador */
	public int actualizarTrabajador(Trabajador bean);
	
	/* Eliminar Trabajador */
	public int eliminarTrabajador(int cod_trabajador);

}
