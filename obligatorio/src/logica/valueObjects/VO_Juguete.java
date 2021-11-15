package logica.valueObjects;

import java.io.Serializable;

public class VO_Juguete implements Serializable {
	private static final long serialVersionUID = -8707375267447779007L;
	private int numero;
	private String descripcion;
	private int cedulaNino;

	public VO_Juguete(int numero, String descripcion, int cedulaNino) {
		super();
		this.numero = numero;
		this.descripcion = descripcion;
		this.cedulaNino = cedulaNino;
	}

	public int getNumero() {
		return numero;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public int getCedulaNino() {
		return cedulaNino;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setCedulaNino(int cedulaNino) {
		this.cedulaNino = cedulaNino;
	}

}
