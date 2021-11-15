package grafica.controladores;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.util.LinkedList;
import java.util.regex.Pattern;

import grafica.ventanas.Menu;
import logica.excepciones.NinoException;
import logica.excepciones.NinoNotieneJuguete;
import logica.excepciones.NinoSinJuguetesException;
import logica.excepciones.PersistenciaException;
import logica.valueObjects.VO_Juguete;
import logica.valueObjects.VO_Nino;
import grafica.CapaLogicaFactory;

public class MenuControlador {

	private Menu vJuguetes;

	public MenuControlador(Menu vJug) {
		vJuguetes = vJug;
		try {
			CapaLogicaFactory.getInstancia();
		} catch (IOException | NotBoundException e) {
			Menu.mostrarMensaje(e.getMessage(), "Error:");
			vJuguetes.cerrar();
		}
	}

	public void registrarNino() {
		try {
			int ced = Integer.parseInt(vJuguetes.getCedulaIngNino());
			String nom = vJuguetes.getNombreIngNino();
			String ap = vJuguetes.getApellidoIngNino();
			VO_Nino voNino = new VO_Nino(ced, nom, ap);
			CapaLogicaFactory.getInstancia().getFachada().nuevoNino(voNino);
			vJuguetes.resultMsgNino("El nino se ingreso correctamente.");
		} catch (NinoException e) {
			vJuguetes.resultMsgNino(e.darMensaje());
		} catch (IOException | NotBoundException e) {
			vJuguetes.resultMsgNino(e.getMessage());
			CapaLogicaFactory.notAlive();
		} catch (PersistenciaException e) {
			vJuguetes.resultMsgNino(e.darMensaje());
		} catch (NumberFormatException e) {
			vJuguetes.resultMsgNino("Valor numerico fuera de rango permitido.");
		}
	}

	public void registrarJuguete() {
		try {
			int ced = Integer.parseInt(vJuguetes.getCedulaIngJug());
			String descrip = vJuguetes.getDescripIngJug();
			CapaLogicaFactory.getInstancia().getFachada().nuevoJuguete(descrip, ced);
			vJuguetes.resultMsgJuguete("Se ingreso juguete correctamente.");
		} catch (NinoException e) {
			vJuguetes.resultMsgJuguete(e.darMensaje());
		} catch (IOException | NotBoundException e) {
			vJuguetes.resultMsgJuguete(e.getMessage());
			CapaLogicaFactory.notAlive();
		} catch (PersistenciaException e) {
			vJuguetes.resultMsgJuguete(e.darMensaje());
		} catch (NumberFormatException e) {
			vJuguetes.resultMsgJuguete("Valor numerico fuera de rango permitido.");
		}
	}

	public void listarJuguetes() {
		LinkedList<VO_Juguete> aux = null;
		try {
			int ced = Integer.parseInt(vJuguetes.getCedulaListJuguetes());
			aux = CapaLogicaFactory.getInstancia().getFachada().listarJuguetes(ced);
			if (aux.size() > 0) {
				vJuguetes.mostrarListadoJuguetes(aux);
				vJuguetes.ListadoJugNinoMsgDescripcion("OK");
			} else {
				vJuguetes.ListadoJugNinoMsgDescripcion("El nino no cuenta con juguetes.");
			}
		} catch (IOException | NotBoundException e) {
			vJuguetes.ListadoJugNinoMsgDescripcion(e.getMessage());
			CapaLogicaFactory.notAlive();
		} catch (PersistenciaException e) {
			vJuguetes.ListadoJugNinoMsgDescripcion(e.darMensaje());
		} catch (NinoSinJuguetesException e) {
			vJuguetes.ListadoJugNinoMsgDescripcion(e.darMensaje());
		} catch (NinoException e) {
			vJuguetes.ListadoJugNinoMsgDescripcion(e.darMensaje());
		} catch (NumberFormatException e) {
			vJuguetes.ListadoJugNinoMsgDescripcion("Valor numerico fuera de rango permitido.");
		}
	}

	public LinkedList<VO_Nino> listarNinos() {
		LinkedList<VO_Nino> aux = null;
		try {
			aux = CapaLogicaFactory.getInstancia().getFachada().listarNinos();
			if (aux.size() > 0) {
				vJuguetes.mostrarListadoNinos(aux);
				vJuguetes.ListadoNinosMsgDescripcion("OK");
			} else {
				vJuguetes.ListadoNinosMsgDescripcion("No hay ninos ingresados.");
			}
		} catch (IOException | NotBoundException e) {
			vJuguetes.ListadoNinosMsgDescripcion(e.getMessage());
			CapaLogicaFactory.notAlive();
		} catch (PersistenciaException e) {
			vJuguetes.ListadoNinosMsgDescripcion(e.darMensaje());
		} catch (NinoException e) {
			vJuguetes.ListadoNinosMsgDescripcion(e.darMensaje());
		}
		return aux;
	}

	public void obtenerDescripJuguete() {
		String aux = null;
		int cedN = Integer.parseInt(vJuguetes.getCedulaDarDescrip());
		int numJ = Integer.parseInt(vJuguetes.getNumeroDarDescrip());
		try {
			aux = CapaLogicaFactory.getInstancia().getFachada().darDescripcion(cedN, numJ);
			vJuguetes.resultMsgQueryJuguete(aux);
		} catch (IOException | NotBoundException e) {
			vJuguetes.resultMsgQueryJuguete(e.getMessage());
			CapaLogicaFactory.notAlive();
		} catch (NinoNotieneJuguete e) {
			vJuguetes.resultMsgQueryJuguete(e.darMensaje());
		} catch (PersistenciaException e) {
			vJuguetes.resultMsgQueryJuguete(e.darMensaje());
		} catch (NinoSinJuguetesException e) {
			vJuguetes.resultMsgQueryJuguete(e.darMensaje());
		} catch (NumberFormatException e) {
			vJuguetes.resultMsgQueryJuguete("Valor numerico fuera de rango permitido.");
		}
	}

	public void borrarNino() {
		try {
			int ced = Integer.parseInt(vJuguetes.getCedulaBorrarNino());
			CapaLogicaFactory.getInstancia().getFachada().borrarNinoJuguetes(ced);
			vJuguetes.resultMsgBorrado("El nino fue borrado con exito!");
		} catch (IOException | NotBoundException e) {
			vJuguetes.resultMsgBorrado(e.getMessage());
			CapaLogicaFactory.notAlive();
		} catch (PersistenciaException e) {
			vJuguetes.resultMsgBorrado(e.darMensaje());
		} catch (NinoException e) {
			vJuguetes.resultMsgBorrado(e.darMensaje());
		} catch (NumberFormatException e) {
			vJuguetes.resultMsgBorrado("Valor numerico fuera de rango permitido.");
		}
	}

	public String validarIngNino() {
		String ced = vJuguetes.getCedulaIngNino();
		String nom = vJuguetes.getNombreIngNino();
		String ape = vJuguetes.getApellidoIngNino();
		String texto_error = "";
		boolean error = false;
		if (ced.isEmpty() && !error) {
			texto_error = "Error: Debe ingresar una cédula!";
			error = true;
		} else {
			if (numberContainsString(ced) && !error) {
				texto_error = "La cedula es inválida, no puede contener letras";
				error = true;
			} else {
				if (ced.length() > 8) {
					texto_error = "La cedula es inválida, no puede contener mas de 8 digitos";
					error = true;
				} else {
					int cedula = Integer.parseInt(ced);
					if (cedula <= 10000000 && !error) {
						texto_error = "La cedula es inválida, debe contener 8 dígitos";
						error = true;
					}
				}
				if (nom.isEmpty() && !error) {
					texto_error = "Debe ingresar un nombre!!";
					error = true;
				} else {
					if (stringContainsNumber(nom) && !error) {
						texto_error = "El nombre no puede contener numeros!!";
						error = true;
					}
				}
				if (ape.isEmpty() && !error) {
					texto_error = "Debe ingresar un apellido!!";
					error = true;
				} else {
					if (stringContainsNumber(ape) && !error) {
						texto_error = "El apellido no puede contener numeros!!";
						error = true;
					}
				}
			}
		}
		return texto_error;
	}

	public String validarIngJugueteNino() {
		String ced = vJuguetes.getCedulaIngJug();
		String desc = vJuguetes.getDescripIngJug();

		String texto_error = "";
		boolean error = false;
		if (ced.isEmpty() && !error) {
			texto_error = "Debe ingresar una cédula!";
			error = true;
		} else {
			if (numberContainsString(ced) && !error) {
				texto_error = "La cedula es inválida, no puede contener letras";
				error = true;
			} else {
				if (ced.length() > 8) {
					texto_error = "La cedula es inválida, no puede contener mas de 8 digitos";
					error = true;
				} else {
					int cedula = Integer.parseInt(ced);
					if (cedula <= 10000000 && !error) {
						texto_error = "La cedula es inválida, debe contener 8 dígitos";
						error = true;
					}
				}
			}
			if (desc.isEmpty() && !error) {
				texto_error = "Debe ingresar un nombre para el juguete!";
				error = true;
			} else {
				if (stringContainsNumber(desc) && !error) {
					texto_error = "El nombre del juguete no debe tener numeros!";
					error = true;
				}
			}
		}
		return texto_error;
	}

	public String validarJuguetesNino() {
		String ced = vJuguetes.getCedulaListJuguetes();
		String texto_error = "";
		boolean error = false;
		if (ced.isEmpty() && !error) {
			texto_error = "Debe ingresar una cédula!";
			error = true;
		} else {
			if (numberContainsString(ced) && !error) {
				texto_error = "La cedula es inválida, no puede contener letras";
				error = true;
			} else {
				if (ced.length() > 8) {
					texto_error = "La cedula es inválida, no puede contener mas de 8 digitos";
					error = true;
				} else {
					int cedula = Integer.parseInt(ced);
					if (cedula <= 10000000 && !error) {
						texto_error = "La cedula es inválida, debe contener 8 dígitos";
						error = true;
					}
				}
			}
		}
		return texto_error;
	}

	public String validarQueryJugNino() {
		String ced = vJuguetes.getCedulaDarDescrip();
		String nro_str = vJuguetes.getNumeroDarDescrip();
		String texto_error = "";
		boolean error = false;
		if (ced.isEmpty() && !error) {
			texto_error = "Debe ingresar una cédula!";
			error = true;
		} else {
			if (numberContainsString(ced) && !error) {
				texto_error = "La cedula es inválida, no puede contener letras";
				error = true;
			} else {
				if (ced.length() > 8) {
					texto_error = "La cedula es inválida, no puede contener mas de 8 digitos";
					error = true;
				} else {
					int cedula = Integer.parseInt(ced);
					if (cedula <= 10000000 && !error) {
						texto_error = "La cedula es inválida, debe contener 8 dígitos";
						error = true;
					}
				}
			}
			if (nro_str.isEmpty() && !error) {
				texto_error = "Debe ingresar un numero de juguete a consultar!";
				error = true;
			} else {
				if (numberContainsString(nro_str) && !error) {
					texto_error = "El numero del juguete no debe tener letras!";
					error = true;
				}
			}
		}
		return texto_error;
	}

	public String validarBorrarNino() {
		String ced = vJuguetes.getCedulaBorrarNino();
		String texto_error = "";
		boolean error = false;
		if (ced.isEmpty() && !error) {
			texto_error = "Debe ingresar una cédula!";
			error = true;
		} else {
			if (numberContainsString(ced) && !error) {
				texto_error = "La cedula es inválida, no puede contener letras";
				error = true;
			} else {
				if (ced.length() > 8) {
					texto_error = "La cedula es inválida, no puede contener mas de 8 digitos";
					error = true;
				} else {
					int cedula = Integer.parseInt(ced);
					if (cedula <= 10000000 && !error) {
						texto_error = "La cedula es inválida, debe contener 8 dígitos";
						error = true;
					}
				}
			}
		}
		return texto_error;
	}

	public boolean stringContainsNumber(String s) {
		return Pattern.compile("[0-9]").matcher(s).find();
	}

	public boolean numberContainsString(String s) {
		return Pattern.compile("[a-zA-Z]").matcher(s).find();
	}
}