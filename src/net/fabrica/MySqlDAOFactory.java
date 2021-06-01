package net.fabrica;

import net.solicitud.interfaces.SolicitudDao;
import solicitud.dao.MySqlSolicitudDAO;

public class MySqlDAOFactory {
	public SolicitudDao getSolicitudDao() {
		// TODO Auto-generated method stub
		return new MySqlSolicitudDAO();
	}

	

}
