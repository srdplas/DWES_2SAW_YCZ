package autoevaluacion2.srdplas.laboral;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import autoevaluacion2.srdplas.laboral.empleados.ConexionBBDD;
import autoevaluacion2.srdplas.laboral.empleados.LecturaEscrituraEmpleados;
import autoevaluacion2.srdplas.laboral.excepciones.DatosNoCorrectosException;

public class CalculaNominas {

	public static void main(String[] args) throws DatosNoCorrectosException, IOException, SQLException {

		Empleado empleado1 = new Empleado("James", "32000032G", 'H', 4, 7);
		Empleado empleado2 = new Empleado("Ada Lovelace", "32000031R", 'F');
		empleado2.incrAnyo();
		empleado1.setCategoria(9);

		// Actualizamos datos en la Base De Datos sobre los Empleados
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		conn = ConexionBBDD.getConnection();
		st = conn.createStatement();
		try {
			int numFilasAfectadas = st
					.executeUpdate("update empleados set categoria=9 where dni='" + empleado1.getDni() + "';");
			System.out.println("Datos actualizados, filas afectadas: " + numFilasAfectadas);

			double nominaUpdate = escribe(empleado1);

			numFilasAfectadas = st.executeUpdate(
					"update nominas set sueldo=" + nominaUpdate + "where dni='" + empleado1.getDni() + "';");
			System.out.println("Datos actualizados, filas afectadas: " + numFilasAfectadas);
			nominaUpdate = escribe(empleado2);

			numFilasAfectadas = st.executeUpdate(
					"update nominas set sueldo=" + nominaUpdate + "where dni='" + empleado2.getDni() + "';");
			System.out.println("Datos actualizados, filas afectadas: " + numFilasAfectadas);
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			conn.close();
			st.close();
		}

//		Map<String, String> nominas = leer.leerNominas("sueldos.dat");

		boolean salir = false;

		Scanner escanerNumero = new Scanner(System.in);
		Scanner escanerTexto = new Scanner(System.in);
		String dni;

		while (!salir) {

			System.out.println("\n1. Ver empleados");
			System.out.println("2. Ver salario de un empleado especifico");
			System.out.println("3. Recalcular y actualizar sueldo de un empleado");
			System.out.println("4. Recalcular y actualizar sueldos de todos los empleado");
			System.out.println("5. Realizar copia de seguridad de la BBDD en fichero");
			System.out.println("6. Alta de nuevos empleados");
			System.out.println("7. Salir");
			int menu = escanerNumero.nextInt();

			conn = null;
			st = null;
			rs = null;
			conn = ConexionBBDD.getConnection();

			try {
				if (menu < 0 || menu > 7) {
					System.out.println("Error porfavor selecciona una opcion del menu");
				} else {
					switch (menu) {
					case 1:
						System.out.println("Lista de todos los Empleados"); // Hacemos un select a la BBDD para obtener
																			// los
																			// empleados
						resetConexion(conn, st, rs);
						conn = ConexionBBDD.getConnection();
						st = conn.createStatement();
						rs = st.executeQuery("Select * from empleados;");
						
						
						
						LecturaEscrituraEmpleados nominas = new LecturaEscrituraEmpleados();
						while (rs.next()) {
							
							System.out.println("Nombre: " + rs.getString("nombre") + " " + "DNI: " + rs.getString("dni")
									+ " " + "Sexo: " + rs.getString("sexo") + " " + "Categoria: "
									+ rs.getInt("categoria") + " " + "Años Trabajados: "
									+ rs.getInt("anyosTrabajados"));	
							Empleado nomina = new Empleado(rs.getString("nombre"), rs.getString("dni"), rs.getString("sexo").charAt(0), rs.getInt("categoria"), rs.getInt("anyosTrabajados"));
							
						}
						
						conn.close();
						st.close();
						break;

					case 2:
						System.out.println("Ver el salario de un empleado en concreto, porfavor indique el DNI");
						resetConexion(conn, st, rs);
						conn = ConexionBBDD.getConnection();
						st = conn.createStatement();

						String nuevoEmpleado = escanerTexto.nextLine();
						rs = st.executeQuery("Select sueldo from nominas where dni=" + "'" + nuevoEmpleado + "';");

						while (rs.next()) {
							System.out.println("Sueldo es de " + rs.getDouble("sueldo") + " € del empleado con DNI: "
									+ nuevoEmpleado);
						}
						conn.close();
						st.close();
						break;

					case 3:
						boolean exit = false;
						do {
							System.out.println("\nSubMenu - Actualizar datos de un Empleado");
							System.out.println("1 - Actualizar DNI");
							System.out.println("2 - Actualizar SEXO");
							System.out.println("3 - Actualizar CATEGORIA");
							System.out.println("4 - Actualizar AÑOS TRABAJADOS");
							System.out.println("5 - Salir al menu anterior\n");
							int subMenu = escanerNumero.nextInt();
							if (subMenu < 1 || subMenu > 5) {
								System.out.println("Porfavor debe elegir una opcion del SubMenu");
							} else {
								switch (subMenu) {
								case 1:
									System.out.println("Has elegido actualizar el dni de un Empleado");

									resetConexion(conn, st, rs);
									conn = ConexionBBDD.getConnection();
									st = conn.createStatement();

									System.out
											.println("Indica el dni del emppleado para actualizr el dni a actualizar");
									String dniActual = escanerTexto.nextLine();
									System.out.println("Indica el nuevo dni");
									String nuevoDNi = escanerTexto.nextLine();
									int numFilasAfectadas = st.executeUpdate("update empleados set dni=" + "'"
											+ nuevoDNi + "'" + " where dni=" + "'" + dniActual + "';");
									System.out.println("Filas Afectadas: " + numFilasAfectadas);

									rs = st.executeQuery("Select * from empleados where dni=" + "'" + dniActual + "';");
									File borrar = new File(
											"../DWES_AutoEvaluacion2_Yuri_Cabrero_Zayas/src/main/java/autoevaluacion2/srdplas/laboral/empleados/empleados.txt");
									FileReader frr = new FileReader(borrar);
									frr.close();
									borrar.delete();
									LecturaEscrituraEmpleados actualizar = new LecturaEscrituraEmpleados();

									conn = ConexionBBDD.getConnection();
									st = conn.createStatement();
									rs = st.executeQuery("Select * from empleados;");

									while (rs.next()) {
										Empleado trabajador2 = new Empleado(rs.getString("nombre"), rs.getString("dni"),
												rs.getString("sexo").charAt(0), rs.getInt("categoria"),
												rs.getInt("anyosTrabajados"));
										actualizar.escribirFichero(trabajador2);
									}
									conn.close();
									st.close();
									break;

								case 2:
									System.out.println("Has elegido actualizar el sexo de un Empleado");

									resetConexion(conn, st, rs);
									conn = ConexionBBDD.getConnection();
									st = conn.createStatement();
									System.out.println("Indica el dni del empleado para actulizar el sexo");
									dniActual = escanerTexto.nextLine();
									System.out.println("Indica el sexo");
									String nuevoSexo = escanerTexto.nextLine();
									numFilasAfectadas = st.executeUpdate("update empleados set sexo=" + "'" + nuevoSexo
											+ "'" + "where dni=" + "'" + dniActual + "';");
									
									System.out.println("Filas Afectadas: " + numFilasAfectadas);
									
									rs = st.executeQuery("Select * from empleados where dni=" + "'" + dniActual + "';");
					
									borrar = new File(
											"../DWES_AutoEvaluacion2_Yuri_Cabrero_Zayas/src/main/java/autoevaluacion2/srdplas/laboral/empleados/empleados.txt");
									frr = new FileReader(borrar);
									frr.close();
									borrar.delete();
									actualizar = new LecturaEscrituraEmpleados();

									conn = ConexionBBDD.getConnection();
									st = conn.createStatement();
									rs = st.executeQuery("Select * from empleados;");

									while (rs.next()) {
										Empleado trabajador2 = new Empleado(rs.getString("nombre"), rs.getString("dni"),
												rs.getString("sexo").charAt(0), rs.getInt("categoria"),
												rs.getInt("anyosTrabajados"));
										actualizar.escribirFichero(trabajador2);
									}
									conn.close();
									st.close();
									break;

								case 3:
									System.out.println("Has elegido actulizar la categoria de un Empleado");

									resetConexion(conn, st, rs);
									conn = ConexionBBDD.getConnection();
									st = conn.createStatement();
									System.out.println("Indica el dni del empleado para actulizar su categoria");
									dniActual = escanerTexto.nextLine();
									System.out.println("Indique la nueva categoria del empleado");
									int nuevaCategoria = escanerNumero.nextInt();

									numFilasAfectadas = st.executeUpdate("update empleados set categoria=" + "'"
											+ nuevaCategoria + "'" + "where dni=" + "'" + dniActual + "';");
									System.out.println("Filas Afectadas: " + numFilasAfectadas);

									// Hacemos un select para obtener los datos del empleado
									rs = st.executeQuery("Select * from empleados where dni=" + "'" + dniActual + "';");
									borrar = new File(
											"../DWES_AutoEvaluacion2_Yuri_Cabrero_Zayas/src/main/java/autoevaluacion2/srdplas/laboral/empleados/empleados.txt");
									frr = new FileReader(borrar);
									frr.close();
									borrar.delete();
									actualizar = new LecturaEscrituraEmpleados();

									conn = ConexionBBDD.getConnection();
									st = conn.createStatement();
									rs = st.executeQuery("Select * from empleados;");

									while (rs.next()) {
										Empleado trabajador2 = new Empleado(rs.getString("nombre"), rs.getString("dni"),
												rs.getString("sexo").charAt(0), rs.getInt("categoria"),
												rs.getInt("anyosTrabajados"));
										actualizar.escribirFichero(trabajador2);
									}
									conn.close();
									st.close();
									break;
								case 4:
									System.out.println("Has elegido actulizar los años trabajados");
									resetConexion(conn, st, rs);
									conn = ConexionBBDD.getConnection();
									st = conn.createStatement();
									System.out.println("Indica el dni del empleado para actulizar su categoria");
									dniActual = escanerTexto.nextLine();
									System.out.println("Indique la nueva categoria del empleado");
									int nuevosAnyosTrabajados = escanerNumero.nextInt();

									numFilasAfectadas = st.executeUpdate("update empleados set anyosTrabajados=" + "'"
											+ nuevosAnyosTrabajados + "'" + "where dni=" + "'" + dniActual + "';");
									System.out.println("Filas Afectadas: " + numFilasAfectadas);

									// Hacemos un select para obtener los datos del empleado
									rs = st.executeQuery("Select * from empleados where dni=" + "'" + dniActual + "';");
									borrar = new File(
											"../DWES_AutoEvaluacion2_Yuri_Cabrero_Zayas/src/main/java/autoevaluacion2/srdplas/laboral/empleados/empleados.txt");
									frr = new FileReader(borrar);
									frr.close();
									borrar.delete();
									actualizar = new LecturaEscrituraEmpleados();

									conn = ConexionBBDD.getConnection();
									st = conn.createStatement();
									rs = st.executeQuery("Select * from empleados;");

									while (rs.next()) {
										Empleado trabajador2 = new Empleado(rs.getString("nombre"), rs.getString("dni"),
												rs.getString("sexo").charAt(0), rs.getInt("categoria"),
												rs.getInt("anyosTrabajados"));
										actualizar.escribirFichero(trabajador2);
									}
									conn.close();
									st.close();
									break;
								case 5:
									System.out.println("Volviendo al menu Anterior...");
									exit = true;
									break;
								}
							}
						} while (exit == false);
						break;

					case 4:
						System.out.println("Actualizando todos los salarios de los empleados");
						resetConexion(conn, st, rs);
						conn=ConexionBBDD.getConnection();
						st = conn.createStatement();
						rs = st.executeQuery("Select * from empleados;");
						try {
							File actSala = new File(
								"../DWES_AutoEvaluacion2_Yuri_Cabrero_Zayas/src/main/java/autoevaluacion2/srdplas/laboral/empleados/empleados.txt");
						FileReader frs = new FileReader(actSala);
						frs.close();
						actSala.delete();
						
							
						}catch(Exception e) {
							File actSala = new File(
									"../DWES_AutoEvaluacion2_Yuri_Cabrero_Zayas/src/main/java/autoevaluacion2/srdplas/laboral/empleados/empleados.txt");
						}

						nominas = new LecturaEscrituraEmpleados();
						while (rs.next()) {
							Empleado nomina = new Empleado(rs.getString("nombre"), rs.getString("dni"), rs.getString("sexo").charAt(0), rs.getInt("categoria"), rs.getInt("anyosTrabajados"));
							nominas.escribirFicheroNominas(nomina);
							nominas.escribirFichero(nomina);
						}
						
						conn.close();
						st.close();
						break;
				

					case 5:
						System.out.println("5. Realizar copia de seguridad de la BBDD en fichero");

						// Borramos el fichero para cargar todos los empleados, de "forma incremental"
						File borrar = new File(
								"../DWES_AutoEvaluacion2_Yuri_Cabrero_Zayas/src/main/java/autoevaluacion2/srdplas/laboral/empleados/empleados.backup");
						FileReader frr = new FileReader(borrar);
						borrar = new File("../DWES_AutoEvaluacion2_Yuri_Cabrero_Zayas/src/main/java/autoevaluacion2/srdplas/laboral/empleados/empleados.backup");
						frr.close();
						borrar.delete();
						LecturaEscrituraEmpleados copiaSeg = new LecturaEscrituraEmpleados();

						conn = ConexionBBDD.getConnection();
						st = conn.createStatement();
						rs = st.executeQuery("Select * from empleados;");

						while (rs.next()) {
							Empleado trabajador2 = new Empleado(rs.getString("nombre"), rs.getString("dni"),
									rs.getString("sexo").charAt(0), rs.getInt("categoria"),
									rs.getInt("anyosTrabajados"));
							copiaSeg.copiaSeguridad(trabajador2);
						}
						conn.close();
						st.close();
						break;

					case 6:
						System.out.println("6. Alta de nuevos empleados");
						LecturaEscrituraEmpleados leer = new LecturaEscrituraEmpleados();
						conn = ConexionBBDD.getConnection();
						leer.altaEmpleado(conn);
						break;

					case 7:
						System.out.println("Hasta la proxima");
						salir = true;

					}
				}

			} catch (InputMismatchException e) {
				menu = -1;

			}

		}

	}

	/**
	 * 
	 * @param emp1
	 * @param emp2 Imprime datos y salario de los parametros
	 */
	private static double escribe(Empleado emp1) {
		Nomina nomina = new Nomina();
		return nomina.sueldo(emp1);

	}

	private static void resetConexion(Connection conn, Statement st, ResultSet rs) throws SQLException {
		conn = null;
		st = null;
		rs = null;

	}
}
