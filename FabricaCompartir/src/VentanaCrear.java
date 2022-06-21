import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class VentanaCrear extends JFrame {

	private JPanel contentPane;
	private JTextField textMat;
	public static JLabel labelMensaje;
	private JTextField textKm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaCrear frame = new VentanaCrear();
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
	public VentanaCrear() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				
				VentanaMenu vmenu = new VentanaMenu();
				vmenu.setVisible(true);
				
			}
		});
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 424, 245);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("MATRICULA");
		lblNewLabel.setBounds(35, 10, 80, 27);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("MARCA");
		lblNewLabel_1.setBounds(35, 47, 80, 30);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("MODELO");
		lblNewLabel_2.setBounds(35, 87, 80, 27);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("COLOR");
		lblNewLabel_3.setBounds(35, 119, 80, 27);
		panel.add(lblNewLabel_3);
		
		textMat = new JTextField();
		textMat.setBounds(122, 14, 101, 20);
		panel.add(textMat);
		textMat.setColumns(10);
		
		JButton botoMat = new JButton("ALEATORIA");
		botoMat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textMat.setText(matAleatoria());
			}
		});
		botoMat.setBounds(265, 37, 89, 23);
		panel.add(botoMat);
		
		JComboBox comboMarca = new JComboBox(Coche.marcas);
		comboMarca.setBounds(122, 47, 104, 22);
		panel.add(comboMarca);
		labelMensaje = new JLabel("");
		JComboBox comboModelo = new JComboBox(Coche.modelos);
		comboModelo.setBounds(119, 89, 104, 22);
		panel.add(comboModelo);
		
		JComboBox comboColor = new JComboBox(Coche.colores);
		comboColor.setBounds(117, 121, 106, 22);
		panel.add(comboColor);
		
		labelMensaje.setBounds(35, 205, 294, 29);
		panel.add(labelMensaje);
		
		JLabel lblNewLabel_4 = new JLabel("KM");
		lblNewLabel_4.setBounds(35, 168, 45, 27);
		panel.add(lblNewLabel_4);
		
		textKm = new JTextField();
		textKm.setBounds(127, 176, 96, 19);
		panel.add(textKm);
		textKm.setColumns(10);
		
		JButton botoFabrica = new JButton("FABRICAR COCHE");
		botoFabrica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Coche.NUM_COCHES < 5) {
					Coche c = new Coche(textMat.getText(), (String)comboMarca.getSelectedItem(), (String)comboModelo.getSelectedItem(), (String)comboColor.getSelectedItem(), Double.parseDouble(textKm.getText()));
					//Coche.arr_coches.add(c);
					//VentanaMostrar.textArea.append(c.toString());
					
					//lo de abajo siempre es igual para escribir en el fichero
					File archivo = new File("parque.txt");//fichero en el que se escribirá
					try {
						FileWriter escribir = new FileWriter(archivo, true);//canal en el que indico voy a escribir
						BufferedWriter buffer_escribir = new BufferedWriter(escribir);//con el buffer se escribe
						
						buffer_escribir.write(c.datos());
						buffer_escribir.flush();
						buffer_escribir.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
					labelMensaje.setForeground(Color.green);
					labelMensaje.setText("Se ha fabricado el coche " + (String)comboMarca.getSelectedItem());
					Coche.NUM_COCHES++;
				}else {
					labelMensaje.setForeground(Color.red);
					labelMensaje.setText("No se pueden fabricar más coches");
					botoFabrica.setEnabled(false);
				}
				
			}
		});
		botoFabrica.setBounds(265, 93, 128, 23);
		panel.add(botoFabrica);

	}
	
	public static String matAleatoria() {
		return "1234 DFR";
	}
}
