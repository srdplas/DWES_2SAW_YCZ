package autoevaluacion2.srdplas.laboral;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import autoevaluacion2.srdplas.laboral.empleados.ConexionBBDD;
import autoevaluacion2.srdplas.laboral.empleados.LecturaEscrituraEmpleados;
import autoevaluacion2.srdplas.laboral.excepciones.DatosNoCorrectosException;

public class CalculaNominas {

	public static void main(String[] args) throws DatosNoCorrectosException, IOException, SQLException {
		
		Empleado empleado1 = new Empleado("James", "32000032G", 'H', 4, 7);
		Empleado empleado2 = new Empleado("Ada Lovelace", "32000031R", 'F');
		empleado2.incrAnyo();
		empleado1.setCategoria(9);
		
		System.out.println("Leemos desde fichero");
		LecturaEscrituraEmpleados leer = new LecturaEscrituraEmpleados();
		leer.lecturaEmpleadosEscrituraNomina("empleados.txt", "sueldos.dat");
		
		//Actualizamos datos en la Base De Datos sobre los Empleados
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		conn = ConexionBBDD.getConnection();
		st = conn.createStatement();
		int numFilasAfectadas = st.executeUpdate("update empleados set categoria=9 where dni='"+empleado1.getDni()+"';");
		System.out.println("Datos actualizados, filas afectadas: "+numFilasAfectadas);
		
		double nominaUpdate = escribe(empleado1);

		numFilasAfectadas = st.executeUpdate("update nominas set sueldo="+nominaUpdate+"where dni='"+empleado1.getDni()+"';");
		System.out.println("Datos actualizados, filas afectadas: "+numFilasAfectadas);
		nominaUpdate = escribe(empleado2);

		numFilasAfectadas = st.executeUpdate("update nominas set sueldo="+nominaUpdate+"where dni='"+empleado2.getDni()+"';");
		System.out.println("Datos actualizados, filas afectadas: "+numFilasAfectadas);
		
//		Map<String, String> nominas = leer.leerNominas("sueldos.dat");
	
		
boolean salir = false;		
		
		Scanner numeros = new Scanner(System.in);
		Scanner letras = new Scanner(System.in);
		String dni;
				
		while (!salir) {
		
			System.out.println("1. Ver empleados");
			System.out.println("2. Ver salario de un empleado especifico");
			System.out.println("4. Recalcular y actualizar sueldo de un empleado");
			System.out.println("5. Recalcular y actualizar sueldos de todos los empleado");
			System.out.println("6. Realizar copia de seguridad de la BBDD en fichero");
			System.out.println("7. Alta de nuevos empleados");
			System.out.println("0. Salir");
			String menuprincipal = letras.nextLine();
		
			switch (menuprincipal) {
			case "1": 
			}
			
		letras.close();
		numeros.close();
	}

	}

	/**
	 * 
	 * @param emp1
	 * @param emp2 Imprime datos y salario de los parametros
	 */
	private static double escribe(Empleado emp1) {
		Nomina nomina = new Nomina();
		return nomina.sueldo(emp1);

	}
}
