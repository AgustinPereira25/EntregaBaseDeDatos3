package logica.negocio;

public class ConexionArchivo implements IConexion {
	private boolean modifica;

	public ConexionArchivo(boolean mod) {
		modifica = mod;
	}

	public boolean getModifica() {
		return modifica;
	}
}
