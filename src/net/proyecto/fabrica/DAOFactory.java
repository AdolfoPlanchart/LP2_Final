package net.proyecto.fabrica;

import net.proyecto.interfaz.CargoDAO;
import net.proyecto.interfaz.ExpedienteDAO;
import net.proyecto.interfaz.GenerarProyectoDAO;
import net.proyecto.interfaz.SolicitudDAO;
import net.proyecto.interfaz.TrabajadorDAO;

public abstract class DAOFactory {
	public static final int MYSQL = 1;
    public static final int ORACLE = 2;
    public static final int DB2 = 3;
    public static final int SQLSERVER = 4;
    public static final int XML = 5;
    
    public abstract TrabajadorDAO getTrabajadorDAO();
    public abstract CargoDAO getCargoDAO();
    public abstract SolicitudDAO getSolicitudDAO();
    public abstract ExpedienteDAO getExpedienteDAO();
    public abstract GenerarProyectoDAO  getGenerarProyectoDAO();
    public static DAOFactory getDAOFactory(int whichFactory){
        switch(whichFactory){
       	case MYSQL:
       		try {
        	   	return new MySqlDAOFactory();
       		} catch (Exception e) {
       			System.out.println("Error en DAOFactory: " + e.getMessage());
       		}
        	case XML:
        	    //return new XmlDAOFactory();
        	case ORACLE:
        	    return null;//OracleDAOFactory();
        	/*case DB2:
        	    return new Db2DAOFactory();*/
        	case SQLSERVER:
        	    return null;
        	/*case XML:
        	    return new XmlDAOFactory();*/
        	default:
        	    return null;
        }
    }
}
