package net.proyecto.fabrica;

import net.proyecto.dao.MySqlSolicitudDAO;
import net.proyecto.dao.MySqlTrabajadorDAO;
import net.proyecto.interfaz.SolicitudDAO;
import net.proyecto.interfaz.TrabajadorDAO;

public class MySqlDAOFactory extends DAOFactory {
	@Override
	public TrabajadorDAO getTrabajadorDAO() {
		return new MySqlTrabajadorDAO();
	}
	
	@Override
	public SolicitudDAO getSolicitudDAO() {
		return new MySqlSolicitudDAO();
	}
}
