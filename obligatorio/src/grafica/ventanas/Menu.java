package grafica.ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;

import grafica.controladores.MenuControlador;
import logica.valueObjects.VO_Juguete;
import logica.valueObjects.VO_Nino;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;

public class Menu {
	private MenuControlador controlador;
	private JFrame frame;
	private JTextField IngNino_txtNombre;
	private JTextField IngNino_txtApellido;
	private JTextField IngNino_txtCedula;
	private JTextField IngNino_txt_Result;
	private JTextField IngJuguete_txtDescrip;
	private JTextField IngJuguete_txtCedula;
	private JTable tablaI, tablaII;
	private DefaultTableModel datosI, datosII;
	private JTextField IngJug_txt_Result;
	private JTextField ListJuguetes_txtCed;
	private JTextField ListJuguetes_txtTotal;
	private JTextField ListNinos_txtTotal;
	private JTextField QueryJug_txtNumJuguete;
	private JTextField QueryJug_txtCedula;
	private JTextField QueryJuguete_txt_Result;
	private JTextField Borrar_txtCedula;
	private JTextField BorrarNino_txtResultado;
	private JTextField JugDeNino_txt_Result;
	private JTextField ListadoNinos_txt_Result;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu window = new Menu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Menu() {
		controlador = new MenuControlador(this);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setForeground(new Color(153, 180, 209));
		frame.setBounds(100, 100, 826, 417);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 790, 356);
		frame.getContentPane().add(tabbedPane);

		JPanel panel = new JPanel();
		// PRIMER PESTAÑA
		tabbedPane.addTab("Inicio", null, panel, null);
		panel.setLayout(null);

		JLabel lblRegistroDeAsignacion = new JLabel("ASIGNACION DE JUGUETES");
		lblRegistroDeAsignacion.setForeground(new Color(0, 0, 0));
		lblRegistroDeAsignacion.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblRegistroDeAsignacion.setBounds(47, 81, 292, 75);
		panel.add(lblRegistroDeAsignacion);

		JLabel lblBienvenidosAlSistema = new JLabel("BIENVENIDOS AL SISTEMA DE:");
		lblBienvenidosAlSistema.setForeground(new Color(0, 0, 0));
		lblBienvenidosAlSistema.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblBienvenidosAlSistema.setBounds(0, 11, 339, 75);
		panel.add(lblBienvenidosAlSistema);
		JLabel homeFondo = new JLabel("home");
		ImageIcon myImage = new ImageIcon("images/fondo.jpg");
		homeFondo.setBounds(0, 0, 785, 328);
		homeFondo.setIcon(myImage);
		panel.add(homeFondo);

		// SEGUNDA PESTAÑA
		JPanel Form_Ing_Nino = new JPanel();
		tabbedPane.addTab("IngresoNino", null, Form_Ing_Nino, null);
		Form_Ing_Nino.setLayout(null);

		IngNino_txtNombre = new JTextField();
		IngNino_txtNombre.setBounds(77, 97, 195, 20);
		IngNino_txtNombre.setColumns(10);
		Form_Ing_Nino.add(IngNino_txtNombre);

		IngNino_txtApellido = new JTextField();
		IngNino_txtApellido.setBounds(77, 128, 195, 20);
		IngNino_txtApellido.setColumns(10);
		Form_Ing_Nino.add(IngNino_txtApellido);

		IngNino_txtCedula = new JTextField();
		IngNino_txtCedula.setBounds(77, 66, 195, 20);
		IngNino_txtCedula.setColumns(10);
		Form_Ing_Nino.add(IngNino_txtCedula);

		JLabel lblCedula = new JLabel("CEDULA:");
		lblCedula.setBounds(10, 67, 47, 16);
		lblCedula.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		Form_Ing_Nino.add(lblCedula);

		JLabel lblIngresoDeNio_1_1 = new JLabel("NOMBRE:");
		lblIngresoDeNio_1_1.setBounds(10, 98, 52, 16);
		lblIngresoDeNio_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		Form_Ing_Nino.add(lblIngresoDeNio_1_1);

		JLabel lblIngresoDeNio_1_2 = new JLabel("APELLIDO:");
		lblIngresoDeNio_1_2.setBounds(10, 129, 56, 16);
		lblIngresoDeNio_1_2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		Form_Ing_Nino.add(lblIngresoDeNio_1_2);

		JButton btnIngresarNino_Ingresar = new JButton("Ingresar");
		btnIngresarNino_Ingresar.setToolTipText("ingresa el nino a la base..");
		btnIngresarNino_Ingresar.setBounds(10, 175, 113, 25);
		btnIngresarNino_Ingresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String aux = controlador.validarIngNino();
				resultMsgNino("");
				if (aux.isEmpty())
					controlador.registrarNino();
				else
					resultMsgNino(aux);
			}
		});
		btnIngresarNino_Ingresar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		Form_Ing_Nino.add(btnIngresarNino_Ingresar);

		JButton btnIngresarNino_Limpiar = new JButton("Limpiar");
		btnIngresarNino_Limpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarIngresoNino();
			}
		});
		btnIngresarNino_Limpiar.setToolTipText("limpia el formulario..");
		btnIngresarNino_Limpiar.setBounds(159, 175, 113, 25);
		btnIngresarNino_Limpiar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		Form_Ing_Nino.add(btnIngresarNino_Limpiar);

		JLabel lblIngresoDeNio = new JLabel("INGRESO DE NI\u00D1O:");
		lblIngresoDeNio.setBounds(10, 11, 150, 24);
		Form_Ing_Nino.add(lblIngresoDeNio);
		lblIngresoDeNio.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngresoDeNio.setForeground(Color.RED);
		lblIngresoDeNio.setFont(new Font("Segoe UI", Font.PLAIN, 17));

		IngNino_txt_Result = new JTextField();
		IngNino_txt_Result.setEnabled(false);
		IngNino_txt_Result.setFont(new Font("Tahoma", Font.BOLD, 16));
		IngNino_txt_Result.setBounds(302, 64, 473, 86);
		Form_Ing_Nino.add(IngNino_txt_Result);
		IngNino_txt_Result.setColumns(10);

		JLabel lblResultado = new JLabel("RESULTADO:");
		lblResultado.setBounds(302, 48, 77, 16);
		lblResultado.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		Form_Ing_Nino.add(lblResultado);

		// TERCER PESTAÑA
		JPanel Form_Ing_Juguete = new JPanel();
		tabbedPane.addTab("IngresarJuguete", null, Form_Ing_Juguete, null);
		Form_Ing_Juguete.setLayout(null);

		IngJuguete_txtDescrip = new JTextField();
		IngJuguete_txtDescrip.setBounds(106, 106, 138, 20);
		IngJuguete_txtDescrip.setColumns(10);
		Form_Ing_Juguete.add(IngJuguete_txtDescrip);

		IngJuguete_txtCedula = new JTextField();
		IngJuguete_txtCedula.setBounds(106, 70, 138, 20);
		IngJuguete_txtCedula.setColumns(10);
		Form_Ing_Juguete.add(IngJuguete_txtCedula);

		JLabel lblDescNino = new JLabel("DESCRIPCION");
		lblDescNino.setBounds(16, 107, 80, 16);
		lblDescNino.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		Form_Ing_Juguete.add(lblDescNino);

		JLabel lblCedNino = new JLabel("CED. NINO:");
		lblCedNino.setBounds(16, 71, 86, 16);
		lblCedNino.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		Form_Ing_Juguete.add(lblCedNino);

		JButton btnIngresarJuguete_Ingresar = new JButton("Ingresar");
		btnIngresarJuguete_Ingresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String aux = controlador.validarIngJugueteNino();
				resultMsgJuguete("");
				if (aux.isEmpty())
					controlador.registrarJuguete();
				else
					resultMsgJuguete(aux);
			}
		});
		btnIngresarJuguete_Ingresar.setBounds(16, 152, 87, 25);
		btnIngresarJuguete_Ingresar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		Form_Ing_Juguete.add(btnIngresarJuguete_Ingresar);

		JButton btnIngresarJuguete_Limpiar = new JButton("Limpiar");
		btnIngresarJuguete_Limpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpiarIngresoJuguete();
			}
		});
		btnIngresarJuguete_Limpiar.setBounds(151, 152, 93, 25);
		btnIngresarJuguete_Limpiar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		Form_Ing_Juguete.add(btnIngresarJuguete_Limpiar);

		IngJug_txt_Result = new JTextField();
		IngJug_txt_Result.setFont(new Font("Tahoma", Font.BOLD, 16));
		IngJug_txt_Result.setBounds(291, 70, 484, 86);
		IngJug_txt_Result.setEnabled(false);
		IngJug_txt_Result.setColumns(10);
		Form_Ing_Juguete.add(IngJug_txt_Result);

		JLabel lblResultado_1 = new JLabel("RESULTADO:");
		lblResultado_1.setBounds(291, 43, 67, 16);
		lblResultado_1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		Form_Ing_Juguete.add(lblResultado_1);

		// CUARTA PESTAÑA
		JPanel List_Ninos = new JPanel();
		tabbedPane.addTab("ListarNinos", null, List_Ninos, null);
		List_Ninos.setLayout(null);

		JScrollPane scrollPaneI = new JScrollPane();
		scrollPaneI.setBounds(10, 43, 765, 190);
		List_Ninos.add(scrollPaneI);

		datosI = new DefaultTableModel() {
			private static final long serialVersionUID = -3064897933767797716L;

			@Override
			public boolean isCellEditable(int fila, int columna) {
				return false;
			}
		};

		tablaI = new JTable(datosI);
		tablaI.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaI.setColumnSelectionAllowed(false);
		tablaI.getTableHeader().setReorderingAllowed(false);
		datosI.addColumn("Cedula");
		datosI.addColumn("Nombre");
		datosI.addColumn("Apellido");
		scrollPaneI.setViewportView(tablaI);

		JButton btnListNinos_Consultar = new JButton("Consultar");

		btnListNinos_Consultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpiarListarNinos();
				controlador.listarNinos();
			}
		});

		btnListNinos_Consultar.setToolTipText("Actualizar");
		btnListNinos_Consultar.setBounds(10, 11, 94, 24);
		List_Ninos.add(btnListNinos_Consultar);

		ListNinos_txtTotal = new JTextField();
		ListNinos_txtTotal.setHorizontalAlignment(SwingConstants.CENTER);
		ListNinos_txtTotal.setEnabled(false);
		ListNinos_txtTotal.setColumns(10);
		ListNinos_txtTotal.setBounds(689, 290, 86, 20);
		List_Ninos.add(ListNinos_txtTotal);

		JLabel lblTotalJuguetes_1 = new JLabel("TOTAL");
		lblTotalJuguetes_1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblTotalJuguetes_1.setBounds(652, 291, 35, 16);
		List_Ninos.add(lblTotalJuguetes_1);

		JLabel lblDescripcion_1_1 = new JLabel("DESCRIPCION");
		lblDescripcion_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblDescripcion_1_1.setBounds(10, 244, 77, 16);
		List_Ninos.add(lblDescripcion_1_1);

		ListadoNinos_txt_Result = new JTextField();
		ListadoNinos_txt_Result.setHorizontalAlignment(SwingConstants.CENTER);
		ListadoNinos_txt_Result.setFont(new Font("Tahoma", Font.BOLD, 17));
		ListadoNinos_txt_Result.setEnabled(false);
		ListadoNinos_txt_Result.setColumns(10);
		ListadoNinos_txt_Result.setBounds(10, 271, 508, 39);
		List_Ninos.add(ListadoNinos_txt_Result);

		JButton btnListNinos_Limpiar = new JButton("Limpiar");
		btnListNinos_Limpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarListarNinos();
			}
		});
		btnListNinos_Limpiar.setToolTipText("Actualizar");
		btnListNinos_Limpiar.setBounds(681, 8, 94, 24);
		List_Ninos.add(btnListNinos_Limpiar);

		// QUINTA PESTAÑA
		JPanel List_Juguetes = new JPanel();
		tabbedPane.addTab("JuguetesNino", null, List_Juguetes, null);
		List_Juguetes.setLayout(null);

		JScrollPane scrollPaneII = new JScrollPane();
		scrollPaneII.setBounds(10, 43, 765, 190);
		List_Juguetes.add(scrollPaneII);

		datosII = new DefaultTableModel() {
			private static final long serialVersionUID = -3064897933767797716L;

			@Override
			public boolean isCellEditable(int fila, int columna) {
				return false;
			}
		};

		tablaII = new JTable(datosII);
		tablaII.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaII.setColumnSelectionAllowed(false);
		tablaII.getTableHeader().setReorderingAllowed(false);
		datosII.addColumn("Numero");
		datosII.addColumn("Descripcion");
		scrollPaneII.setViewportView(tablaII);

		JButton btnListarJugNino_Consultar = new JButton("Consultar");
		btnListarJugNino_Consultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String aux = controlador.validarJuguetesNino();
				limpiarListadoJugNino();
				if (aux.isEmpty())
					controlador.listarJuguetes();
				else
					ListadoJugNinoMsgDescripcion(aux);
			}
		});
		btnListarJugNino_Consultar.setToolTipText("obtiene de la base los juguetes del nino...");
		btnListarJugNino_Consultar.setBounds(10, 11, 94, 24);
		List_Juguetes.add(btnListarJugNino_Consultar);

		ListJuguetes_txtCed = new JTextField();
		ListJuguetes_txtCed.setColumns(10);
		ListJuguetes_txtCed.setBounds(625, 12, 150, 20);
		List_Juguetes.add(ListJuguetes_txtCed);

		JLabel lblCedula_1 = new JLabel("CEDULA:");
		lblCedula_1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblCedula_1.setBounds(568, 13, 47, 16);
		List_Juguetes.add(lblCedula_1);

		ListJuguetes_txtTotal = new JTextField();
		ListJuguetes_txtTotal.setHorizontalAlignment(SwingConstants.CENTER);
		ListJuguetes_txtTotal.setEnabled(false);
		ListJuguetes_txtTotal.setColumns(10);
		ListJuguetes_txtTotal.setBounds(689, 290, 86, 20);
		List_Juguetes.add(ListJuguetes_txtTotal);

		JLabel lblTotalJuguetes = new JLabel("TOTAL");
		lblTotalJuguetes.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblTotalJuguetes.setBounds(642, 291, 47, 16);
		List_Juguetes.add(lblTotalJuguetes);

		JugDeNino_txt_Result = new JTextField();
		JugDeNino_txt_Result.setHorizontalAlignment(SwingConstants.CENTER);
		JugDeNino_txt_Result.setFont(new Font("Tahoma", Font.BOLD, 16));
		JugDeNino_txt_Result.setEnabled(false);
		JugDeNino_txt_Result.setColumns(10);
		JugDeNino_txt_Result.setBounds(10, 271, 508, 39);
		List_Juguetes.add(JugDeNino_txt_Result);

		JLabel lblDescripcion_1 = new JLabel("DESCRIPCION");
		lblDescripcion_1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblDescripcion_1.setBounds(10, 244, 77, 16);
		List_Juguetes.add(lblDescripcion_1);

		JButton btnListarJugNino_Limpiar = new JButton("Limpiar");
		btnListarJugNino_Limpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpiarListadoJugNino();
				ListJuguetes_txtCed.setText("");
			}
		});
		btnListarJugNino_Limpiar.setToolTipText("limpia el formulario...");
		btnListarJugNino_Limpiar.setBounds(114, 11, 94, 24);
		List_Juguetes.add(btnListarJugNino_Limpiar);

		// SEXTA PESTAÑA
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("QueryJuguete", null, panel_1, null);
		panel_1.setLayout(null);

		JLabel lblNumJuguete = new JLabel("NRO. JUGUETE");
		lblNumJuguete.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNumJuguete.setBounds(10, 50, 100, 16);
		panel_1.add(lblNumJuguete);

		QueryJug_txtNumJuguete = new JTextField();
		QueryJug_txtNumJuguete.setColumns(10);
		QueryJug_txtNumJuguete.setBounds(120, 49, 125, 20);
		panel_1.add(QueryJug_txtNumJuguete);

		JLabel lblCedNino_1 = new JLabel("CED. NINO:");
		lblCedNino_1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblCedNino_1.setBounds(10, 13, 86, 16);
		panel_1.add(lblCedNino_1);

		QueryJug_txtCedula = new JTextField();
		QueryJug_txtCedula.setColumns(10);
		QueryJug_txtCedula.setBounds(120, 12, 125, 20);
		panel_1.add(QueryJug_txtCedula);

		JButton btnQueryJuguete_Consultar = new JButton("Consultar");
		btnQueryJuguete_Consultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String aux = controlador.validarQueryJugNino();
				resultMsgQueryJuguete("");
				if (aux.isEmpty())
					controlador.obtenerDescripJuguete();
				else
					resultMsgQueryJuguete(aux);
			}
		});
		btnQueryJuguete_Consultar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnQueryJuguete_Consultar.setBounds(10, 95, 104, 25);
		panel_1.add(btnQueryJuguete_Consultar);

		JLabel lblDescripcion = new JLabel("DESCRIPCION");
		lblDescripcion.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblDescripcion.setBounds(267, 12, 77, 16);
		panel_1.add(lblDescripcion);

		QueryJuguete_txt_Result = new JTextField();
		QueryJuguete_txt_Result.setHorizontalAlignment(SwingConstants.CENTER);
		QueryJuguete_txt_Result.setFont(new Font("Tahoma", Font.BOLD, 16));
		QueryJuguete_txt_Result.setEnabled(false);
		QueryJuguete_txt_Result.setColumns(10);
		QueryJuguete_txt_Result.setBounds(267, 34, 508, 86);
		panel_1.add(QueryJuguete_txt_Result);

		JButton btnQueryJuguete_Limpiar = new JButton("Limpiar");
		btnQueryJuguete_Limpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarQueryJuguete();
			}
		});
		btnQueryJuguete_Limpiar.setToolTipText("limpia el formulario...");
		btnQueryJuguete_Limpiar.setBounds(151, 97, 94, 24);
		panel_1.add(btnQueryJuguete_Limpiar);

		// SEPTIMA PESTAÑA
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("BorrarNino", null, panel_2, null);
		panel_2.setLayout(null);

		Borrar_txtCedula = new JTextField();
		Borrar_txtCedula.setColumns(10);
		Borrar_txtCedula.setBounds(69, 43, 196, 20);
		panel_2.add(Borrar_txtCedula);

		JLabel Borrar__lblCedula = new JLabel("CEDULA:");
		Borrar__lblCedula.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		Borrar__lblCedula.setBounds(12, 44, 47, 16);
		panel_2.add(Borrar__lblCedula);

		JButton btnBorrar_BorrarNino = new JButton("Borrar");
		btnBorrar_BorrarNino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String aux = controlador.validarBorrarNino();
				resultMsgBorrado("");
				if (aux.isEmpty())
					controlador.borrarNino();
				else
					resultMsgBorrado(aux);
			}
		});
		btnBorrar_BorrarNino.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnBorrar_BorrarNino.setBounds(10, 104, 104, 25);
		panel_2.add(btnBorrar_BorrarNino);

		BorrarNino_txtResultado = new JTextField();
		BorrarNino_txtResultado.setFont(new Font("Tahoma", Font.BOLD, 16));
		BorrarNino_txtResultado.setEnabled(false);
		BorrarNino_txtResultado.setColumns(10);
		BorrarNino_txtResultado.setBounds(302, 43, 473, 86);
		panel_2.add(BorrarNino_txtResultado);

		JLabel lblResultado_2 = new JLabel("RESULTADO:");
		lblResultado_2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblResultado_2.setBounds(304, 23, 77, 16);
		panel_2.add(lblResultado_2);

		JButton btnBorrar_Limpiar = new JButton("Limpiar");
		btnBorrar_Limpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarBorraNino();
			}
		});
		btnBorrar_Limpiar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnBorrar_Limpiar.setBounds(161, 106, 104, 25);
		panel_2.add(btnBorrar_Limpiar);

		frame.setVisible(true);
	}

	public static void mostrarMensaje(String message, String titleBar) {
		JOptionPane.showMessageDialog(null, message, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
	}

	public void cerrar() {

	}

	public String getCedulaIngNino() {
		return IngNino_txtCedula.getText();
	}

	public String getNombreIngNino() {
		return IngNino_txtNombre.getText();
	}

	public String getApellidoIngNino() {
		return IngNino_txtApellido.getText();
	}

	public String getCedulaIngJug() {
		return IngJuguete_txtCedula.getText();
	}

	public String getDescripIngJug() {
		return IngJuguete_txtDescrip.getText();
	}

	public String getCedulaListJuguetes() {
		return ListJuguetes_txtCed.getText();
	}

	public String getCedulaDarDescrip() {
		return QueryJug_txtCedula.getText();
	}

	public String getNumeroDarDescrip() {
		return QueryJug_txtNumJuguete.getText();
	}

	public String getCedulaBorrarNino() {
		return Borrar_txtCedula.getText();
	}

	public void resultMsgNino(String aux) {
		IngNino_txt_Result.setText(aux);
	}

	public void resultMsgJuguete(String aux) {
		IngJug_txt_Result.setText(aux);
	}

	public void resultMsgQueryJuguete(String aux) {
		QueryJuguete_txt_Result.setText(aux);
	}

	public void resultMsgBorrado(String aux) {
		BorrarNino_txtResultado.setText(aux);
	}

	public void ListadoJugNinoMsgDescripcion(String aux) {
		JugDeNino_txt_Result.setText(aux);
		if (aux != "OK")
			borrarListadoJuguetes();
	}

	public void mostrarListadoJuguetes(LinkedList<VO_Juguete> aux) {
		while (datosII.getRowCount() > 0)
			datosII.removeRow(0);
		int cant_reg = 0;
		Iterator<VO_Juguete> iter = aux.iterator();
		while (iter.hasNext()) {
			Object[] fila = new Object[2];
			VO_Juguete voJug = iter.next();
			fila[0] = voJug.getNumero();
			fila[1] = voJug.getDescripcion();
			cant_reg++;
			datosII.addRow(fila);
		}
		tablaII.updateUI();
		String valor = String.valueOf(cant_reg);
		ListJuguetes_txtTotal.setText(valor);
	}

	public void mostrarListadoNinos(LinkedList<VO_Nino> aux) {
		while (datosI.getRowCount() > 0)
			datosI.removeRow(0);
		int cant_reg = 0;
		Iterator<VO_Nino> iter = aux.iterator();
		while (iter.hasNext()) {
			Object[] fila = new Object[3];
			VO_Nino voNino = iter.next();
			fila[0] = voNino.getCedula();
			fila[1] = voNino.getNombre();
			fila[2] = voNino.getApellido();
			cant_reg++;
			datosI.addRow(fila);
		}
		tablaI.updateUI();
		ListNinos_txtTotal.setText(String.valueOf(cant_reg));
	}

	public void borrarListadoJuguetes() {
		while (datosII.getRowCount() > 0)
			datosII.removeRow(0);
		tablaII.updateUI();
		String valor = String.valueOf(0);
		ListJuguetes_txtTotal.setText(valor);
	}

	public void borrarListadoNinos() {
		while (datosI.getRowCount() > 0)
			datosI.removeRow(0);
		tablaI.updateUI();
		String valor = String.valueOf(0);
		ListNinos_txtTotal.setText(valor);
	}

	public void ListadoNinosMsgDescripcion(String aux) {
		ListadoNinos_txt_Result.setText(aux);
	}

	private void limpiarIngresoJuguete() {
		IngJuguete_txtDescrip.setText("");
		IngJuguete_txtCedula.setText("");
		IngJug_txt_Result.setText("");
	}

	private void limpiarIngresoNino() {
		IngNino_txtCedula.setText("");
		IngNino_txtNombre.setText("");
		IngNino_txtApellido.setText("");
		IngNino_txt_Result.setText("");
	}

	private void limpiarQueryJuguete() {
		QueryJug_txtNumJuguete.setText("");
		QueryJug_txtCedula.setText("");
		QueryJuguete_txt_Result.setText("");
	}

	private void limpiarListarNinos() {
		borrarListadoNinos();
		ListadoNinosMsgDescripcion("");
	}

	private void limpiarListadoJugNino() {
		borrarListadoJuguetes();
		ListadoJugNinoMsgDescripcion("");
	}

	private void limpiarBorraNino() {
		Borrar_txtCedula.setText("");
		BorrarNino_txtResultado.setText("");
	}
}
