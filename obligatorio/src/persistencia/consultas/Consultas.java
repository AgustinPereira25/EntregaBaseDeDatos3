package persistencia.consultas;

import java.io.Serializable;

public class Consultas implements Serializable {

	private static final long serialVersionUID = 7045648461527561867L;

	public String existeNino() {
		String query;
		query = "SELECT * FROM ninos WHERE cedula =(?)";
		return query;
	}

	public String existenNinos() {
		String query;
		query = "SELECT * FROM ninos WHERE";
		return query;
	}

	public String existeJuguete() {
		String query;
		query = "SELECT * FROM juguetes WHERE cedulaNino =(?) AND numero=(?)";
		return query;
	}

	public String ingresarNino() {
		String insert;
		insert = "INSERT INTO ninos (cedula,nombre,apellido) " + "VALUES (?,?,?)";
		return insert;
	}

	public String ingresarJuguete() {
		String insert;
		insert = "INSERT INTO juguetes (numero,descripcion,cedulaNino) " + "VALUES(?,?,?)";
		return insert;
	}

	public String obtenerNroJuguete() {
		String query;
		query = "SELECT COUNT(*) as cant_juguetes from juguetes WHERE cedulaNino=(?)";
		return query;
	}

	public String listarNinos() {
		String query;
		query = "SELECT cedula,nombre,apellido from ninos ORDER BY cedula ";
		return query;
	}

	public String listarJuguetes() {
		String query;
		query = "SELECT numero,descripcion,cedulaNino from juguetes WHERE cedulaNino =(?) ORDER BY numero ";
		return query;
	}

	public String obtenerDescricpion() {
		String query;
		query = "SELECT descripcion from juguetes WHERE numero =(?) AND cedulaNino=(?)";
		return query;
	}

	public String borrarJuguetesNino() {
		String borrar;
		borrar = "DELETE FROM juguetes WHERE cedulaNino =(?)";
		return borrar;
	}

	public String borrarNino() {
		String borrar;
		borrar = "DELETE FROM ninos WHERE cedula =(?)";
		return borrar;
	}

	public String largoJuguetes() {
		return "SELECT COUNT(numero) FROM JUGUETES WHERE cedulaNino = (?)";
	}

	public String obtenerUltimoJuguete() {
		return "SELECT numero FROM Juguetes where cedulaNino = (?) ORDER BY numero DESC;";
	}

}
