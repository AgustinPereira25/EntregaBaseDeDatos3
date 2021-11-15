
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class Main {
	public static void main(String[] args) {
		try {
			/*
			 * EDITO PARA AGREGAR EL ARCHIVO DE CONFIG
			 * 
			 */
			Properties p = new Properties();
			String archivoConfiguracion = "config/config.properties";
			p.load(new FileInputStream(archivoConfiguracion));
			String driver = p.getProperty("driver");
			String url = p.getProperty("url");
			String user = p.getProperty("user");
			String password = p.getProperty("password");

			/* 1. cargo dinamicamente el driver de MySQL */
			// String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);

			/* 2. una vez cargado el driver, me conecto con la base de datos */
			// String url = "jdbc:mysql://localhost:3306/Prueba";
			Connection con = DriverManager.getConnection(url, user, password);

			/* 3. creo un PreparedStatement para crear la BD */
			String createDB = "CREATE DATABASE IF NOT EXISTS toyStory";
			PreparedStatement pstmt = con.prepareStatement(createDB);
			pstmt.executeUpdate();

			/* 4. creo la tabla ninos */
			String tdCreaTdNinos = "CREATE TABLE  IF NOT EXISTS toyStory.ninos (cedula INT PRIMARY KEY NOT NULL,"
					+ "nombre VARCHAR(45) NOT NULL,apellido VARCHAR(45) NOT NULL)";
			pstmt = con.prepareStatement(tdCreaTdNinos);
			pstmt.executeUpdate();
			pstmt.close();

			/* 5. creo la tabla juguetes */
			String tdCreaTdJuguetes = "CREATE TABLE IF NOT EXISTS toyStory.juguetes (" + "numero INT NOT NULL, "
					+ "descripcion VARCHAR(45) NOT NULL," + "cedulaNino INT NOT NULL,"
					+ "	INDEX FK_ninos (cedulaNino),"
					+ "	CONSTRAINT FK_ninos FOREIGN KEY (cedulaNino) REFERENCES ninos(cedula) ON UPDATE CASCADE ON DELETE CASCADE)";
			pstmt = con.prepareStatement(tdCreaTdJuguetes);
			pstmt.executeUpdate();
			pstmt.close();

			/* 7. Ingreso las tuplas en las tablas. */

			String tdIngresaJugadores1 = "INSERT INTO toyStory.ninos(cedula,nombre,apellido) VALUES (1234567,'Kevin','McCallister')";
			String tdIngresaJugadores2 = "INSERT INTO toyStory.ninos(cedula,nombre,apellido) VALUES (2345678,'Matilda','Wormwood')";
			String tdIngresaJugadores3 = "INSERT INTO toyStory.ninos(cedula,nombre,apellido) VALUES (3456789,'Harry','Potter')";
			String tdIngresaJugadores4 = "INSERT INTO toyStory.ninos(cedula,nombre,apellido) VALUES (4567890,'Merlina','Adams')";
			pstmt = con.prepareStatement(tdIngresaJugadores1);
			pstmt.executeUpdate();
			pstmt = con.prepareStatement(tdIngresaJugadores2);
			pstmt.executeUpdate();
			pstmt = con.prepareStatement(tdIngresaJugadores3);
			pstmt.executeUpdate();
			pstmt = con.prepareStatement(tdIngresaJugadores4);
			pstmt.executeUpdate();
			pstmt.close();

			/* 7. por ultimo, cierro la conexion con la base de datos */
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
