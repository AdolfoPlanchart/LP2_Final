package net.proyecto.fabrica;

import net.proyecto.dao.MySqlTrabajadorDAO;
import net.proyecto.interfaz.TrabajadorDAO;

public class MySqlDAOFactory extends DAOFactory {
	@Override
	public TrabajadorDAO getTrabajadorDAO() {
		return new MySqlTrabajadorDAO();
	}
}
