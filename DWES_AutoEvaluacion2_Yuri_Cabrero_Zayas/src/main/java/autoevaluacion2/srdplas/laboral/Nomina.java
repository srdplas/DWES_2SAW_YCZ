package autoevaluacion2.srdplas.laboral;

public class Nomina {
	/***
	 * Tablas con Salarios Base
	 */
	private static final int SUELDO_BASE[] =
		{50000, 70000, 90000, 110000, 130000,
		150000, 170000, 190000, 210000, 230000};

	/**
	 * 
	 * @param empleado
	 * @return sueldo del empleado por categoria y anyos trabajados
	 */
	public double sueldo(Empleado empleado) {
		
		double salario = (SUELDO_BASE[(empleado.getCategoria()-1)]+(5000*empleado.anyosTrabajados));
		return salario;
		
	}
}
