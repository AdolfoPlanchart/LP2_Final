package net.proyecto.interfaz;

import java.util.List;

import net.proyecto.entidad.Solicitud;

public interface SolicitudDAO {
	public int save(Solicitud bean);
	public int update(Solicitud bean);
	public int delete(int cod);
	public List<Solicitud> listAll();
}
