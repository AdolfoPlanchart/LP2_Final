package net.proyecto.service;

import java.util.List;

import net.proyecto.entidad.Expediente;
import net.proyecto.fabrica.DAOFactory;
import net.proyecto.interfaz.ExpedienteDAO;

public class ExpedienteService {
     DAOFactory fabrica = DAOFactory.getDAOFactory(1);
     ExpedienteDAO expedienteDAO = fabrica.getExpediente();
     
     public List<Expediente>listarExpedientes(){
		return expedienteDAO.listarExpedientes();
     }
}
