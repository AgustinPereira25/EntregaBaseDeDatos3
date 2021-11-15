package logica.objetos;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Properties;

import logica.excepciones.LecturaArchivoException;
import logica.excepciones.PersistenciaException;
import logica.negocio.IConexion;
import logica.valueObjects.VO_Juguete;
import persistencia.daos.IDAOJuguetes;
import persistencia.fabrica.FabricaAbstracta;

public class Nino implements Serializable {
	private static final long serialVersionUID = 1L;
	private int cedula;
	private String nombre;
	private String apellido;
	private IDAOJuguetes secuencia;
	private FabricaAbstracta fabrica;

	public Nino(int cedula, String nombre, String apellido) throws PersistenciaException {
		try {
			this.cedula = cedula;
			this.nombre = nombre;
			this.apellido = apellido;
			Properties p = new Properties();
			String archivoConfiguracion = "config/config.properties";
			p.load(new FileInputStream(archivoConfiguracion));
			String nomFab = p.getProperty("fabrica");
			fabrica = (FabricaAbstracta) Class.forName(nomFab).newInstance();
			secuencia = fabrica.crearIDAOJuguetes(cedula);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
			throw new PersistenciaException("texto");
		}
	}

	public boolean tieneJuguete(int numJ, IConexion ic) throws PersistenciaException, LecturaArchivoException {
		Juguete Jug = null;
		boolean tiene = false;
		Jug = secuencia.kesimo(numJ, ic);
		if (Jug.getNumero() == numJ) {
			tiene = true;
		}

		return tiene;
	}

	public int cantidadJuguetes(IConexion ic) throws PersistenciaException, LecturaArchivoException {
		int cantidadJug = 0;
		cantidadJug = secuencia.largo(ic);
		return cantidadJug;
	}

	public void addJuguete(Juguete jug, IConexion ic) throws PersistenciaException {
		secuencia.insback(jug, ic);
	}

	public Juguete obtenerJuguete(int numJ, IConexion ic) throws PersistenciaException, LecturaArchivoException {
		Juguete Jug = null;
		Jug = secuencia.kesimo(numJ, ic);
		return Jug;
	}

	public LinkedList<VO_Juguete> listarJuguetes(IConexion ic) throws PersistenciaException, LecturaArchivoException {

		return secuencia.listarJuguetes(ic);
	}

	public void borrarJuguetes(IConexion ic) throws PersistenciaException {
		secuencia.borrarJuguetes(ic);
	}

	public int getCedula() {
		return cedula;
	}

	public void setCedula(int cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nom) {
		nombre = nom;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public IDAOJuguetes getJuguetes() {
		return secuencia;
	}

	public void setJuguetes(IDAOJuguetes juguetes) {
		this.secuencia = juguetes;
	}

}
