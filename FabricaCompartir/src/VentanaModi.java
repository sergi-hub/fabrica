import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class VentanaModi extends JFrame {

	private JPanel contentPane;
	private JTextField textMatricula;
	private JTextField textKm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaModi frame = new VentanaModi("", "", "", "", "", -1);
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
	public VentanaModi(String mat, String marc, String model, String col, String km, int pos) {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 426, 248);
		contentPane.add(panel);
		panel.setLayout(null);
		
		textMatricula = new JTextField();
		textMatricula.setBounds(75, 10, 96, 19);
		panel.add(textMatricula);
		textMatricula.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Matricula");
		lblNewLabel.setBounds(10, 13, 45, 13);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Marca");
		lblNewLabel_1.setBounds(10, 50, 45, 13);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Modelo");
		lblNewLabel_2.setBounds(10, 94, 45, 13);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Color");
		lblNewLabel_3.setBounds(10, 135, 45, 13);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Km");
		lblNewLabel_4.setBounds(10, 177, 45, 13);
		panel.add(lblNewLabel_4);
		
		int posi = -1;
		int i = 0;
		while(i < Coche.marcas.length && posi == -1) {
			if(Coche.marcas[i].equals(marc)) {
				posi = i;
			}
			i++;
		}
		
		
		
		JComboBox comboMarca = new JComboBox(Coche.marcas);
		comboMarca.setBounds(75, 46, 96, 21);
		panel.add(comboMarca);
		comboMarca.setSelectedIndex(posi);
		posi = -1;
		i = 0;
		
		while(i < Coche.modelos.length && posi == -1) {
			if(Coche.modelos[i].equals(model)) {
				posi = i;
			}
			i++;
		}
		
		
		JComboBox comboModelo = new JComboBox(Coche.modelos);
		comboModelo.setBounds(75, 90, 96, 21);
		panel.add(comboModelo);
		comboModelo.setSelectedIndex(posi);
		posi = -1;
		i = 0;
		
		while(i < Coche.colores.length && posi == -1) {
			if(Coche.colores[i].equals(col)) {
				posi = i;
			}
			i++;
		}
		
		JComboBox comboColor = new JComboBox(Coche.colores);
		comboColor.setBounds(75, 131, 96, 21);
		panel.add(comboColor);
		comboColor.setSelectedIndex(posi);
		
		textKm = new JTextField();
		textKm.setColumns(10);
		textKm.setBounds(75, 174, 96, 19);
		panel.add(textKm);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Coche c = new Coche(textMatricula.getText(), (String) comboMarca.getSelectedItem(), (String) comboModelo.getSelectedItem(), (String) comboColor.getSelectedItem(), Double.parseDouble(textKm.getText()));
				Coche.arr_coches.set(pos, c);
				
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
		btnModificar.setBounds(287, 173, 85, 21);
		panel.add(btnModificar);
	}
}
