package persistencia.daos;

import java.util.LinkedList;

import logica.excepciones.LecturaArchivoException;
import logica.excepciones.PersistenciaException;
import logica.negocio.IConexion;
import logica.objetos.Juguete;
import logica.valueObjects.VO_Juguete;

public interface IDAOJuguetes {

	public abstract void insback(Juguete jug, IConexion ic) throws PersistenciaException;

	public abstract int largo(IConexion ic) throws PersistenciaException, LecturaArchivoException;

	public abstract Juguete kesimo(int numero, IConexion ic) throws PersistenciaException, LecturaArchivoException;

	public abstract LinkedList<VO_Juguete> listarJuguetes(IConexion ic) throws PersistenciaException, LecturaArchivoException;

	public abstract void borrarJuguetes(IConexion ic) throws PersistenciaException;
}
