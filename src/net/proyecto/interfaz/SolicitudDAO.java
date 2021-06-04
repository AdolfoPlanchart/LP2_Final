package net.proyecto.interfaz;

import java.util.List;

import net.proyecto.entidad.Solicitud;
import net.proyecto.entidad.SolicitudxTrabajador;

public interface SolicitudDAO {
	public int save(Solicitud bean);
	public int update(Solicitud bean);
	public int delete(int cod);
	public List<SolicitudxTrabajador> listAll(int numPisos);
	public List<Solicitud> listAll();
}
