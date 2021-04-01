package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanUsuario;
import mantenimientos.GestionUsuario;

/**
 * Servlet implementation class ServletLogin
 */
@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLogin() {
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
		String email = request.getParameter("email");
		String psw = request.getParameter("psw");
		
		GestionUsuario gu = new GestionUsuario();
		try {
			BeanUsuario usuario = gu.validarLogin(email, psw);
			String redirectTo = "login.jsp";
			
			if(usuario != null) {
				request.setAttribute("msg", "Bienvenido " + usuario.getName() + " " + usuario.getLastName());
			} else {
				request.setAttribute("msg", "login fallido");
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher(redirectTo);
			dispatcher.forward(request, response);
		} catch(Exception e) {
			System.out.println("Error en ServletLogin: " + e.getMessage());
		}
	}

}
