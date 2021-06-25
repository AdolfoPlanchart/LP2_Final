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
import net.proyecto.entidad.Trabajador;
import net.proyecto.service.SolicitudService;
import net.proyecto.service.TrabajadorService;

/**
 * Servlet implementation class ServletSolicitudJson
 */
@WebServlet("/ServletSolicitudJson")
public class ServletSolicitudJson extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletSolicitudJson() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Solicitud> lista=new SolicitudService().listarTodos();//
		Gson gson=new Gson();
		String json=gson.toJson(lista);
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter salida=response.getWriter();
		salida.println(json);
	}

}
