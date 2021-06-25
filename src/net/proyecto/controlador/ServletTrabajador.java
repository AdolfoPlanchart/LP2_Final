package net.proyecto.controlador;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.proyecto.entidad.Menu;
import net.proyecto.entidad.Trabajador;
import net.proyecto.service.TrabajadorService;

@WebServlet("/ServletTrabajador")
public class ServletTrabajador extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private TrabajadorService servicio;
    public ServletTrabajador() {
        super();
        servicio = new TrabajadorService();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("ACCION");
		if(accion.equals("LISTAR"))
			listar(request,response);
		else if(accion.equals("LISTARxCARGO"))
			listarPorCargo(request,response);
		else if(accion.equals("GUARDAR"))
			guardar(request,response);
		else if(accion.equals("ELIMINAR"))
			eliminar(request,response);
		else if(accion.equals("LOGIN"))
			iniciarSesion(request,response);
		else if(accion.equals("LOGOUT"))
			cerrarSesion(request,response);
	}

	private void listarPorCargo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cargo;
		cargo = request.getParameter("filtroCargo");		
		List<Trabajador> data = servicio.listarTrabajadores(Integer.parseInt(cargo));
		request.setAttribute("filtrando", true);
		request.setAttribute("filtro", true);
		request.setAttribute("trabajadores", data);
		request.getRequestDispatcher("/trabajador.jsp").forward(request, response);
	}
	private void guardar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cod,nom,pat,mat,dir,dni,codCargo,correo,psw;
		cod=request.getParameter("codigo");
		nom=request.getParameter("nombre");
		pat=request.getParameter("paterno");
		mat=request.getParameter("materno");
		dir=request.getParameter("direccion");
		dni=request.getParameter("dni");
		codCargo=request.getParameter("cargo");
		correo=request.getParameter("correo");
		psw=request.getParameter("psw");
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
		bean.setCorreo(correo);
		bean.setClave(psw);
		
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

	private void iniciarSesion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String correo = request.getParameter("correo");
		String psw = request.getParameter("psw");
		
		Trabajador bean = servicio.iniciarSesion(correo, psw);
		
		if(bean == null) {
			request.setAttribute("MENSAJE", "Usuario y/o clave incorrecto.");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		} else {
			HttpSession session = request.getSession();
			
			List<Menu> lista = servicio.getMenus(bean.getCod_cargo());
			session.setAttribute("MENUS", lista);
			session.setAttribute("DATOS",bean);
			request.getRequestDispatcher("/menu.jsp").forward(request, response);
		}
	}
	private void cerrarSesion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		request.setAttribute("MENSAJE", "Sesion cerrada.");
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}
}
