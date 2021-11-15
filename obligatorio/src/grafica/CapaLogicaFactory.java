package grafica;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;

import config.Configuracion;
import logica.negocio.IFachada;

public class CapaLogicaFactory {

	private static boolean alive;
	private IFachada capaLogica;
	private static CapaLogicaFactory instancia;

	private CapaLogicaFactory() throws IOException, NotBoundException {
		Configuracion config = Configuracion.getInstancia();
		String ruta = "//" + config.getServidorIp() + ":" + config.getServidorPuerto() + "/capaLogica";
		capaLogica = (IFachada) Naming.lookup(ruta);
		alive = true;
	}

	public static CapaLogicaFactory getInstancia() throws IOException, NotBoundException {
		if (instancia == null) {
			instancia = new CapaLogicaFactory();
		} else {
			/*
			 * Intenta reconectarse -- Sirve para el caso en que el sistema inicia y el
			 * server está arriba, pero en un determinado momento este cae. Ante una acción
			 * del usuario, se intenta realizar la "reconexión"
			 */
			if (!alive)
				instancia = new CapaLogicaFactory();
		}
		return instancia;
	}

	public IFachada getFachada() {
		return capaLogica;
	}

	public static void notAlive() {
		alive = false;
	}
}
