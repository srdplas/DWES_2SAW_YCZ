package autoevaluacion2.srdplas.laboral.empleados;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import autoevaluacion2.srdplas.laboral.Empleado;
import autoevaluacion2.srdplas.laboral.Nomina;
import autoevaluacion2.srdplas.laboral.excepciones.DatosNoCorrectosException;

public class LecturaEscrituraEmpleados {
	File archivo = null;
	FileReader fr = null;
	BufferedReader br = null;
	
	File archivo2 = null;
	FileWriter fw = null;
	BufferedWriter bw = null;

	
	public void leerFichero() throws IOException, NumberFormatException, DatosNoCorrectosException {
		try {
			Nomina nom = new Nomina();
			
			archivo = new File("../DWES_AutoEvaluacion2_Yuri_Cabrero_Zayas/src/main/java/autoevaluacion2/srdplas/laboral/empleados/empleados.txt");
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			String empleados[];
			Empleado em = null;
			
			archivo2 = new File("../DWES_AutoEvaluacion2_Yuri_Cabrero_Zayas/src/main/java/autoevaluacion2/srdplas/laboral/empleados/sueldos.dat");
			fw = new FileWriter(archivo2, true);
			bw = new BufferedWriter(fw);
			
			
			//Leemos el Fichero
			String linea;

			while((linea=br.readLine())!= null) {
			
				
				empleados = linea.split(", ");
				
				if (empleados.length == 3) { 
	        		em = new Empleado(empleados[0], empleados[1], empleados[2].toCharArray()[0]);
	        		bw.write(empleados[1]+", "+nom.sueldo(em)+"€\n");
	        		
	        		
				}else if (empleados.length ==5) { 
	        		em = new Empleado(empleados[0], empleados[1], empleados[2].toCharArray()[0], Integer.parseInt(empleados[3]), Integer.parseInt(empleados[4]));
	        		bw.write(empleados[1]+", "+nom.sueldo(em)+"€\n");
	        		
				}
				em.imprime();
			
					
					
			
			}
			
			
		}catch (FileNotFoundException e) {
			
		}finally {
			if(fr!=null) {
				fr.close();
			}
			if(bw!=null) {
				bw.close();
			}
		}
	}
	

}
