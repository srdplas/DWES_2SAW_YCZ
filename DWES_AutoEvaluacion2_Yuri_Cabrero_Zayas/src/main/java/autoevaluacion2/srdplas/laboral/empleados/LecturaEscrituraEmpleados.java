package autoevaluacion2.srdplas.laboral.empleados;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import autoevaluacion2.srdplas.laboral.CalculaNominas;
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

	/**
	 * 
	 * @throws IOException
	 * @throws NumberFormatException
	 * @throws DatosNoCorrectosException
	 * Lee datos de un fichero y escribe en otro
	 * @throws SQLException 
	 */
	public void lecturaEmpleadosEscrituraNomina(String empleado, String nominas) throws IOException, NumberFormatException, DatosNoCorrectosException, SQLException {
		String nombreFichero = "../DWES_AutoEvaluacion2_Yuri_Cabrero_Zayas/src/main/java/autoevaluacion2/srdplas/laboral/empleados/"+empleado;
		try {
			Nomina nom = new Nomina();
			
			archivo = new File(nombreFichero);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			String empleados[];
			Empleado em = null;
			
			archivo2 = new File("nominas");
			fw = new FileWriter(archivo2, true);
			bw = new BufferedWriter(fw);
			
			
			//Leemos el Fichero
			String linea;
			String linea2;

			while((linea=br.readLine())!= null) {
			
				
				empleados = linea.split(", ");
				
				if (empleados.length == 3) { 
	        		em = new Empleado(empleados[0], empleados[1], empleados[2].toCharArray()[0]);	
	        		bw.write(empleados[1]+", "+nom.sueldo(em)+"\n");
	        		altaEmpleado(em);
	        		
	        		
	        		
	        		
				}else if (empleados.length ==5) { 
	        		em = new Empleado(empleados[0], empleados[1], empleados[2].toCharArray()[0], Integer.parseInt(empleados[3]), Integer.parseInt(empleados[4]));
	        		bw.write(empleados[1]+", "+nom.sueldo(em)+"\n");
	        		altaEmpleado(em);
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
	 
	
	
	/**
	 * 
	 * @param nominas
	 * @throws IOException
	 * @throws NumberFormatException
	 * @throws DatosNoCorrectosException
	 * Lee todas las nominas del fichero indicado
	 */
	public Map<String, String> leerNominas(String nominas) throws IOException, NumberFormatException, DatosNoCorrectosException {
		String nombreFichero = "../DWES_AutoEvaluacion2_Yuri_Cabrero_Zayas/src/main/java/autoevaluacion2/srdplas/laboral/empleados/"+nominas;
		Map<String, String> map = new HashMap<String, String>();
		try {
			Nomina nom = new Nomina();
			
			archivo = new File(nombreFichero);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			String empleados[];
			
			Empleado em = null;
	
			//Leemos el Fichero
			String linea;

			while((linea=br.readLine())!= null) {
				try {
					empleados = linea.split(", ");
					map.put(empleados[0], empleados[1]);
					empleados[0] = null;
					empleados[1] = null;
				}catch(Exception e){
					continue;
				}
			}
			System.out.println(map);
		
		}catch (FileNotFoundException e) {
			
		}finally {
			if(fr!=null) {
				fr.close();
			}
			if(bw!=null) {
				bw.close();
			}
		}
		return map;
	}
	
	public static void copiaSeguridad(Empleado emp) {
		String rutaUrl = "../DWES_AutoEvaluacion2_Yuri_Cabrero_Zayas/src/main/java/autoevaluacion2/srdplas/laboral/backup/";
    	File copiaSeg = new File("empleadosCopiaSeg.txt");
    	try {
	        FileWriter fw = new FileWriter(copiaSeg.getAbsoluteFile(),true);
	        BufferedWriter bw = new BufferedWriter(fw);
	        
	       	bw.write(emp.getNombre()+";"+emp.getDni()+";"+emp.getSexo()+";"+emp.getCategoria()+";"+emp.getAnyosTrabajados()+";"+Nomina.sueldo(emp)+'\n');
	        bw.close();
	        fw.close();
	        
    	}catch (FileNotFoundException ex) {
    		System.out.println(ex);
    		
    	}catch (IOException ex) {
    		System.out.println(ex);
    	}
	}
	
	public static void altaEmpleado(Empleado emp) throws SQLException {
        Connection con = ConexionBBDD.getConnection();
	        
        try {    
	        Statement st=con.createStatement();
	        st.execute("insert into Empleados values ('"+emp.getNombre()+"','"+emp.getDni()+"','"+emp.getSexo()+"','"+emp.getCategoria()+"','"+emp.getAnyosTrabajados()+"')");
			st.execute("insert into nominas values ('"+emp.getDni()+"','"+Nomina.sueldo(emp)+"')");
			copiaSeguridad(emp);
			
        }catch (SQLException ex) {
	    	  ex.printStackTrace();
	    	  
	    }
	}
}
