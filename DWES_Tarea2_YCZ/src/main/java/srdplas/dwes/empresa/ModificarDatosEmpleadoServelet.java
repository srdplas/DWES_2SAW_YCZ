package srdplas.dwes.empresa;

import java.io.IOException;
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
 * Servlet implementation class ModificarDatosEmpleadoServelet
 */
@WebServlet("/modificarDatosEmpleado")
public class ModificarDatosEmpleadoServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificarDatosEmpleadoServelet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			
			Connection conn =  ConexionBBDD.getConnection();
			Statement st = conn.createStatement();
			String dni = request.getParameter("dni");
			ResultSet rs = st.executeQuery("Select * from empleados where dni=" + "'" + dni + "';");
			
			String nombre="";
			String sexo ="";
			int categoria=0;
			int anyosTrabajados=0;
			
			while (rs.next()) {

				nombre = rs.getString("nombre");
				sexo= rs.getString("sexo");
				categoria= rs.getInt("categoria");
				anyosTrabajados= rs.getInt("anyosTrabajados");	
			}
			conn.close();
			st.close();
			
			System.out.println(nombre);
			System.out.println(dni);
			System.out.println(sexo);
			System.out.println(categoria);
			System.out.println(anyosTrabajados);
			
			
			request.setAttribute("nombre", nombre);
			request.setAttribute("dni", dni);
			request.setAttribute("sexo", sexo);
			request.setAttribute("categoria", categoria+"");
			request.setAttribute("anyosTrabajados", anyosTrabajados+"");
			
			
			RequestDispatcher rd = request.getRequestDispatcher("/modificarEmpleado.jsp");
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
