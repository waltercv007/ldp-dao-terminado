// connection.ConexionMySQL.java
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMySQL {
	// Atributos
	private static final String	HOST ="localhost";
	private static final String	PUERTO ="3306";
	private static final String	BASE_DE_DATOS ="db_cesta_fresca";
	private static final String	USUARIO ="root";
	private static final String	PASSWORD ="";
	private static final String	URL ="jdbc:mysql://"+HOST+":"+PUERTO+"/"+BASE_DE_DATOS+"?serverTimezone=America/Lima";
	// Metodos
	
	public static Connection obtenerConexion() throws ClassNotFoundException, SQLException {
		// Buscar el archivo .class dentro de tu librería mysql-connector-j-9.5.0.jar.
		// Cargar esa clase en la memoria RAM.
		// La JVM ve el bloque static { ... } y lo ejecuta inmediatamente.
		// Ese bloque llama a DriverManager.registerDriver(new Driver()).
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		return DriverManager.getConnection(URL,USUARIO,PASSWORD);
	
	}
		
}
