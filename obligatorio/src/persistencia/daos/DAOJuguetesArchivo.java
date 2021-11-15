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
import logica.objetos.Juguete;
import logica.valueObjects.VO_Juguete;

public class DAOJuguetesArchivo implements IDAOJuguetes, Serializable {
	private static final long serialVersionUID = -673870982303770045L;
	private File folder;
	private int cedulanino;

	public DAOJuguetesArchivo(int cedNino) {
		folder = new File("C:/Users/Usuario/Archivos/Juguetes");
		cedulanino = cedNino;
	}

	@SuppressWarnings("unused")
	private Juguete leerJugueteDeArchivo(String archivo) throws LecturaArchivoException {

		Juguete jug = null;
		jug = new Juguete();
		FileReader fr = null;
		BufferedReader br = null;
		File file = new File(archivo);
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			String linea;
			if ((linea = br.readLine()) != null) {
				jug.setNumero(Integer.parseInt(linea));
				linea = br.readLine();
				jug.setDescripcion(linea);
			}
		} catch (IOException e) {
			throw new LecturaArchivoException("No se ha podido completar la lectura");
		} finally {
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				throw new LecturaArchivoException("No se ha podido completar la lectura");
			}
		}
		return jug;
	}

	@Override
	public void insback(Juguete jug, IConexion ic) throws PersistenciaException {
		String nomArch = "\\juguetes-" + cedulanino + ".txt";
		Path archivo = Paths.get(this.folder.getPath(), nomArch);
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			File f = new File(archivo.toString());
			if (!f.exists()) {
				f.createNewFile();
			}
			fw = new FileWriter(archivo.toString(), true);
			bw = new BufferedWriter(fw);
			bw.write(String.valueOf(jug.getNumero()));
			bw.newLine();
			bw.write(jug.getDescripcion());
			bw.newLine();
		} catch (IOException e) {
			throw new PersistenciaException("Error al abrir/crear el archivo");
		} finally {
			if (bw != null) {
				try {
					bw.flush();
					bw.close();
				} catch (IOException e) {
					throw new PersistenciaException("Error al cerrar el archivo");
				}
			}
		}
	}

	@Override
	public int largo(IConexion ic) throws PersistenciaException, LecturaArchivoException {
		// Devuelve la cantidad de
		// juguetes de niño.
		int largo = 0;
		largo = listarJuguetes(ic).size();
		return largo;
	}

	@Override
	public Juguete kesimo(int num, IConexion ic) throws PersistenciaException, LecturaArchivoException {
		String nomArch = "\\juguetes-" + cedulanino + ".txt";
		Path archivo = Paths.get(this.folder.getPath(), nomArch);
		Juguete kesimo = null;
		boolean salir = false;
		FileReader fr = null;
		BufferedReader br = null;
		File f = new File(archivo.toString());
		try {
			fr = new FileReader(f);
			br = new BufferedReader(fr);
			String linea;
			while ((linea = br.readLine()) != null && !salir) {
				int id = Integer.parseInt(linea);
				linea = br.readLine();
				String des = linea;
				if (id == num) {
					salir = true;
					kesimo = new Juguete(num, des);
				}
			}
		} catch (IOException e) {
			throw new LecturaArchivoException("No se ha podido completar la lectura");
		} finally {
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				throw new LecturaArchivoException("No se ha podido completar la lectura");
			}
		}
		return kesimo;
	}

	@Override
	public LinkedList<VO_Juguete> listarJuguetes(IConexion ic) throws PersistenciaException, LecturaArchivoException {
		String nomArch = "\\juguetes-" + cedulanino + ".txt";
		Path archivo = Paths.get(this.folder.getPath(), nomArch);
		LinkedList<VO_Juguete> listaJug = new LinkedList<VO_Juguete>();
		FileReader fr = null;
		BufferedReader br = null;
		File f = new File(archivo.toString());
		if (f.exists()) {
			try {
				fr = new FileReader(f);
				br = new BufferedReader(fr);
				String linea;
				while ((linea = br.readLine()) != null) {
					int id = Integer.parseInt(linea);
					linea = br.readLine();
					String des = linea;
					VO_Juguete voJug = new VO_Juguete(id, des, cedulanino);
					listaJug.add(voJug);
				}

			} catch (IOException e) {
				throw new LecturaArchivoException("No se ha podido completar la lectura");
			} finally {
				try {
					br.close();
					fr.close();
				} catch (IOException e) {
					throw new LecturaArchivoException("No se ha podido completar la lectura");
				}
			}
		}
		return listaJug;
	}

	@Override
	public void borrarJuguetes(IConexion ic) throws PersistenciaException {
		String nomArch = "\\juguetes-" + cedulanino + ".txt";
		Path archivo = Paths.get(this.folder.getPath(), nomArch);
		File f = new File(archivo.toString());
		f.delete();
	}
}