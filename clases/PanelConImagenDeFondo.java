package clases;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;

public class PanelConImagenDeFondo extends javax.swing.JPanel {
	
	public PanelConImagenDeFondo(){
		this.setSize(600,462);
	}
	
	
	/**
	 *METODO PARA PONER LA IMAGEN DE FONDO
	 */
	public void paintComponent (Graphics g){
		Dimension tamanio = getSize();
		ImageIcon imagenFondo = new ImageIcon(getClass().getResource("imagenes\\fondoDePrueba.jpg"));
		g.drawImage(imagenFondo.getImage(),0,0,tamanio.width, tamanio.height, null);
		setOpaque(false);
		super.paintComponent(g);
	}

}
