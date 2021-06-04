package net.proyecto.service;

import java.util.List;

import net.proyecto.entidad.Trabajador;
import net.proyecto.fabrica.DAOFactory;
import net.proyecto.interfaz.TrabajadorDAO;

public class TrabajadorService {
	DAOFactory fabrica = DAOFactory.getDAOFactory(1);
	TrabajadorDAO trabajadorDAO = fabrica.getTrabajadorDAO();
	
	public List<Trabajador> listarTrabajadores() {
		return trabajadorDAO.listarTrabajadores();
	}
	
	public int registrarTrabajador(Trabajador bean) {
		return trabajadorDAO.registrarTrabajador(bean);
	}
	
	public int actualizar(Trabajador bean) {
		// TODO Auto-generated method stub
		return trabajadorDAO.actualizarTrabajador(bean);
	}
	
	public int eliminarTrabajador(int cod_trabajador) {
		return trabajadorDAO.eliminarTrabajador(cod_trabajador);
	}
}
