package net.proyecto.interfaz;

import java.util.List;

import net.proyecto.entidad.Cargo;

public interface CargoDAO {
	public List<Cargo> listarCargos();
	public int registrarCargo(Cargo bean);
	public int actualizarCargo(Cargo bean);
	public int eliminarCargo(int cod_cargo);
}
