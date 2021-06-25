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

import net.proyecto.entidad.ExpedienteGastos;
import net.proyecto.entidad.Solicitud;
import net.proyecto.service.ExpedienteService;
import net.proyecto.service.SolicitudService;

/**
 * Servlet implementation class ServletExpedienteJSON
 */
@WebServlet("/ServletExpedienteJSON")
public class ServletExpedienteJSON extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletExpedienteJSON() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<ExpedienteGastos> lista=new ExpedienteService().listarTodos();//
		Gson gson=new Gson();
		String json=gson.toJson(lista);
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter salida=response.getWriter();
		salida.println(json);
	}

}
