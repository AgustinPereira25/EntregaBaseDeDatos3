package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuracion {

	private static Configuracion instancia;

	private String servidorIp;
	private int servidorPuerto;

	private Configuracion() throws IOException {
		Properties p = new Properties();
		String archivoConfiguracion = "config/config.properties";
		p.load(new FileInputStream(archivoConfiguracion));
		servidorIp = p.getProperty("servidorIp");
		servidorPuerto = Integer.parseInt(p.getProperty("servidorPuerto"));
	}

	public static Configuracion getInstancia() throws IOException {
		if (instancia == null)
			instancia = new Configuracion();
		return instancia;
	}

	public String getServidorIp() {
		return servidorIp;
	}

	public int getServidorPuerto() {
		return servidorPuerto;
	}
}
