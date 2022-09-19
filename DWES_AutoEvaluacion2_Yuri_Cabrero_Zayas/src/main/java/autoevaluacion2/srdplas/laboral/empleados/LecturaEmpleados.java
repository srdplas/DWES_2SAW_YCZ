package autoevaluacion2.srdplas.laboral.empleados;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LecturaEmpleados {
	File archivo = null;
	FileReader fr = null;
	BufferedReader br = null;
	
	
	public void LeerFichero() throws IOException {
		try {
			archivo = new File("../DWES_AutoEvaluacion2_Yuri_Cabrero_Zayas/src/main/java/autoevaluacion2/srdplas/laboral/empleados/empleados.txt");
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			
			//Leemos el Fichero
			String linea;
			String nombre;
			String dni;
			char sexo;
			int categoria;
			int anyosTrabajados;
			
			
			while((linea=br.readLine())!= null) {
				System.out.println(linea);
				
			}
			
			
		}catch (Exception e) {
			
		}finally {
			if(fr!=null) {
				fr.close();
			}
		}
	}

}
