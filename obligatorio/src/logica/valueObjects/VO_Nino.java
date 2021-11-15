package logica.valueObjects;

import java.io.Serializable;

public class VO_Nino implements Serializable {
	private static final long serialVersionUID = -8707375267847779007L;
	private int cedula;
	private String nombre;
	private String apellido;

	public VO_Nino(int ced, String name, String lastName) {
		cedula = ced;
		nombre = name;
		apellido = lastName;
	}

	public int getCedula() {
		return cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setCedula(int ced) {
		cedula = ced;
	}

	public void setNombre(String nom) {
		nombre = nom;
	}

	public void setApellido(String ap) {
		apellido = ap;
	};

}
