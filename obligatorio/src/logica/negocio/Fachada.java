package logica.negocio;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.Properties;

import logica.excepciones.LecturaArchivoException;
import logica.excepciones.NinoException;
import logica.excepciones.NinoNotieneJuguete;
import logica.excepciones.NinoSinJuguetesException;
import logica.excepciones.PersistenciaException;
import logica.objetos.Juguete;
import logica.objetos.Nino;
import logica.valueObjects.VO_Juguete;
import logica.valueObjects.VO_Nino;
import persistencia.daos.IDAONinos;
import persistencia.fabrica.FabricaAbstracta;
import persistencia.poolConexiones.IPoolConexiones;

public class Fachada extends UnicastRemoteObject implements IFachada, Serializable {

	private static final long serialVersionUID = -8707375267447779007L;
	private IPoolConexiones pool;
	private IDAONinos ninos;
	private FabricaAbstracta fabrica;

	public Fachada() throws RemoteException, ClassNotFoundException, PersistenciaException {
		try {
			Properties p = new Properties();
			String archivoConfiguracion = "config/config.properties";
			p.load(new FileInputStream(archivoConfiguracion));
			String nomFab = p.getProperty("fabrica");
			try {
				fabrica = (FabricaAbstracta) Class.forName(nomFab).newInstance();
				this.pool = this.fabrica.crearIPoolConexiones();
				ninos = fabrica.crearIDAONinos();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void nuevoNino(VO_Nino voNino) throws NinoException, PersistenciaException, RemoteException {
		/*
		 * El m�todo nuevoNi�o ingresa un nuevo ni�o al sistema, chequeando que no
		 * existiera.
		 */
		IConexion icon = null;
		String msgError = "";
		Boolean errorPersistencia = false;
		boolean existeNino = false;
		try {
			icon = pool.obtenerConexion(true);
			int ced = voNino.getCedula();
			String nom = voNino.getNombre();
			String ap = voNino.getApellido();
			if (!ninos.member(ced, icon)) {
				try {
					Nino nino = new Nino(ced, nom, ap);
					ninos.insert(nino, icon);
				} catch (PersistenciaException | LecturaArchivoException e) {
					errorPersistencia = true;
					msgError = "Error de conexion";
				}
			} else {
				existeNino = true;
				msgError = "El ni�o que intenta ingresar, ya existe!";
			}
			pool.liberarConexion(icon, true);
		} catch (PersistenciaException e) {
			try {
				pool.liberarConexion(icon, false);
				errorPersistencia = true;
				msgError = "Error al obtener conexion";
			} catch (PersistenciaException b) {
				errorPersistencia = true;
				msgError = "Error al liberar conexion";
			}
		} finally {
			if (existeNino)
				throw new NinoException(msgError);
			if (errorPersistencia)
				throw new PersistenciaException(msgError);
		}
	};

	@Override
	public void nuevoJuguete(String desc, int cedN) throws RemoteException, NinoException, PersistenciaException {
		/*
		 * El m�todo nuevoJuguete ingresa un nuevo juguete al nino, chequeando que
		 * existiera.
		 */
		IConexion icon = null;
		String msgError = "";
		Boolean errorPersistencia = false;
		Boolean existenino = false;
		try {
			icon = pool.obtenerConexion(true);
			if (ninos.member(cedN, icon)) {
				existenino = true;
				Nino nino = null;
				try {
					int num_jug = 0;
					nino = ninos.find(cedN, icon);
					num_jug = nino.cantidadJuguetes(icon) + 1;
					Juguete jug = new Juguete(num_jug, desc);
					nino.addJuguete(jug, icon);
				} catch (PersistenciaException | LecturaArchivoException e) {
					errorPersistencia = true;
					msgError = "Error de conexion";
				}

			} else {
				existenino = false;
				msgError = "El ni�o al que hace referencia, no existe!";
			}
			pool.liberarConexion(icon, true);
		} catch (Exception e) {
			try {
				pool.liberarConexion(icon, false);
				errorPersistencia = true;
				msgError = "Error al obtener conexion";
			} catch (PersistenciaException b) {
				errorPersistencia = true;
				msgError = "Error al liberar conexion";
			}
		} finally {
			if (!existenino)
				throw new NinoException(msgError);
			if (errorPersistencia)
				throw new PersistenciaException(msgError);
		}
	};

	@Override
	public LinkedList<VO_Nino> listarNinos() throws RemoteException, NinoException, PersistenciaException {
		/*
		 * � El m�todo listar Ni�os devuelve un listado de todos los ni�os registrados,
		 * ordenado por c�dula.
		 */
		IConexion icon = null;
		LinkedList<VO_Nino> lista = null;
		String msgError = "";
		Boolean errorPersistencia = false;
		try {
			icon = pool.obtenerConexion(false);
			lista = ninos.listarNinos(icon);
			pool.liberarConexion(icon, true);
		} catch (Exception e) {
			try {
				pool.liberarConexion(icon, false);
				errorPersistencia = true;
				msgError = "Error al obtener conexion.";
			} catch (PersistenciaException e1) {
				errorPersistencia = true;
				msgError = "Error al liberar conexion";
			}
		} finally {
			if (lista.size() == 0) {
				msgError = "No existen ni�os en la base.";
				throw new NinoException(msgError);
			}
			if (errorPersistencia)
				throw new PersistenciaException(msgError);
		}
		return lista;
	};

	@Override
	public LinkedList<VO_Juguete> listarJuguetes(int cedN)
			throws RemoteException, NinoSinJuguetesException, PersistenciaException, NinoException {
		/*
		 * El m�todo listarJuguetes devuelve un listado de todos los juguetes de un ni�o
		 * determinado, (chequeando que dicho ni�o est� registrado) ordenado por n�mero
		 * de juguete.
		 */
		IConexion icon = null;
		LinkedList<VO_Juguete> lista = null;
		String msgError = "";
		Boolean errorPersistencia = false;
		Boolean existenino = false;
		try {
			icon = pool.obtenerConexion(false);
			if (ninos.member(cedN, icon)) {
				existenino = true;
				Nino n = ninos.find(cedN, icon);
				lista = n.listarJuguetes(icon);
			} else {
				existenino = false;
				msgError = "El ni�o al que hace referencia, no existe!";
			}
			pool.liberarConexion(icon, true);
		} catch (PersistenciaException | LecturaArchivoException e) {
			try {
				pool.liberarConexion(icon, false);
			} catch (PersistenciaException e1) {
				errorPersistencia = true;
				msgError = "Error de acceso a los datos";
			}
		} finally {
			if (!existenino) {
				throw new NinoException(msgError);
			} else {
				if (lista == null) {
					msgError = "El ni�o no tiene juguetes.";
					throw new NinoSinJuguetesException(msgError);
				}
			}
			if (errorPersistencia)
				throw new PersistenciaException(msgError);
		}
		return lista;
	};

	@Override
	public String darDescripcion(int cedN, int numJ)
			throws RemoteException, NinoNotieneJuguete, PersistenciaException, NinoSinJuguetesException {
		/*
		 * El m�todo darDescripci�n devuelve la descripci�n de un juguete, dados su
		 * n�mero y la c�dula del ni�o que le corresponde (chequeando que el ni�o exista
		 * y tenga un juguete con ese n�mero).
		 */
		String resu = "";
		String msgError = "";
		Boolean errorPersistencia = false;
		Boolean existeNino = false;
		Boolean existeJuguete = false;
		IConexion icon = null;
		try {
			icon = pool.obtenerConexion(false);
			existeNino = ninos.member(cedN, icon);
			if (existeNino) {
				existeNino = true;
				Nino n = ninos.find(cedN, icon);
				Juguete jug = n.obtenerJuguete(numJ, icon);
				if (jug != null) {
					existeJuguete = true;
					resu = jug.getDescripcion();
				} else {
					msgError = "El ni�o no cuenta con ese juguete";
				}
			} else
				msgError = "El ni�o al que hace referencia, no existe!";
			pool.liberarConexion(icon, true);
		} catch (PersistenciaException | LecturaArchivoException e) {
			try {
				pool.liberarConexion(icon, false);
			} catch (PersistenciaException e1) {
				errorPersistencia = true;
				msgError = "Error de acceso a los datos";
			}

		} finally {
			if (!existeNino) {
				throw new NinoSinJuguetesException(msgError);
			}
			if (!existeJuguete) {
				throw new NinoNotieneJuguete(msgError);
			}
			if (errorPersistencia) {
				throw new PersistenciaException(msgError);
			}
		}
		return resu;
	};

	@Override
	public void borrarNinoJuguetes(int cedN) throws RemoteException, PersistenciaException, NinoException {
		/*
		 * El m�todo borrarNi�oJuguetes elimina del sistema al ni�o con la c�dula
		 * ingresada, y tambi�n elimina a todos sus juguetes, chequeando que el ni�o
		 * est� registrado.
		 */
		IConexion icon = null;
		String msgError = "";
		Boolean errorPersistencia = false;
		Boolean existeNino = false;
		try {
			icon = pool.obtenerConexion(true);
			existeNino = ninos.member(cedN, icon);
			if (existeNino) {
				existeNino = true;
				Nino n = null;
				try {
					n = ninos.find(cedN, icon);
					n.borrarJuguetes(icon);
					ninos.delete(cedN, icon);
				} catch (PersistenciaException | LecturaArchivoException e) {
					errorPersistencia = true;
					msgError = "Error de conexion";
				}
			} else
				msgError = "El ni�o al que hace referencia, no existe!";
			pool.liberarConexion(icon, true);
		} catch (PersistenciaException e) {
			pool.liberarConexion(icon, false);
			errorPersistencia = true;
			msgError = "error de acceso a los datos";
		} finally {
			if (!existeNino) {
				throw new NinoException(msgError);
			}
			if (errorPersistencia) {
				throw new PersistenciaException(msgError);
			}
		}
	}

	@Override
	public int cantJugdeNino(int cedN) throws NinoSinJuguetesException, PersistenciaException, NinoException {
		/*
		 * El m�todo cantJugdeNino, devuelve la cantidad de juguetes del nino ingresado.
		 */
		IConexion icon = null;
		int resu = 0;
		String msgError = "";
		Boolean errorPersistencia = false;
		Boolean existeNino = false;
		int cantJug = 0;
		try {
			icon = pool.obtenerConexion(true);
			existeNino = ninos.member(cedN, icon);
			if (existeNino) {
				existeNino = true;
				Nino n = ninos.find(cedN, icon);
				cantJug = n.cantidadJuguetes(icon);
			} else {
				msgError = "El ni�o al que hace referencia, no existe!";
			}
			pool.liberarConexion(icon, true);
		} catch (PersistenciaException | LecturaArchivoException e) {
			try {
				pool.liberarConexion(icon, false);
			} catch (PersistenciaException e1) {
				errorPersistencia = true;
				msgError = "Error de acceso a los datos";
			}
		} finally {
			if (!existeNino) {
				throw new NinoException(msgError);
			}
			if (cantJug == 0) {
				msgError = "El ni�o no tiene juguetes.";
				throw new NinoSinJuguetesException(msgError);
			}
			if (errorPersistencia)
				throw new PersistenciaException(msgError);
		}
		return resu;
	}

	@Override
	public int cantNinos() throws NinoException, PersistenciaException {
		/*
		 * El m�todo cantNinos, devuelve la cantidad de ninos ingresados.
		 */
		IConexion icon = null;
		LinkedList<VO_Nino> lista = null;
		int resu = 0;
		String msgError = "";
		Boolean errorPersistencia = false;
		Boolean errorNinos = true;
		try {
			icon = pool.obtenerConexion(true);
			lista = ninos.listarNinos(icon);
			if (lista != null) {
				errorNinos = false;
				resu = lista.size();
			}
			pool.liberarConexion(icon, true);
		} catch (PersistenciaException | LecturaArchivoException e) {
			try {
				pool.liberarConexion(icon, false);
			} catch (PersistenciaException e1) {
			}
			errorPersistencia = true;
			msgError = "Error de acceso a los datos";
		} finally {
			if (errorNinos) {
				msgError = "No hay ninos ingresados.";
				throw new NinoException(msgError);
			}
			if (errorPersistencia)
				throw new PersistenciaException(msgError);
		}
		return resu;
	}
}
