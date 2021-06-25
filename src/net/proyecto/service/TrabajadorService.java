package net.proyecto.service;

import java.util.List;

import net.proyecto.entidad.Menu;
import net.proyecto.entidad.Trabajador;
import net.proyecto.entidad.Usuario;
import net.proyecto.fabrica.DAOFactory;
import net.proyecto.interfaz.TrabajadorDAO;

public class TrabajadorService {
	DAOFactory fabrica = DAOFactory.getDAOFactory(1);
	TrabajadorDAO trabajadorDAO = fabrica.getTrabajadorDAO();
	
	public List<Trabajador> listarTrabajadores() {
		return trabajadorDAO.listarTrabajadores();
	}
	
	public List<Trabajador> listarTrabajadores(int cod_cargo) {
		return trabajadorDAO.listarTrabajadores(cod_cargo);
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
	
	public Trabajador iniciarSesion(String login,String psw) {
		return trabajadorDAO.inicarSesion(login, psw);
	}
	
	public List<Menu> getMenus(int cod_cargo) {
		return trabajadorDAO.getMenus(cod_cargo);
	}
}
