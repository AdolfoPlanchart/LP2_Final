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

import net.proyecto.entidad.Expediente;
import net.proyecto.entidad.ExpedienteGastos;
import net.proyecto.service.ExpedienteService;


@WebServlet("/ServletExpedienteJSON")
public class ServletExpedienteJSON extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private ExpedienteService sExpediente;

    public ServletExpedienteJSON() {
        super();
        sExpediente=new ExpedienteService();
       
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<ExpedienteGastos> lista=sExpediente.listarTodos();
		Gson gson=new Gson();
		String json;
		json=gson.toJson(lista);
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter salida=response.getWriter();
		salida.println(json);
	}

}
