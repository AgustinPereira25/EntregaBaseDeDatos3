package servidor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import config.Configuracion;
import logica.excepciones.PersistenciaException;
import logica.negocio.Fachada;

public class Servidor {

	public static void main(String[] args) {

		try {
			/* Obtengo la configuración */
			Configuracion config = Configuracion.getInstancia();

			/* Armo la ruta al objeto remoto */
			String ruta = "//" + config.getServidorIp() + ":" + config.getServidorPuerto() + "/capaLogica";

			/* Creo la instancia del objeto remoto */
			Fachada capaLogica = new Fachada();

			/* Publico el objeto remoto */
			LocateRegistry.createRegistry(config.getServidorPuerto());
			Naming.rebind(ruta, capaLogica);
			System.out.println("CapaLogica publicada correctamente");
		} catch (RemoteException e) {
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (PersistenciaException e) {
			e.printStackTrace();
		}
	}
}
