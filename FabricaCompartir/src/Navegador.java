import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Navegador {
	
	public static ArrayList<JFrame> arr = new ArrayList<JFrame>(5);
	
	public static void window(String name, boolean accion) {
		JFrame jf = null;
		int i=0;
		while(i<arr.size() && jf == null) {
			if(arr.get(i).getClass().getName().equals(name)) {
				jf = arr.get(i);
			}
			i++;
		}
		
		if(jf == null) {
			JOptionPane.showMessageDialog(null, "La ventana " + name + " no se ha encontrado");
		}else {
			jf.setVisible(accion);
		}
	}

}
