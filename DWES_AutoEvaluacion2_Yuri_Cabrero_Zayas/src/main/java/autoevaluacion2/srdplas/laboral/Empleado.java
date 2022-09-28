package autoevaluacion2.srdplas.laboral;

import autoevaluacion2.srdplas.laboral.excepciones.DatosNoCorrectosException;

public class Empleado extends Persona {

	private int categoria;
	private int anyosTrabajados;

	/**
	 * 
	 * @param nombre
	 * @param dni
	 * @param sexo
	 */
	public Empleado(String nombre, String dni, char sexo) {
		super(nombre, dni, sexo);
		categoria = 1;
		anyosTrabajados = 0;

	}

	/**
	 * 
	 * @param nombre
	 * @param dni
	 * @param sexo
	 * @param categoria
	 * @param anyosTrabajados
	 * @throws DatosNoCorrectosException
	 */
	public Empleado(String nombre, String dni, char sexo, int categoria, int anyosTrabajados) throws DatosNoCorrectosException {
		super(nombre, dni, sexo);
		
		if (categoria < 0 || categoria > 10) {
			throw new DatosNoCorrectosException("Datos no correctos");
		} else {
			this.categoria = categoria;
		}

		if (anyosTrabajados < 0) {
			throw new DatosNoCorrectosException("Datos no correctos");
		} else {
			this.anyosTrabajados = anyosTrabajados;
		}

	}
	/**
	 * 
	 * @return categoria
	 */

	public int getCategoria() {
		return categoria;
	}

	
	public int getAnyosTrabajados() {
		return anyosTrabajados;
	}

	public void setAnyosTrabajados(int anyosTrabajados) {
		this.anyosTrabajados = anyosTrabajados;
	}

	/**
	 * 
	 * @param categoria
	 * @throws DatosNoCorrectosException
	 */
	public void setCategoria(int categoria) throws DatosNoCorrectosException {
		if (categoria < 0 || categoria > 10) {
			throw new DatosNoCorrectosException("Datos no correctos");
		} else {
			this.categoria = categoria;
		}
	}

	/**
	 * Incrementa +1 anyos trabajados
	 */
	public void incrAnyo() {
		if(anyosTrabajados<0) {
			anyosTrabajados=0;
			this.anyosTrabajados++;
		}else {
			this.anyosTrabajados++;;
		}
		
	}
	/**
	 * Imprime todos los datos del Empleado
	 */
	public void imprime() {
		System.out.println("Nombre: "+this.getNombre()+", DNI: "+this.getDni()+", Sexo: "+this.getSexo()+", Categoria: "+this.getCategoria()+", Años Trabajados: "+getAnyosTrabajados());
	}

}
