package net.proyecto.service;

import java.util.List;

import net.proyecto.entidad.ExpedienteGastos;

import net.proyecto.entidad.SolicitudxTrabajador;
import net.proyecto.fabrica.DAOFactory;
import net.proyecto.interfaz.ExpedienteDAO;


public class ExpedienteService {
	DAOFactory fabrica = DAOFactory.getDAOFactory(1);
	ExpedienteDAO ExpedienteDAO = fabrica.getExpedienteDAO();
	
	public List<SolicitudxTrabajador> listarSolcitud(int pisos) {
		return ExpedienteDAO.listAll(pisos);
	}
	
	public int agregar(ExpedienteGastos bean) {
		return ExpedienteDAO.save(bean);
	}
	public int actualizar(ExpedienteGastos bean) {
		return ExpedienteDAO.update(bean);
	}
	public int eliminar(int cod) {
		return ExpedienteDAO.delete(cod);
	}
	
	public List<ExpedienteGastos> listarTodos(){
		return ExpedienteDAO.listAll();
	}

}
