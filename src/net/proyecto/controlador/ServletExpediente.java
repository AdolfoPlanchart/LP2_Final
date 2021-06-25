package net.proyecto.controlador;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.proyecto.entidad.ExpedienteGastos;

import net.proyecto.service.ExpedienteService;


/**
 * Servlet implementation class ServletExpediente
 */
@WebServlet("/ServletExpediente")
public class ServletExpediente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ExpedienteService sExpediente;
       
    public ServletExpediente() {
    	super();
        //crear objeto sDocente
   	 sExpediente=new ExpedienteService();
       
        // TODO Auto-generated constructor stub
    }

	
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
	private void listado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<ExpedienteGastos> data=sExpediente.listarTodos();
		//enviar al cliente la respuesta
		//PASO 1: crear un atributo
		request.setAttribute("docentes", data);
		//PASO 2: direccionar a la página docente.jsp
		request.getRequestDispatcher("/Expediente.jsp").forward(request, response);
	}


	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String cod;
		cod=request.getParameter("codigo");
		//invocar al mètodo delete
		int salida;
		salida=sExpediente.eliminar(Integer.parseInt(cod));
		//validar salida
		if(salida>0) {// SE ELIMINO CORRECTAMENTE
			//crear un atributo MENSAJE
			request.setAttribute("MENSAJE","Expediente eliminado");
		}
		else {// ERROR AL ELIMINAR
			request.setAttribute("MENSAJE","Error al eliminar docente");
		}
		listado(request,response);
		
	}


	private void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub	String cod,fech,des,codtra,estado;
		String cod,fech,des,codtra,estado;
		cod=request.getParameter("codigo");
		codtra=request.getParameter("soli");
		fech=request.getParameter("fecha");
		des=request.getParameter("des");
		estado=request.getParameter("estado");
		//PASO 2: crear objeto de la clase Docente
		ExpedienteGastos bean=new ExpedienteGastos();
		//PASO 3: asignar valor a los atributos del objeto "bean" con las variables
		bean.setCodigo(Integer.parseInt(cod));
		bean.setCodigoSolicitud(Integer.parseInt(codtra));
		bean.setFecha(fech);
		bean.setDescripcion(des);
		bean.setEstado(estado);
		//PASO 4: validar atributo código
		if(bean.getCodigo()==0) {//INSERTTTTTTTTTTTTTT
			//invocar al método save
			int salida;
			salida=sExpediente.agregar(bean);
			//validar salida
			if(salida>0) {// SE INSERTO CORRECTAMENTE
				//crear un atributo MENSAJE
				request.setAttribute("Solicitud","Docente registrado");
			}
			else {// ERROR AL INSERTAR
				request.setAttribute("Solcitud","Error en el registro de docente");
			}
		}
		else {//UPDATEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
			//invocar al método update
			int salida;
			salida=sExpediente.actualizar(bean);
			//validar salida
			if(salida>0) {// SE ACTUALIZO CORRECTAMENTE
				request.setAttribute("MENSAJE","Docente actualizado");
			}
			else {// ERROR AL ACTUALIZAR
				request.setAttribute("MENSAJE","Error al actualizar docente");
			}
		}
		listado(request,response);
		
	}


	
}
