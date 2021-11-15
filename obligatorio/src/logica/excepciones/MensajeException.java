package logica.excepciones;

public class MensajeException extends Exception {

	private static final long serialVersionUID = 2868220191674382054L;
	private String mensaje;

	public MensajeException(String mensaje) {
		this.mensaje = mensaje;
	}

	public String darMensaje() {
		return mensaje;
	}
}