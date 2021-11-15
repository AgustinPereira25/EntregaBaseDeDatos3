package logica.excepciones;

public class PersistenciaException extends MensajeException {
	private static final long serialVersionUID = -5925067631784660391L;

	public PersistenciaException(String mensaje) {
		super(mensaje);
	}
}
