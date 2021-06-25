package net.proyecto.service;

import java.util.List;

import net.proyecto.entidad.Detalle;
import net.proyecto.entidad.GenerarProyecto;
import net.proyecto.fabrica.DAOFactory;
import net.proyecto.interfaz.GenerarProyectoDAO;

public class GenerarProyectoService {
    private DAOFactory fabrica=DAOFactory.getDAOFactory(1);
    private GenerarProyectoDAO dao=fabrica.getGenerarProyectoDAO();
    
    public int saveProyecto(GenerarProyecto bean, List<Detalle> lista) {
    	return dao.registrarProyecto(bean, lista);
    }
}
