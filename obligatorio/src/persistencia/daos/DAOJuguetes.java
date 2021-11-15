package persistencia.daos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import logica.excepciones.PersistenciaException;
import logica.negocio.Conexion;
import logica.negocio.IConexion;
import logica.objetos.Juguete;
import logica.valueObjects.VO_Juguete;
import persistencia.consultas.Consultas;

public class DAOJuguetes implements IDAOJuguetes, Serializable {
	private static final long serialVersionUID = 6087904498097049089L;
	private int cedulaNino;

	public DAOJuguetes(int cedulaNino) {
		this.cedulaNino = cedulaNino;
	}

	@Override
	public void insback(Juguete jug, IConexion ic) throws PersistenciaException {
		try {
			int numeroJug = 1;
			Conexion c = (Conexion) ic;
			Connection con = c.getConexion();

			Consultas consulta = new Consultas();
			String query = consulta.obtenerUltimoJuguete();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, cedulaNino);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				numeroJug = rs.getInt(1) + 1;
			}
			rs.close();
			pstmt.close();
			query = consulta.ingresarJuguete();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, numeroJug);
			pstmt.setString(2, jug.getDescripcion());
			pstmt.setInt(3, cedulaNino);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new PersistenciaException("Error en la conexion");
		}

	}

	@Override
	public int largo(IConexion ic) throws PersistenciaException {
		int largo = 0;
		try {
			Conexion c = (Conexion) ic;
			Connection con = c.getConexion();

			Consultas consulta = new Consultas();
			String query = consulta.largoJuguetes();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, cedulaNino);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				largo = rs.getInt(1);
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			throw new PersistenciaException("Error en la conexion");
		}
		return largo;
	}

	public Juguete kesimo(int num, IConexion ic) throws PersistenciaException {
		Juguete jug = null;
		try {
			Conexion c = (Conexion) ic;
			Connection con = c.getConexion();

			Consultas consulta = new Consultas();
			String query = consulta.existeJuguete();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, cedulaNino);
			pstmt.setInt(2, num);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				jug = new Juguete(rs.getInt(1), rs.getString(2));
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			throw new PersistenciaException("Error en la conexion");
		}
		return jug;
	}

	@Override
	public LinkedList<VO_Juguete> listarJuguetes(IConexion ic) throws PersistenciaException {
		LinkedList<VO_Juguete> lista = new LinkedList<VO_Juguete>();
		try {
			Conexion c = (Conexion) ic;
			Connection con = c.getConexion();
			Consultas consulta = new Consultas();
			String query = consulta.listarJuguetes();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, cedulaNino);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				VO_Juguete jug = new VO_Juguete(rs.getInt(1), rs.getString(2), rs.getInt(3));
				lista.add(jug);
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			throw new PersistenciaException("Error en la conexion");
		}
		return lista;
	}

	@Override
	public void borrarJuguetes(IConexion ic) throws PersistenciaException {
		try {
			Conexion c = (Conexion) ic;
			Connection con = c.getConexion();

			Consultas consulta = new Consultas();
			String query = consulta.borrarJuguetesNino();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, cedulaNino);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			throw new PersistenciaException("Error en la conexion");
		}
	}
}
