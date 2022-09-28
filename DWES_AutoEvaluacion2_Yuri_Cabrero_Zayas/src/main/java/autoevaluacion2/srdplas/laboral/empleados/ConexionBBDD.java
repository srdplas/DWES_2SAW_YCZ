package autoevaluacion2.srdplas.laboral.empleados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class ConexionBBDD {
	private static String baseDatos = "autoevaluacion";
	private static String baseDatosUrl = "jdbc:mysql://localhost:3306/"+baseDatos;
	private static String usuario = "yuri";
	private static String password = "yuri";
	
	public static Connection getConnection() throws SQLException{
		
		Connection conexion = null;
		conexion = DriverManager.getConnection(baseDatosUrl, usuario, password);
		return conexion;
		
	}

	
	

	

}
