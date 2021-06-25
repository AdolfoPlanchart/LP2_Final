package net.proyecto.controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import net.proyecto.entidad.Solicitud;
import net.proyecto.entidad.SolicitudxTrabajador;
import net.proyecto.service.SolicitudService;

/**
 * Servlet implementation class ServletSolicitudCrud
 */
@WebServlet("/ServletSolicitudCrud")
public class ServletSolicitudCrud extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SolicitudService sSolicitud;
    /**
     * Default constructor. 
     */
    public ServletSolicitudCrud() {
        // TODO Auto-generated constructor stub
    	 super();
         //crear objeto sDocente
    	 sSolicitud=new SolicitudService();
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String tipo=request.getParameter("ACCION");
		//evaluar
		if(tipo.equals("LISTAR"))
			listado(request,response);
		else if(tipo.equals("REGISTRAR"))
			registrar(request,response);
		else if(tipo.equals("Eliminar"))
			eliminar(request,response);
	}

	private void listado(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		List<Solicitud> data=sSolicitud.listarTodos();
		//enviar al cliente la respuesta
		//PASO 1: crear un atributo
		request.setAttribute("docentes", data);
		//PASO 2: direccionar a la página docente.jsp
		request.getRequestDispatcher("/Solicitud.jsp").forward(request, response);
	
	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String cod;
		cod=request.getParameter("codigo");
		//invocar al mètodo delete
		int salida;
		salida=sSolicitud.eliminar(Integer.parseInt(cod));
		//validar salida
		if(salida>0) {// SE ELIMINO CORRECTAMENTE
			//crear un atributo MENSAJE
			request.setAttribute("MENSAJE","Solicitud eliminado");
		}
		else {// ERROR AL ELIMINAR
			request.setAttribute("MENSAJE","Error al eliminar docente");
		}
		listado(request,response);
		
	}

	private void registrar(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String cod,fech,des,codtra,estado;
		cod=request.getParameter("codigo");
		fech=request.getParameter("fecha");
		des=request.getParameter("des");
		codtra=request.getParameter("codTrabajador");
		estado=request.getParameter("estado");
		//PASO 2: crear objeto de la clase Docente
		Solicitud bean=new Solicitud();
		//PASO 3: asignar valor a los atributos del objeto "bean" con las variables
		bean.setCodigo(Integer.parseInt(cod));
		bean.setFecha(fech);
		bean.setDescripcion(des);
		bean.setCodigoTrabajador(Integer.parseInt(codtra));
		bean.setEstado(estado);
		//PASO 4: validar atributo código
		if(bean.getCodigo()==0) {
			//invocar al método save
			int salida;
			salida=sSolicitud.agregar(bean);
			//validar salida
			if(salida>0) {
				//crear un atributo MENSAJE
				request.setAttribute("MENSAJE","Solicitud registrada");
			}
			else {
				request.setAttribute("MENSAJE","Error en el registro de solicitud");
			}
		}
		else {
			int salida;
			salida=sSolicitud.actualizar(bean);
			//validar salida
			if(salida>0) {// SE ACTUALIZO CORRECTAMENTE
				request.setAttribute("MENSAJE","Solicitud actualizada");
			}
			else {// ERROR AL ACTUALIZAR
				request.setAttribute("MENSAJE","Error al actualizar solicitud");
			}
		}
		listado(request,response);
	}

}
