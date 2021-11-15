package persistencia.fabrica;

import java.io.FileNotFoundException;
import java.io.IOException;

import logica.excepciones.PersistenciaException;
import persistencia.daos.IDAOJuguetes;
import persistencia.daos.IDAONinos;
import persistencia.poolConexiones.IPoolConexiones;

public interface FabricaAbstracta {

	public abstract IDAONinos crearIDAONinos();

	public abstract IDAOJuguetes crearIDAOJuguetes(int num);

	public abstract IPoolConexiones crearIPoolConexiones() throws ClassNotFoundException, FileNotFoundException, IOException, PersistenciaException;
}
