package persistencia.fabrica;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

import logica.excepciones.PersistenciaException;
import persistencia.daos.DAOJuguetes;
import persistencia.daos.DAONinos;
import persistencia.daos.IDAOJuguetes;
import persistencia.daos.IDAONinos;
import persistencia.poolConexiones.IPoolConexiones;
import persistencia.poolConexiones.PoolConexiones;

public class FabricaMySQL implements FabricaAbstracta, Serializable {
	private static final long serialVersionUID = 6435017813367550700L;

	@Override
	public IDAONinos crearIDAONinos() {
		return new DAONinos();
	}

	@Override
	public IDAOJuguetes crearIDAOJuguetes(int num) {
		return new DAOJuguetes(num);
	}

	@Override
	public IPoolConexiones crearIPoolConexiones()
			throws ClassNotFoundException, FileNotFoundException, IOException, PersistenciaException {
		return new PoolConexiones();
	}

}
