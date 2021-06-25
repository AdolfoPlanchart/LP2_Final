package net.proyecto.controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import net.proyecto.entidad.Detalle;
import net.proyecto.entidad.GenerarProyecto;
import net.proyecto.service.GenerarProyectoService;

@WebServlet("/ServletGenerarProyecto")
public class ServletGenerarProyecto extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public ServletGenerarProyecto() {
        super();
       
    }


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tipo=request.getParameter("accion");
		     if(tipo.equals("AGREGAR"))
			agregar(request,response);
		else if(tipo.equals("ELIMINAR"))
			eliminar(request,response);
		else if(tipo.equals("GENERAR"))
			generar(request,response);
		
	}


	private void agregar(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// obtener parametros
		 String cod,des;
		 cod=request.getParameter("codigo");
		 des=request.getParameter("descripcion");
		 //sesion
		 HttpSession session=request.getSession(); 
		 //declarar arreglo de objetos de la clase detalle
		 List<Detalle> lista=null;
		 
		 //validar si  existe el atributo "detalle" dentro del objeto "session"
		 if(session.getAttribute("detalle")==null) {//no existe atributo atributo detalle dentro del objeto session
			 //crear arreglo de objetos "lista"
			 lista=new ArrayList<Detalle>(); 
		 }
		 else {//si existe atributo atributo detalle dentro del objeto session
			 //obtener el valor del atributo "detalle" y guardarlo dentro de lista
			 lista=(List<Detalle>) session.getAttribute("detalle");
		 }
		 
		 //crear objeto "det" de la clase detalle
		 Detalle det=new Detalle();
		 //asignar valor a los atributos del objeto det
		 det.setCod_resolu(Integer.parseInt(cod));
		 det.setDesc_expediente(des);
		 //adicionar objeto "det"dentro de "lista"
		 lista.add(det);
		 
		 //crear atributo "Detalle(dentro del objeto session)" con el valor de "lista"
		 session.setAttribute("detalle", lista);
		 Gson gson=new Gson();
			String json;
			json=gson.toJson(lista);
			response.setContentType("application/json;charset=UTF-8");
			PrintWriter salida=response.getWriter();
			salida.println(json);
		
	}


	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws IOException {
         //obtener el codigo enviado desde el boton "eliminar"
		String cod=request.getParameter("codigo");
		//obtener el valor del atributo "detalle"
		HttpSession session=request.getSession();
		  List<Detalle> data=(List<Detalle>) session.getAttribute("detalle");
		  for(Detalle d:data) {
			  if(d.getCod_resolu()==Integer.parseInt(cod)) {
				  data.remove(d);
				  break;
			  }
		  }
		  Gson gson=new Gson();
			String json;
			json=gson.toJson(data);
			response.setContentType("application/json;charset=UTF-8");
			PrintWriter salida=response.getWriter();
			salida.println(json);
	}


	private void generar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//session
		HttpSession session=request.getSession();
		
		//leer cajas
		String cod,des,mon,car,doc;
		cod=request.getParameter("CodigoPro");
		des=request.getParameter("descripcion");
		mon=request.getParameter("moneda");
		car=request.getParameter("cargo");
		doc=request.getParameter("documentos");
		
		//crear objeto de la clase GenerarProyecto
		GenerarProyecto bean=new GenerarProyecto();
		//setear
		bean.setCod_resolu(Integer.parseInt(cod));
		bean.setDes_resolu(des);
		bean.setFech_resolu(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		bean.setDocumentos_Resolu(doc);
		bean.setCargo_resolu(car);
		
		//obtener el atributo detalle
		List<Detalle> lista=(List<Detalle>) session.getAttribute("detalle");
		
		//invocar al metodo
		int salida;
		salida=new GenerarProyectoService().saveProyecto(bean, lista);
		if(salida>0) {// SE ELIMINO CORRECTAMENTE
			//crear un atributo MENSAJE
			request.setAttribute("MENSAJE","Proyecto generado");
		}
		else {// ERROR AL ELIMINAR
			request.setAttribute("MENSAJE","Error al generar proyecto");
		}
		request.getRequestDispatcher("/FacturaProyecto.jps").forward(request, response);
		
	}

}
