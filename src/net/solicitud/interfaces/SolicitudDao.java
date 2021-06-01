package net.solicitud.interfaces;

import java.util.List;

import beans.Solicitud;

public interface SolicitudDao {
	public int save(Solicitud bean);
	public int update(Solicitud bean);
	public int delete(int cod);
	public List<Solicitud> listAll();

}
