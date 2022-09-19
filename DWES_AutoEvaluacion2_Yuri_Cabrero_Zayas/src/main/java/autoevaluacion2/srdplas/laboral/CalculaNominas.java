package autoevaluacion2.srdplas.laboral;

import autoevaluacion2.srdplas.laboral.excepciones.DatosNoCorrectosException;

public class CalculaNominas {

	public static void main(String[] args) throws DatosNoCorrectosException {
		
		Empleado empleado1 = new Empleado("James", "32000032G", 'H', 4, 7);
		Empleado empleado2 = new Empleado("Ada Lovelace", "32000031R", 'F');
		
		//Usamos el metodo para que escriba los datos de los empleados
		escribe(empleado1, empleado2);
		
		System.out.println("\nIncrementamos y cambiamos categoria\n");
		
		empleado2.incrAnyo();
		empleado1.setCategoria(9);
		escribe(empleado1, empleado2);

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
