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
	public int registrarCargo(Cargo bean) {
		return cargoDAO.registrarCargo(bean);
	}
	public int actualizarCargo(Cargo bean) {
		return cargoDAO.actualizarCargo(bean);
	}
	public int eliminarCargo(int cod_cargo) {
		return cargoDAO.eliminarCargo(cod_cargo);
	}
}
