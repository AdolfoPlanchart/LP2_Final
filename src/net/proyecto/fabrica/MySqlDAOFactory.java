package net.proyecto.fabrica;

import net.proyecto.dao.MySqlCargoDAO;
import net.proyecto.dao.MySqlSolicitudDAO;
import net.proyecto.dao.MySqlTrabajadorDAO;
import net.proyecto.interfaz.CargoDAO;
import net.proyecto.interfaz.ExpedienteDAO;
import net.proyecto.interfaz.SolicitudDAO;
import net.proyecto.interfaz.TrabajadorDAO;

public class MySqlDAOFactory extends DAOFactory {
	@Override
	public TrabajadorDAO getTrabajadorDAO() {
		return new MySqlTrabajadorDAO();
	}

	@Override
	public CargoDAO getCargoDAO() {
		// TODO Auto-generated method stub
		return new MySqlCargoDAO();
	}
	
	@Override
	public SolicitudDAO getSolicitudDAO() {
		return new MySqlSolicitudDAO();
	}

	@Override
	public ExpedienteDAO getExpediente() {
		// TODO Auto-generated method stub
		return null;
	}
}
