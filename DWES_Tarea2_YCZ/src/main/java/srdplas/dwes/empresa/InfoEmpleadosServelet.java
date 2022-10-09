package srdplas.dwes.empresa;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import srdplas.dwes.empresa.conexionbbdd.ConexionBBDD;

/**
 * Servlet implementation class InfoEmpleados
 */
@WebServlet("/infoEmpleados")
public class InfoEmpleadosServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InfoEmpleadosServelet() {
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
			ResultSet rs = st.executeQuery("Select * from empleados;");
			
			List<String> nombre= new ArrayList<String>();
			List <String>dni= new ArrayList<String>();
			List <String>sexo= new ArrayList<String>();
			List <String>categoria= new ArrayList<String>();
			List <String>anyos= new ArrayList<String>();
			
			
			
			
			
			
			while (rs.next()) {

				nombre.add(rs.getString("nombre"));
				dni.add(rs.getString("dni"));
				sexo.add( rs.getString("sexo"));
				categoria.add(rs.getInt("categoria")+"");
				anyos.add(rs.getInt("anyosTrabajados")+"");	
			}
			conn.close();
			st.close();
			request.setAttribute("nombres", nombre);
			request.setAttribute("dnis", dni);
			request.setAttribute("sexos", sexo);
			request.setAttribute("categorias", categoria);
			request.setAttribute("anyosTrabajados", anyos);
			
			RequestDispatcher rd = request.getRequestDispatcher("/infoEmpleado.jsp");
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
