package logica.negocio;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.LinkedList;

import logica.excepciones.NinoException;
import logica.excepciones.NinoNotieneJuguete;
import logica.excepciones.NinoSinJuguetesException;
import logica.excepciones.PersistenciaException;
import logica.valueObjects.VO_Juguete;
import logica.valueObjects.VO_Nino;

public interface IFachada extends Remote {
	public abstract void nuevoNino(VO_Nino voNino) throws NinoException, PersistenciaException, RemoteException;

	public abstract void nuevoJuguete(String desc, int cedN)
			throws NinoException, PersistenciaException, RemoteException;

	public abstract LinkedList<VO_Nino> listarNinos() throws NinoException, PersistenciaException, RemoteException;

	public abstract LinkedList<VO_Juguete> listarJuguetes(int cedN)
			throws NinoSinJuguetesException, PersistenciaException, RemoteException, NinoException;

	public abstract String darDescripcion(int cedN, int numJ)
			throws NinoNotieneJuguete, PersistenciaException, NinoSinJuguetesException, RemoteException;

	public abstract void borrarNinoJuguetes(int cedN) throws PersistenciaException, RemoteException, NinoException;

	public abstract int cantJugdeNino(int ced)
			throws NinoSinJuguetesException, PersistenciaException, RemoteException, NinoException;

	public abstract int cantNinos() throws NinoException, PersistenciaException, RemoteException;
}