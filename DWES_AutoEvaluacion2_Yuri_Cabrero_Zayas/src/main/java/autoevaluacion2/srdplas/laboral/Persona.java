package autoevaluacion2.srdplas.laboral;

public class Persona {

	String nombre, dni;
	char sexo;

	/**
	 * 
	 * @param nombre
	 * @param dni
	 * @param sexo
	 */
	public Persona(String nombre, String dni, char sexo) {
		super();
		this.nombre = nombre;
		this.dni = dni;
		this.sexo = sexo;
	}

	/**
	 * 
	 * @param nombre
	 * @param sexo
	 */
	public Persona(String nombre, char sexo) {
		super();
		this.nombre = nombre;
		this.sexo = sexo;
	}
	/**
	 * 
	 * @param dni
	 */

	public void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * Imprime los Datos de la Persona
	 */
	public void Imprime() {
		System.out.println("Nombre: "+nombre+", DNI: "+dni);
	}
	
	
	
}



