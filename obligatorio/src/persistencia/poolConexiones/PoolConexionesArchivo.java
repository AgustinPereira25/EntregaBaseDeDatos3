package persistencia.poolConexiones;

import logica.excepciones.PersistenciaException;
import logica.negocio.ConexionArchivo;
import logica.negocio.IConexion;

public class PoolConexionesArchivo implements IPoolConexiones {

	private int escritores;
	private int lectores;

	public PoolConexionesArchivo() {
		escritores = 0;
		lectores = 0;
	}

	@Override
	public synchronized IConexion obtenerConexion(boolean modifica) throws PersistenciaException {
		IConexion ret = null;
		boolean termino = false;
		while (!termino) {
			if (modifica) {
				if (escritores > 0 || lectores > 0) {
					try {
						this.wait();
					} catch (InterruptedException ex) {
					}
				}
				termino = true;
			} else {
				if (escritores > 0) {
					try {
						this.wait();
					} catch (InterruptedException ex) {
						throw new PersistenciaException("Error al acceder a los datos");
					}
				} else {
					termino = true;
				}
			}
		}
		if (modifica) {
			escritores++;
		} else {
			lectores++;
		}
		ret = new ConexionArchivo(modifica);
		return ret;
	}

	@Override
	public synchronized void liberarConexion(IConexion con, boolean ok) {
		if (((ConexionArchivo) con).getModifica()) {
			escritores--;
		} else {
			lectores--;
		}
		this.notify();
	}
}
