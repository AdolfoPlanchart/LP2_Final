package net.proyecto.interfaz;

import java.util.List;

import net.proyecto.entidad.ExpedienteGastos;
import net.proyecto.entidad.SolicitudxTrabajador;

public interface ExpedienteDAO {
	public int save(ExpedienteGastos bean);
	public int update(ExpedienteGastos bean);
	public int delete(int cod);
	public List<SolicitudxTrabajador> listAll(int numPisos);
	public List<ExpedienteGastos> listAll();

}
