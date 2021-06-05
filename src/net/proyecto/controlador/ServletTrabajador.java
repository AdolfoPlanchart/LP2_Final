package net.proyecto.controlador;

import java.io.IOException;
<<<<<<< HEAD
=======
import java.util.List;

>>>>>>> dev
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

<<<<<<< HEAD
=======
import net.proyecto.entidad.Trabajador;
>>>>>>> dev
import net.proyecto.service.TrabajadorService;

@WebServlet("/ServletTrabajador")
public class ServletTrabajador extends HttpServlet {
	private static final long serialVersionUID = 1L;
<<<<<<< HEAD

    public ServletTrabajador() {
        super();

    }


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cod;
		int salida;
		cod=request.getParameter("codigo");
		salida=new TrabajadorService().eliminarTrabajador(Integer.parseInt(cod));
		if(salida>0) 
			request.setAttribute("MENSAJE", "TRABAJADOR ELIMINADO");
			else
				request.setAttribute("MENSAJE",	"ERROR EN LA ELIMINACION");
								
			request.getRequestDispatcher("/consulta.jsp").forward(request, response);
	}
	

=======
	private TrabajadorService servicio;
    public ServletTrabajador() {
        super();
        servicio = new TrabajadorService();
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
	
	private void guardar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cod,nom,pat,mat,dir,dni,codCargo;
		cod=request.getParameter("codigo");
		nom=request.getParameter("nombre");
		pat=request.getParameter("paterno");
		mat=request.getParameter("materno");
		dir=request.getParameter("direccion");
		dni=request.getParameter("dni");
		codCargo=request.getParameter("cargo");
		//PASO 2: crear objeto de la clase Docente
		Trabajador bean=new Trabajador();
		//PASO 3: asignar valor a los atributos del objeto "bean" con las variables
		bean.setCod_trabajador(Integer.parseInt(cod));
		bean.setNom_trabajador(nom);
		bean.setApe_pat_trabajador(pat);
		bean.setApe_mat_trabajador(mat);
		bean.setDir_trabajador(dir);
		bean.setDni_trabajador(dni);
		bean.setCod_cargo(Integer.parseInt(codCargo));

		if(bean.getCod_trabajador()==0) {
			//invocar al método save
			int salida;
			salida=servicio.registrarTrabajador(bean);
			//validar salida
			if(salida>0) {
				request.setAttribute("MENSAJE","Trabajador registrado");
			}
			else {
				request.setAttribute("MENSAJE","Error en el registro de trabajador");
			}
		}
		else {
			//invocar al método update
			int salida;
			salida=servicio.actualizar(bean);
			//validar salida
			if(salida>0) {
				request.setAttribute("MENSAJE","Trabajador actualizado");
			}
			else {
				request.setAttribute("MENSAJE","Error al actualizar trabajador");
			}
		}
		listar(request,response);
	}
	
	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cod;
		cod=request.getParameter("codigoEliminar");
		int salida;
		salida=servicio.eliminarTrabajador(Integer.parseInt(cod));
		if(salida>0) {
			request.setAttribute("MENSAJE","Trabajador eliminado");
		}
		else {
			request.setAttribute("MENSAJE","Error al eliminar trabajador");
		}
		listar(request,response);
	}
	
	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Trabajador> data = servicio.listarTrabajadores();
		request.setAttribute("trabajadores", data);
		request.getRequestDispatcher("/trabajador.jsp").forward(request, response);
	}
>>>>>>> dev
}
