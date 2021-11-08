package tiendaDeRopa;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import java.awt.Label;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollBar;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.List;
import java.awt.ScrollPane;
import java.awt.Point;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.Box;

public class VentanaUsuario extends JFrame {

	private JPanel contentPane;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaUsuario frame = new VentanaUsuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VentanaUsuario() {
		setTitle("SWEET WEAR");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(345, 700, 345, 700);

		setBounds(100, 100, 582, 409);
		

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"TODO", "CAMISETAS", "PANTALONES", "SUDADERAS"}));
		comboBox.setToolTipText("AYUDA");
		comboBox.setBounds(63, 92, 169, 22);
		contentPane.add(comboBox);
		
		Label label = new Label("TIPO");
		label.setAlignment(Label.CENTER);
		label.setBounds(10, 92, 47, 22);
		contentPane.add(label);
		
		Label label_1 = new Label("PRECIO");
		label_1.setBounds(306, 92, 47, 22);
		contentPane.add(label_1);
		
		Label label_2 = new Label("CUENTA");
		label_2.setAlignment(Label.CENTER);
		label_2.setBounds(291, 31, 62, 22);
		contentPane.add(label_2);
		
		Label label_3 = new Label("FAVORITOS");
		label_3.setAlignment(Label.CENTER);
		label_3.setBounds(375, 31, 62, 22);
		contentPane.add(label_3);
		
		Label label_4 = new Label("CARRITO");
		label_4.setAlignment(Label.CENTER);
		label_4.setBounds(462, 31, 62, 22);
		contentPane.add(label_4);
		
		JSlider slider = new JSlider();
		slider.setMinorTickSpacing(50);
		slider.setValue(0);
		slider.setBounds(356, 88, 200, 26);
		contentPane.add(slider);
		
		JLabel lblNewLabel = new JLabel("0");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(338, 120, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("100");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(520, 113, 46, 14);
		contentPane.add(lblNewLabel_1);
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
