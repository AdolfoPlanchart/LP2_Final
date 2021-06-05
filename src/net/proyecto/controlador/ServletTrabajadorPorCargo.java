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

import net.proyecto.entidad.Trabajador;
import net.proyecto.service.TrabajadorService;

/**
 * Servlet implementation class ServletTrabajadorPorCargo
 */
@WebServlet("/ServletTrabajadorPorCargo")
public class ServletTrabajadorPorCargo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ServletTrabajadorPorCargo() {
        super();
    }


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cargos;
		cargos=request.getParameter("cargoTrabajador");		
		List<Trabajador> lista=new TrabajadorService().listarTrabajadores(Integer.parseInt(cargos));
		Gson gson=new Gson();
		String json=gson.toJson(lista);
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter salida=response.getWriter();
		salida.println(json);
	}

}
