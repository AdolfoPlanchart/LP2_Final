package net.proyecto.interfaz;

import java.util.List;

import net.proyecto.entidad.Detalle;
import net.proyecto.entidad.GenerarProyecto;

public interface GenerarProyectoDAO {

	public int registrarProyecto(GenerarProyecto bean, List<Detalle> lista);
}
