package persistencia.daos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;

import logica.excepciones.LecturaArchivoException;
import logica.excepciones.PersistenciaException;
import logica.negocio.IConexion;
import logica.objetos.Nino;
import logica.valueObjects.VO_Nino;

public class DAONinosArchivo implements IDAONinos, Serializable {

	private static final long serialVersionUID = 1L;
	private File folder;

	public DAONinosArchivo() {
		this.folder = new File("C:/Users/Usuario/Archivos/Ninos");
	}

	private Nino leerNinoDeArchivo(String nomArch) throws LecturaArchivoException {
		Nino nino = null;
		FileReader fr = null;
		BufferedReader br = null;
		File file = new File(nomArch);
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			String linea;
			if ((linea = br.readLine()) != null) {
				int ced = Integer.parseInt(linea);
				linea = br.readLine();
				String nom = linea;
				linea = br.readLine();
				String ape = linea;
				nino = new Nino(ced, nom, ape);
			}

		} catch (IOException | PersistenciaException e) {
			throw new LecturaArchivoException("No se ha podido completar la lectura");
		} finally {
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				throw new LecturaArchivoException("No se ha podido completar la lectura");
			}
		}
		return nino;
	}

	@Override
	public boolean member(int cedula, IConexion ic) throws PersistenciaException {

		String nomArch = "\\nino-" + cedula + ".txt";
		Path aux = Paths.get(this.folder.getPath(), nomArch);
		boolean existe = false;
		File file = new File(aux.toString());
		if (file.exists()) {
			existe = true;
		}
		return existe;
	}

	@Override
	public void insert(Nino nino, IConexion ic) throws PersistenciaException {
		String nomArch = "\\nino-" + nino.getCedula() + ".txt";
		Path aux = Paths.get(this.folder.getPath(), nomArch);
		FileWriter fw = null;
		BufferedWriter bw = null;
		File file = new File(aux.toString());
		try {
			file.createNewFile();
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			bw.write(String.valueOf(nino.getCedula()));
			bw.newLine();
			bw.write(nino.getNombre());
			bw.newLine();
			bw.write(nino.getApellido());
		} catch (IOException e) {
			e.printStackTrace();
			throw new PersistenciaException("Error en la escritura");
		} finally {
			try {
				bw.close();
			} catch (IOException e) {
				throw new PersistenciaException("No se ha podido completar la lectura");
			}
		}
	}

	@Override
	public Nino find(int cedula, IConexion ic) throws LecturaArchivoException {
		Nino nino = null;
		String nomArch = "\\nino-" + cedula + ".txt";
		Path aux = Paths.get(folder.getPath(), nomArch);
		File file = new File(aux.toString());
		if (file.exists()) {
			nino = leerNinoDeArchivo(aux.toString());
		}
		return nino;
	}

	@Override
	public void delete(int ced, IConexion ic) throws PersistenciaException {
		String nomArch = "\\nino-" + ced + ".txt";
		Path aux = Paths.get(this.folder.getPath(), nomArch);
		File file = new File(aux.toString());
		if (file.exists()) {
			file.delete();
		}
	}

	@Override
	public LinkedList<VO_Nino> listarNinos(IConexion ic) throws LecturaArchivoException {
		LinkedList<VO_Nino> list = new LinkedList<VO_Nino>();
		Nino nino = null;
		for (File archNino : this.folder.listFiles()) {
			nino = leerNinoDeArchivo(archNino.getPath());
			VO_Nino VO_Nino = new VO_Nino(nino.getCedula(), nino.getNombre(), nino.getApellido());
			list.add(VO_Nino);
		}
		return list;
	}

}
