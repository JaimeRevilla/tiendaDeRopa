package clases;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PanelConImagenDeFondo extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private Image fondo = null;
	
	public PanelConImagenDeFondo(Dimension dim) {
		setPreferredSize(dim);
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(fondo, 0, 0, getSize().width, getSize().height, this);
	}
	
	public void setImage(String image) {
		fondo = new ImageIcon(getClass().getResource(image)).getImage();
	}
	
	public String toString() {
		return this.getName();
	}
}
