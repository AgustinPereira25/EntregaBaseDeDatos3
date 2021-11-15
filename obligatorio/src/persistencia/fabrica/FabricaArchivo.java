package persistencia.fabrica;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

import persistencia.daos.DAOJuguetesArchivo;
import persistencia.daos.DAONinosArchivo;
import persistencia.daos.IDAOJuguetes;
import persistencia.daos.IDAONinos;
import persistencia.poolConexiones.IPoolConexiones;
import persistencia.poolConexiones.PoolConexionesArchivo;

public class FabricaArchivo implements FabricaAbstracta, Serializable {
	private static final long serialVersionUID = -6103941679374934443L;

	@Override
	public IDAONinos crearIDAONinos() {
		return new DAONinosArchivo();
	}

	@Override
	public IDAOJuguetes crearIDAOJuguetes(int num) {
		return new DAOJuguetesArchivo(num);
	}

	@Override
	public IPoolConexiones crearIPoolConexiones() throws ClassNotFoundException, FileNotFoundException, IOException {
		return new PoolConexionesArchivo();
	}

}
