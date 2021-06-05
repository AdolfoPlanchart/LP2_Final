package net.proyecto.controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.proyecto.service.TrabajadorService;

@WebServlet("/ServletTrabajador")
public class ServletTrabajador extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
	

}
