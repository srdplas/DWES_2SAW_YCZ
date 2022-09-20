package autoevaluacion2.srdplas.laboral;

import java.io.IOException;

import autoevaluacion2.srdplas.laboral.empleados.LecturaEscrituraEmpleados;
import autoevaluacion2.srdplas.laboral.excepciones.DatosNoCorrectosException;

public class CalculaNominas {

	public static void main(String[] args) throws DatosNoCorrectosException, IOException {
		
		Empleado em = new Empleado("James","32000032G",'M',9,7);
		
		System.out.println("Leemos desde fichero");
		LecturaEscrituraEmpleados leer = new LecturaEscrituraEmpleados();
		leer.leerFichero();
		
	}

	/**
	 * 
	 * @param emp1
	 * @param emp2
	 * Imprime datos y salario de los parametros
	 */
	private static double escribe(Empleado emp1) {
		Nomina nomina = new Nomina();
		emp1.imprime();
		return nomina.sueldo(emp1);
		
	}
}
