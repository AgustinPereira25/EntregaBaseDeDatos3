package persistencia.daos;

import java.util.LinkedList;

import logica.excepciones.LecturaArchivoException;
import logica.excepciones.PersistenciaException;
import logica.negocio.IConexion;
import logica.objetos.Nino;
import logica.valueObjects.VO_Nino;

public interface IDAONinos {

	public abstract boolean member(int cedula, IConexion ic) throws PersistenciaException;

	public abstract void insert(Nino nino, IConexion ic) throws PersistenciaException, LecturaArchivoException;

	public abstract Nino find(int cedula, IConexion ic) throws LecturaArchivoException, PersistenciaException;

	public abstract void delete(int ced, IConexion ic) throws PersistenciaException;

	public abstract LinkedList<VO_Nino> listarNinos(IConexion ic) throws LecturaArchivoException, PersistenciaException;

}
