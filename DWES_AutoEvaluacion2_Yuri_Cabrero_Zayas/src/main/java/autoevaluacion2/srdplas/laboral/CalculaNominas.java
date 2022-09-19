package autoevaluacion2.srdplas.laboral;

import java.io.IOException;

import autoevaluacion2.srdplas.laboral.empleados.LecturaEmpleados;
import autoevaluacion2.srdplas.laboral.excepciones.DatosNoCorrectosException;

public class CalculaNominas {

	public static void main(String[] args) throws DatosNoCorrectosException, IOException {
		
		
		
		System.out.println("Leemos desde fichero");
		LecturaEmpleados leer = new LecturaEmpleados();
		leer.LeerFichero();

	}

	/**
	 * 
	 * @param emp1
	 * @param emp2
	 * Imprime datos y salario de los parametros
	 */
	private static void escribe(Empleado emp1, Empleado emp2) {
		Nomina nomina = new Nomina();
		emp1.imprime();
		System.out.println(nomina.sueldo(emp1)+" €");
		
		emp2.imprime();
		System.out.println(nomina.sueldo(emp2)+" €");
	}
}
