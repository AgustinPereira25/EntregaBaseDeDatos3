package persistencia.poolConexiones;

import logica.excepciones.PersistenciaException;
import logica.negocio.IConexion;

public interface IPoolConexiones {

	public IConexion obtenerConexion(boolean aux) throws PersistenciaException;

	public void liberarConexion(IConexion ic, boolean aux) throws PersistenciaException;
}
