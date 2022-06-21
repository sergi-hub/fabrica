import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class VentanaModificar2 extends JFrame {

	private JPanel contentPane;
	private JTextField textMat;
	private JTextField textKm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaModificar2 frame = new VentanaModificar2(0);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaModificar2(int posi) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 436, 263);
		contentPane.add(panel);
		panel.setLayout(null);
		
		textMat = new JTextField(Coche.arr_coches.get(posi).getMatricula());
		textMat.setBounds(50, 10, 96, 19);
		panel.add(textMat);
		textMat.setColumns(10);
		
		JComboBox comboMarca = new JComboBox(Coche.marcas);
		comboMarca.setSelectedItem(Coche.arr_coches.get(posi).getMarca());
		comboMarca.setBounds(50, 54, 96, 21);
		panel.add(comboMarca);
		
		JComboBox comboModelo = new JComboBox(Coche.modelos);
		comboModelo.setSelectedItem(Coche.arr_coches.get(posi).getModelo());
		comboModelo.setBounds(50, 98, 96, 21);
		panel.add(comboModelo);
		
		JComboBox comboColor = new JComboBox(Coche.colores);
		comboModelo.setSelectedItem(Coche.arr_coches.get(posi).getColor());
		comboColor.setBounds(50, 145, 96, 21);
		panel.add(comboColor);
		
		textKm = new JTextField(Coche.arr_coches.get(posi).getKm() + "");
		textKm.setColumns(10);
		textKm.setBounds(50, 191, 96, 19);
		panel.add(textKm);
		
		JButton botonModificar = new JButton("Modificar");
		botonModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Coche c = new Coche(textMat.getText(), (String) comboMarca.getSelectedItem(), (String) comboModelo.getSelectedItem(), (String) comboColor.getSelectedItem(), Double.parseDouble(textKm.getText()));
				Coche.arr_coches.set(posi, c);
				
				File archivo = new File("parque.txt");
				try {
					FileWriter escribir = new FileWriter(archivo);
					BufferedWriter buffer_escribir = new BufferedWriter(escribir);
					
					for(int i=0; i<Coche.arr_coches.size(); i++) {
						buffer_escribir.write(Coche.arr_coches.get(i).datos());
					}
					buffer_escribir.flush();
					buffer_escribir.close();
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				VentanaMostrar frame = new VentanaMostrar();
				frame.setVisible(true);
				dispose();
				
			}
		});
		botonModificar.setBounds(284, 190, 85, 21);
		panel.add(botonModificar);
	}
}
