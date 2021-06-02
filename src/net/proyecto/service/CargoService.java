package net.proyecto.service;

import java.util.List;

import net.proyecto.entidad.Cargo;
import net.proyecto.fabrica.DAOFactory;
import net.proyecto.interfaz.CargoDAO;

public class CargoService {
	DAOFactory fabrica = DAOFactory.getDAOFactory(1);
	CargoDAO cargoDAO = fabrica.getCargoDAO();
	
	public List<Cargo> listarCargos() {
		return cargoDAO.listarCargos();
	}
}
