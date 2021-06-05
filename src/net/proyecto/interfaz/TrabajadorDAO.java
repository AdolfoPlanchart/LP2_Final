package net.proyecto.interfaz;

import java.util.List;

import net.proyecto.entidad.Trabajador;

public interface TrabajadorDAO {
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
