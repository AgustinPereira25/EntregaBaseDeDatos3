package logica.objetos;

import java.io.Serializable;

public class Juguete implements Serializable {
	private static final long serialVersionUID = -707615038326196787L;
	private int numero;
	private String descripcion;

	public Juguete(int numero, String descripcion) {
		this.numero = numero;
		this.descripcion = descripcion;
	}

	public Juguete() {
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
