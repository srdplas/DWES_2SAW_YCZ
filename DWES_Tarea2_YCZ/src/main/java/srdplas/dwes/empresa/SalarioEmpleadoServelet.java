package srdplas.dwes.empresa;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import srdplas.dwes.empresa.conexionbbdd.ConexionBBDD;

/**
 * Servlet implementation class SalarioEmpleado
 */
@WebServlet("/salarioEmpleado")
public class SalarioEmpleadoServelet extends HttpServlet {
	
	
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SalarioEmpleadoServelet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    /**
     * Se ejecuta al iniciar el servelet
     */
    public void init() throws ServletException{
    	
    }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * Obtenemos el dni del formulario del jsp salarioEmpleado.jsp y realizamos consulta en tabla nominas para devolver la cantidad
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String dni = request.getParameter("dni");
		System.out.println(dni);
//		response.sendRedirect("salarioEmpleado.jsp");

	
		
		//CREAR EL METODO QUE BUSQUE Y QUE CREE UN ELEMENTO EN EL JSP CON EL SALARIO
	
		try {
	
			Connection conn =  ConexionBBDD.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("Select sueldo from nominas where dni=" + "'" + dni + "';");
			double sueldo = 0;
			while (rs.next()) {
				sueldo= rs.getDouble("sueldo");
			}
			conn.close();
			st.close();
			
			String newSueldo = sueldo+"";
			request.setAttribute("sueldo", newSueldo);
			request.setAttribute("dni", dni);
			System.out.println(newSueldo);
			RequestDispatcher rd = request.getRequestDispatcher("/salarioEmpleado.jsp");
			rd.forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
