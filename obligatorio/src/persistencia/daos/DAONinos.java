package persistencia.daos;

import java.io.Serializable;
import java.sql.*;
import java.util.LinkedList;
import logica.excepciones.PersistenciaException;
import logica.negocio.Conexion;
import logica.negocio.IConexion;
import logica.objetos.Nino;
import logica.valueObjects.VO_Nino;
import persistencia.consultas.Consultas;

public class DAONinos implements IDAONinos, Serializable {
	private static final long serialVersionUID = 7683020520241394761L;

	public DAONinos() {
	}

	@Override
	public boolean member(int cedula, IConexion ic) throws PersistenciaException {
		boolean existeCed = false;

		try {
			Conexion c = (Conexion) ic;
			Connection con = c.getConexion();

			Consultas consulta = new Consultas();
			String query = consulta.existeNino();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, cedula);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				existeCed = true;
			}
			rs.close();
			pstmt.close();

		} catch (SQLException e) {
			throw new PersistenciaException("Error en la conexion");
		}

		return existeCed;
	}

	@Override
	public void insert(Nino nino, IConexion ic) throws PersistenciaException {
		try {
			Conexion c = (Conexion) ic;
			Connection con = c.getConexion();
			Consultas consulta = new Consultas();
			String query = consulta.ingresarNino();
			PreparedStatement pstmt = con.prepareStatement(query);
			int ced = nino.getCedula();
			String nom = nino.getNombre();
			String ape = nino.getApellido();
			pstmt.setInt(1, ced);
			pstmt.setString(2, nom);
			pstmt.setString(3, ape);
			pstmt.executeUpdate();
			pstmt.close();

		} catch (SQLException e) {
			throw new PersistenciaException("Error en la conexion");
		}
	}

	@Override
	public Nino find(int cedula, IConexion ic) throws PersistenciaException {
		Nino nino = null;

		try {
			Conexion c = (Conexion) ic;
			Connection con = c.getConexion();

			Consultas consulta = new Consultas();
			String query = consulta.existeNino();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, cedula);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				nino = new Nino(rs.getInt(1), rs.getString(2), rs.getString(3));
			}
			rs.close();
			pstmt.close();

		} catch (SQLException e) {
			throw new PersistenciaException("Error en la conexion");
		}

		return nino;
	}

	@Override
	public void delete(int ced, IConexion ic) throws PersistenciaException {
		try {
			Conexion c = (Conexion) ic;
			Connection con = c.getConexion();

			Consultas consulta = new Consultas();
			String query = consulta.borrarNino();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, ced);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			throw new PersistenciaException("Error en la conexion");
		}
	}

	@Override
	public LinkedList<VO_Nino> listarNinos(IConexion ic) throws PersistenciaException {
		LinkedList<VO_Nino> Lista = new LinkedList<VO_Nino>();
		try {
			Conexion c = (Conexion) ic;
			Connection con = c.getConexion();
			Consultas consulta = new Consultas();
			String query = consulta.listarNinos();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				VO_Nino ninos = new VO_Nino(rs.getInt(1), rs.getString(2), rs.getString(3));
				Lista.add(ninos);
			}
		} catch (SQLException e) {
			throw new PersistenciaException("Error en la conexion");
		}
		return Lista;

	}
}
