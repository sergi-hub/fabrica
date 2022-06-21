import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Point;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaMenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaMenu frame = new VentanaMenu();
					frame.setVisible(true);
					
					VentanaCrear frame2 = new VentanaCrear();
					Navegador.arr.add(frame2);

					//VentanaMostrar frame3 = new VentanaMostrar();
					//Navegador.arr.add(frame3);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaMenu() {
		setForeground(new Color(51, 102, 102));
		setBackground(new Color(102, 255, 0));
		setLocationByPlatform(true);
		setLocation(new Point(2500, 2500));
		setTitle("Men\u00FA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(204, 0, 153));
		contentPane.setLocation(new Point(500, 500));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(153, 153, 255));
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panel.setBounds(109, 39, 179, 172);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("MEN\u00DA");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(66, 7, 46, 14);
		panel.add(lblNewLabel);
		
		JButton botoCrear = new JButton("Crear Coche");
		botoCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Navegador.window("VentanaCrear", true);
				if(VentanaCrear.labelMensaje.getText().contains("fabricado")) {
					VentanaCrear.labelMensaje.setText("");
				}
				
				dispose();
				
				
			}
		});
		botoCrear.setForeground(new Color(0, 0, 0));
		botoCrear.setBackground(new Color(0, 255, 255));
		botoCrear.setBounds(43, 48, 106, 23);
		panel.add(botoCrear);
		
		JButton botoMostrar = new JButton("Mostrar Parque");
		botoMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Navegador.window("VentanaMostrar", true);
				VentanaMostrar frame = new VentanaMostrar();
				frame.setVisible(true);
				
				dispose();
			}
		});
		botoMostrar.setBackground(new Color(255, 51, 153));
		botoMostrar.setBounds(43, 94, 106, 23);
		panel.add(botoMostrar);
		
	}
}
