package autoevaluacion2.srdplas.laboral.empleados;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

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
	 * @throws SQLException 
	 */
	public void lecturaEmpleadosEscrituraNomina(String empleado, String nominas) throws IOException, NumberFormatException, DatosNoCorrectosException, SQLException {
		String nombreFichero = "../DWES_AutoEvaluacion2_Yuri_Cabrero_Zayas/src/main/java/autoevaluacion2/srdplas/laboral/empleados/"+empleado;
		String nombreNominas =  "../DWES_AutoEvaluacion2_Yuri_Cabrero_Zayas/src/main/java/autoevaluacion2/srdplas/laboral/empleados/"+nominas;
		try {
			Nomina nom = new Nomina();
			
			archivo = new File(nombreFichero);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			String empleados[];
			
			
			archivo2 = new File(nombreNominas);
			fw = new FileWriter(archivo2, true);
			bw = new BufferedWriter(fw);
			
			
			//Leemos el Fichero
			String linea;


			while((linea=br.readLine())!= null) {
			
				
				empleados = linea.split(", ");
				
				if (empleados.length == 3) { 
	        		Empleado em = new Empleado(empleados[0], empleados[1], empleados[2].toCharArray()[0]);	
	        		bw.write(empleados[1]+", "+nom.sueldo(em)+"\n");
	        		altaEmpleado(em);
	        		
	        		
	        		
	        		
				}else if (empleados.length ==3) { 
	        		Empleado em = new Empleado(empleados[0], empleados[1], empleados[2].toCharArray()[0], Integer.parseInt(empleados[3]), Integer.parseInt(empleados[4]));
	        		bw.write(empleados[1]+", "+nom.sueldo(em)+"\n");
	        		altaEmpleado(em);
				}
				
			}
		}catch (FileNotFoundException e) {
			
		}finally {
			if(fr!=null) {
				fr.close();
				br.close();
			}
			if(bw!=null) {
				bw.close();
			}
		}
	}
	 
	public  void copiaSeguridad(Empleado emp) throws NumberFormatException, IOException, DatosNoCorrectosException, SQLException {
		String ruta = "../DWES_AutoEvaluacion2_Yuri_Cabrero_Zayas/src/main/java/autoevaluacion2/srdplas/laboral/empleados/";
		String rutaNom = ruta+"nominas.dat";
		String rutaEmp = ruta+"empleados.backup";
		String rutaEmpleado = ruta+"empleados.txt";
		
		archivo = null;
		fr = null;
		br = null;
		
		
	
		
  
    	try {
        		File escribe = new File(rutaEmp);
        		
    	        FileWriter fw = new FileWriter(escribe.getAbsoluteFile(),true);
    	        BufferedWriter bw = new BufferedWriter(fw);
    	        // CREAMOS EL FICHERO DE BACKUP CON LOS EMPLEADOS
    	       	bw.write(emp.getNombre()+","+emp.getDni()+","+emp.getSexo()+","+emp.getCategoria()+","+emp.getAnyosTrabajados()+'\n');
    	       	bw.close();
    	       	
    	       	File nominas = new File(rutaNom);
    	       	FileWriter ffw = new FileWriter(nominas);
    	       	BufferedWriter bff = new BufferedWriter(ffw);
    	       	escribirFicheroNominas(emp);
    	       	bff.close();
	       
    	 
    	}catch(Exception e) {
    		System.out.println(e);
    	}
		
	}

	/**
	 * 
	 * @param emp
	 * @throws IOException
	 * Escribe los empleados de la BBDD en el fichero empleados.txt
	 */
	public  void escribirFichero(Empleado emp) throws IOException {
		
		String ruta = "../DWES_AutoEvaluacion2_Yuri_Cabrero_Zayas/src/main/java/autoevaluacion2/srdplas/laboral/empleados/";
		String rutaNom = ruta+"nominas.dat";;
		String rutaEmpleado = ruta+"empleados.txt";
		
		archivo = null;
		fr = null;
		br = null;
		
		
	
		
  
    	try {
        		File escribe = new File(rutaEmpleado);
        		
    	        FileWriter fw = new FileWriter(escribe.getAbsoluteFile(),true);
    	        BufferedWriter bw = new BufferedWriter(fw);
    	        // CREAMOS EL FICHERO DE BACKUP CON LOS EMPLEADOS
    	       	bw.write(emp.getNombre()+","+emp.getDni()+","+emp.getSexo()+","+emp.getCategoria()+","+emp.getAnyosTrabajados()+'\n');
    	       	bw.close();
    	       	
    	       	File nominas = new File(rutaNom);
    	       	FileWriter ffw = new FileWriter(nominas);
    	       	BufferedWriter bff = new BufferedWriter(ffw);
    	       	bff.write(emp.getDni()+" - "+escribe(emp)+" €");
    	       	bff.close();
	       
    	 
    	}catch(Exception e) {
    		System.out.println(e);
    	}
	}

	/**
	 * 
	 * @param con
	 */
	public void altaEmpleado(Connection con) { 
		Scanner sct = new Scanner(System.in);
		Scanner sci = new Scanner(System.in);
		ResultSet rs = null;
		con = null;
		Statement st = null;
		
		try {
			System.out.println("A continuacion indica los datos del Empleado\n");
			System.out.println("Nombre del empleado:");
			String nombreEmpleado=sct.nextLine();
			System.out.println("DNI del empleado:");
			String dniEmpleado=sct.nextLine();
			System.out.println("Sexo del empleado:");
			String sexoEmpleado=sct.nextLine();
			System.out.println("Categoria del empleado:");
			int categoriaEmpleado =sci.nextInt();
			System.out.println("Años trabajados del empleado:");
			int  anyosTrabajadosEmpleado=sci.nextInt();
			
			
			//HACEMOS UNA CONSULTA POR SI EL DNI YA ESTÁ EN LA BBDD
			con = ConexionBBDD.getConnection();
			st = con.createStatement();
			rs = st.executeQuery("Select dni from empleados where dni=" + "'" + dniEmpleado + "';");
			boolean vacio = false;
			while(rs.next()) {
				String resultado = rs.getString("dni");
				if(resultado==null||resultado=="") {
					vacio = true;
				}
			}
			if (vacio !=false) {
				System.out.println("El dni ya estaba en la BBDD");
				
			}else {
				//INSERTAMOS EL EMPLEADO EN LAS 2 TABLAS
				
				if(anyosTrabajadosEmpleado==0||anyosTrabajadosEmpleado<0) {
					anyosTrabajadosEmpleado=0;
				}
				if(categoriaEmpleado<1) {
					categoriaEmpleado=1;
				}
				
				st.execute("insert into empleados values('"+nombreEmpleado+"', '"+dniEmpleado+"', '"+sexoEmpleado+"', "+categoriaEmpleado+", "+anyosTrabajadosEmpleado+");");
				
				
				//ACTUALIZAMOS EL SALARIO AUTOMATICAMENTE
				char stringToCharSexo = sexoEmpleado.charAt(0);
				Empleado trabajador = new Empleado(nombreEmpleado, dniEmpleado, stringToCharSexo,  categoriaEmpleado, anyosTrabajadosEmpleado);
				escribirFichero(trabajador);
				double nuevoSalarioActualizado = 0;
				try {
					nuevoSalarioActualizado = this.escribe(trabajador);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				int numFilasAfectadas = st.executeUpdate("update nominas set sueldo="+"'"+nuevoSalarioActualizado+"'"+ "where dni="+"'"+dniEmpleado+"';");
				System.out.println("Filas actualizadas: "+numFilasAfectadas);
				
				nuevoSalarioActualizado = escribe(trabajador);
				st.execute("insert into nominas values('"+dniEmpleado+"' , "+nuevoSalarioActualizado+");");
			}
	
		}catch (Exception e) {
			
		}
		
	}
	
public  void escribirFicheroNominas(Empleado emp) throws IOException {
		
		String ruta = "../DWES_AutoEvaluacion2_Yuri_Cabrero_Zayas/src/main/java/autoevaluacion2/srdplas/laboral/empleados/";
		String rutaNom = ruta+"nominas.dat";;
		
		archivo = null;
		fr = null;
		br = null;
		
		
	
		
  
    	try {
    		Connection conn = ConexionBBDD.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("Select * from nominas;");
			
			File escribe = new File(rutaNom);
    		
	        FileWriter fw = new FileWriter(escribe.getAbsoluteFile(),false);
	        BufferedWriter bw = new BufferedWriter(fw);
	        
			while (rs.next()) {
				// CREAMOS EL FICHERO DE BACKUP CON LOS EMPLEADOS
				
		       	bw.write(rs.getString("dni")+" - "+rs.getDouble("sueldo")+" €\n");
			}
			

			
	       
			bw.close();
    	}catch(Exception e) {
    		System.out.println(e);
    	
		}
			

	}
	
	
	
	public  void altaEmpleado(Empleado emp) throws SQLException, IOException {  //ALTA EMPLEADO INTRODUCIENDOLE UN EMPLEADO
        Connection con = ConexionBBDD.getConnection();
        Statement st=con.createStatement();
        Nomina nomina = new Nomina();
        try {    
	        
	        st.execute("insert into Empleados values ('"+emp.getNombre()+"','"+emp.getDni()+"','"+emp.getSexo()+"','"+emp.getCategoria()+"','"+emp.getAnyosTrabajados()+"')");
			st.execute("insert into nominas values ('"+emp.getDni()+"'-'"+nomina.sueldo(emp)+"')");
			escribirFichero(emp);
			
        }catch (SQLIntegrityConstraintViolationException ex) {
	    	  ex.printStackTrace();
	    	  
	    }finally {
	    	con.close();
	    	st.close();
	    }
	}
	
	public  void altaEmpleado(List<Empleado> empLista) throws SQLException, IOException {  //ALTA EMPLEADO INTRODUCIENDOLE UN EMPLEADO
        Connection con = ConexionBBDD.getConnection();
        Statement st=con.createStatement();
        Nomina nomina = new Nomina();
        Iterator<Empleado>iterador =empLista.iterator();
        while(iterador.hasNext()) {
        	Empleado obtenido = (Empleado)(iterador.next());
        	altaEmpleado(obtenido);
        }
	}
	
	/**
	 * 
	 * @param emp1
	 * @param emp2 Imprime datos y salario de los parametros
	 */
	public double escribe(Empleado emp1) {
		Nomina nomina = new Nomina();
		return nomina.sueldo(emp1);

	}
	

	
	
}
