package net.proyecto.controlador;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.proyecto.entidad.Cargo;
import net.proyecto.entidad.Trabajador;
import net.proyecto.service.CargoService;

/**
 * Servlet implementation class ServletCargo
 */
@WebServlet("/ServletCargo")
public class ServletCargo extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CargoService servicio;
    public ServletCargo() {
        super();
        servicio = new CargoService();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("ACCION");
		if(accion.equals("LISTAR"))
			listar(request,response);
		else if(accion.equals("GUARDAR"))
			guardar(request,response);
		else if(accion.equals("ELIMINAR"))
			eliminar(request,response);
	}
	
	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Cargo> data = servicio.listarCargos();
		request.setAttribute("cargos", data);
		request.getRequestDispatcher("/cargo.jsp").forward(request, response);
	}
	private void guardar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cod,desc;
		cod=request.getParameter("codigo");
		desc=request.getParameter("descripcion");
		//PASO 2: crear objeto de la clase Docente
		Cargo bean=new Cargo();
		//PASO 3: asignar valor a los atributos del objeto "bean" con las variables
		bean.setCod_cargo(Integer.parseInt(cod));
		bean.setDesc_cargo(desc);

		if(bean.getCod_cargo()==0) {
			//invocar al método save
			int salida;
			salida=servicio.registrarCargo(bean);
			//validar salida
			if(salida>0) {
				request.setAttribute("MENSAJE","Cargo registrado");
			}
			else {
				request.setAttribute("MENSAJE","Error en el registro de cargo");
			}
		}
		else {
			//invocar al método update
			int salida;
			salida=servicio.actualizarCargo(bean);
			//validar salida
			if(salida>0) {
				request.setAttribute("MENSAJE","Cargo actualizado");
			}
			else {
				request.setAttribute("MENSAJE","Error al actualizar cargo");
			}
		}
		listar(request,response);
	}
	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cod;
		cod=request.getParameter("codigoEliminar");
		int salida;
		salida=servicio.eliminarCargo(Integer.parseInt(cod));
		if(salida>0) {
			request.setAttribute("MENSAJE","Cargo eliminado");
		}
		else {
			request.setAttribute("MENSAJE","Error al eliminar cargo");
		}
		listar(request,response);
	}
}
