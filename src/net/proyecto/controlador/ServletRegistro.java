package net.proyecto.controlador;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.proyecto.dao.MySqlUsuarioDAO;
import net.proyecto.entidad.Usuario;

/**
 * Servlet implementation class ServletRegistro
 */
@WebServlet("/ServletRegistro")
public class ServletRegistro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRegistro() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean seRegistro = false;
		
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String email = request.getParameter("email");
		String psw = request.getParameter("psw");
		String celular = request.getParameter("codigopais") + request.getParameter("celular");
		
		Usuario usuario = new Usuario();
		usuario.setName(nombre);
		usuario.setLastName(apellido);
		usuario.setEmail(email);
		usuario.setPsw(psw);
		usuario.setPhoneNumber(celular);
		
		MySqlUsuarioDAO gu = new MySqlUsuarioDAO();
		try {
			seRegistro = gu.registrarUsuario(usuario);
			String redirectTo = "login.jsp";
			System.out.println(seRegistro);
			
			if(seRegistro) {
				request.setAttribute("msg", "Registrado");
			} else {
				request.setAttribute("msg", "Error en registro");
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher(redirectTo);
			dispatcher.forward(request, response);
		} catch(Exception e) {
			System.out.println("Error en ServletRegistro.doPost: " + e.getMessage());
		}
	}

}
