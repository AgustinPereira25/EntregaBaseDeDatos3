package grafica.ventanas;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImagenFondo extends JPanel {

	private static final long serialVersionUID = -3798052180884846212L;
	private ImageIcon imagen;

	/* Creo el panel y cargo la imagen a colocar en el */
	public ImagenFondo() {
		imagen = new ImageIcon("images/fondo.jpg");
		this.setLayout(null);
	}

	public ImagenFondo(String s) {
		imagen = new ImageIcon(s);
		this.setLayout(null);
	}

	/*
	 * Dibujo la imagen dentro del panel, este metodo es invocado automaticamente
	 * por java cuando va a dibujar la ventana principal que contiene a este panel
	 */
	public void paint(Graphics g) {
		if (imagen != null) {
			g.drawImage(imagen.getImage(), 0, 0, super.getWidth(), super.getHeight(), this);
			super.setOpaque(false);
			super.paint(g);
		}
	}
}
