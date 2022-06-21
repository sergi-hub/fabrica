import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class VentanaMostrar extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton botonGuardar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaMostrar frame = new VentanaMostrar();
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
	public VentanaMostrar() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				VentanaMenu vmenu = new VentanaMenu();
				vmenu.setVisible(true);
			}
		});
		setTitle("Parque");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Acciones");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("ORDENAR");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Coche aux;
				for(int i=0; i<Coche.arr_coches.size(); i++) {
					for(int j=i; j<Coche.arr_coches.size()-1; j++) {
						if(Coche.arr_coches.get(i).getKm() > Coche.arr_coches.get(j+1).getKm()) {
							aux = Coche.arr_coches.get(i);
							Coche.arr_coches.set(i, Coche.arr_coches.get(j+1));
							Coche.arr_coches.set(j+1, aux);
						}
					}
				}
				
				((DefaultTableModel) table.getModel()).setNumRows(0);
				
				int numCols = table.getModel().getColumnCount();
				
				Object[] fila = new Object[numCols];
				for(int i=0; i<Coche.arr_coches.size(); i++) {
					fila[0] = i;
					fila[1] = Coche.arr_coches.get(i).getMatricula();
					fila[2] = Coche.arr_coches.get(i).getMarca();
					fila[3] = Coche.arr_coches.get(i).getModelo();
					fila[4] = Coche.arr_coches.get(i).getColor();
					fila[5] = Coche.arr_coches.get(i).getKm();
					
					((DefaultTableModel) table.getModel()).addRow(fila);
				}
				
				botonGuardar.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("ELIMINAR");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "No hay ninguna fila selecionada", "Mensaje", 2);
				}else {
					if(JOptionPane.showConfirmDialog(null, "Esta seguro de que quiere eliminar el coche con matricula " + Coche.arr_coches.get(table.getSelectedRow()).getMatricula()) == 0) {
						//Coche.arr_coches.remove(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString()));
						Coche.arr_coches.remove(table.getSelectedRow());
						File archivo = new File("parque.txt");
						try {
							FileWriter escribir = new FileWriter(archivo);
							BufferedWriter buffer_escribir = new BufferedWriter(escribir);
							
							for(int i=0; i<Coche.arr_coches.size(); i++) {
								buffer_escribir.write(Coche.arr_coches.get(i).datos());
							}
							buffer_escribir.flush();
							buffer_escribir.close();
							((DefaultTableModel) table.getModel()).removeRow(table.getSelectedRow());
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
				}
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("MODIFICAR");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//OPCION MAS FACIL
				if(table.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "No hay ninguna fila seleccionadoa", "Mensaje", 0);
				}else {
					VentanaModificar2 frame = new VentanaModificar2(table.getSelectedRow());
					frame.setVisible(true);
					dispose();
				}
				
				/* OPCION MAS DIFICIL
				if(table.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "No hay ninguna fila seleccionada", "Mensaje", 0);
				}else {
					
					
					int posi = table.getSelectedRow();
					String mat =  table.getValueAt(table.getSelectedRow(), 1).toString();
					String marc =  table.getValueAt(table.getSelectedRow(), 2).toString();
					String model =  table.getValueAt(table.getSelectedRow(), 3).toString();
					String col =  table.getValueAt(table.getSelectedRow(), 4).toString();
					String km =  table.getValueAt(table.getSelectedRow(), 5).toString();
					
					VentanaModi frame = new VentanaModi(mat, marc, model, col, km, posi);
					frame.setVisible(true);
				}
				
				dispose();
				
				*/
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 261);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Parque de coches");
		lblNewLabel.setBounds(115, 24, 107, 14);
		panel.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 48, 414, 203);
		panel.add(scrollPane);
		
		Coche.arr_coches.clear();
		table = new JTable();
		table.setDefaultEditor(Object.class, null);
		
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Matricula", "Marca", "Modelo", "Color", "KM"
			}
		));
		scrollPane.setViewportView(table);
		
		botonGuardar = new JButton("Guardar");
		botonGuardar.setVisible(false);
		botonGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//cuando le damos a guardar, lo que haremos es que maxacara todo lo que hay en el fichero por los datos que hay en el array, ya que abajo abrimos el archivo sin el true
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
			}
		});
		botonGuardar.setBounds(339, 21, 85, 21);
		panel.add(botonGuardar);
		//((DefaultTableModel) table.getModel()).setNumRows(0);//para que no duplice las filas que ya existe
		
		
		//todo lo de debajo sirve para cargar lo que hay en el fichero e ir añadiendo cada linea del fichero en las filas de la tabla
		File archivo = new File("parque.txt");
		try {
			FileReader leer = new FileReader(archivo);
			BufferedReader buffer_leer = new BufferedReader(leer);
			
			String linea = "";
			
			int numCols = table.getModel().getColumnCount();
			Object[] fila = new Object[numCols];
			int i = 0;
			
			while((linea = buffer_leer.readLine()) != null) {
				String[] concat = linea.split(";");
				Coche c = new Coche(concat[0], concat[1], concat[2], concat[3], Double.parseDouble(concat[4]));
				Coche.arr_coches.add(c);
				
				fila[0] = i;
				fila[1] = c.getMatricula();
				fila[2] = c.getMarca();
				fila[3] = c.getModelo();
				fila[4] = c.getColor();
				fila[5] = c.getKm();
				
				((DefaultTableModel) table.getModel()).addRow(fila);
				i++;
			}
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		
	}
}
