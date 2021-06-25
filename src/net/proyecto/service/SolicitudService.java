package net.proyecto.service;

import java.util.List;

import net.proyecto.entidad.Solicitud;
import net.proyecto.entidad.SolicitudxTrabajador;

import net.proyecto.fabrica.DAOFactory;
import net.proyecto.interfaz.SolicitudDAO;


public class SolicitudService {
	DAOFactory fabrica = DAOFactory.getDAOFactory(1);
	SolicitudDAO SolicitudDAO = fabrica.getSolicitudDAO();
	
	public List<SolicitudxTrabajador> listarSolcitud(int pisos) {
		return SolicitudDAO.listAll(pisos);
	}
	
	public int agregar(Solicitud bean) {
		return SolicitudDAO.save(bean);
	}
	public int actualizar(Solicitud bean) {
		return SolicitudDAO.update(bean);
	}
	public int eliminar(int cod) {
		return SolicitudDAO.delete(cod);
	}
	
	public List<Solicitud> listarTodos(){
		return SolicitudDAO.listAll();
	}
	
}
