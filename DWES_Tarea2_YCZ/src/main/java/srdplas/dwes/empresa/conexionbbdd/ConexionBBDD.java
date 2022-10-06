package srdplas.dwes.empresa.conexionbbdd;
import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBBDD {
	private static String baseDatos = "autoevaluacion";
	private static String baseDatosUrl = "jdbc:mysql://localhost:3306/" + baseDatos;
	private static String usuario = "yuri";
	private static String password = "yuri";

	/**
	 * 
	 * @return conexion
	 * @throws SQLException Conecta con la BBDD
	 */
	 private static BasicDataSource dataSource = null;
	 
	 private static DataSource getDataSource() {
	  if (dataSource == null) {
	   dataSource = new BasicDataSource();
	   dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
	   dataSource.setUsername(usuario);
	   dataSource.setPassword(password);
	   dataSource.setUrl(baseDatosUrl);
	   dataSource.setInitialSize(20);
	   dataSource.setMaxIdle(15);
	   dataSource.setMaxTotal(20);
	   dataSource.setMaxWaitMillis(5000);
	  }
	  return dataSource;
	 }
	 
	 public static Connection getConnection() throws SQLException {
	  return getDataSource().getConnection();
	 }
	}


