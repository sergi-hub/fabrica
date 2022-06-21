import java.util.ArrayList;
//
public class Coche {
	
	private String matricula;
	private String marca;
	private String modelo;
	private String color;
	private double km;
	public static int NUM_COCHES = 0;
	public static int MAX_COCHES = 5;
	
	public static ArrayList<Coche> arr_coches = new ArrayList<Coche>(5);
	
	public static String[] marcas = {"OPEL","CITROEN"};
	public static String[] modelos = {"CORSA","CACTUS"};
	public static String[] colores = {"blanco","rojo"};
	
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public double getKm() {
		return km;
	}
	public void setKm(double km) {
		this.km = km;
	}
	public Coche(String matricula, String marca, String modelo, String color, double km) {
		
		this.matricula = matricula;
		this.marca = marca;
		this.modelo = modelo;
		this.color = color;
		this.km = km;
		
	}
	public String datos() {
		return this.matricula + ";" + this.marca + ";" + this.modelo + ";" + this.color + ";" + this.km + "\n";
	}

	@Override
	public String toString() {
		return this.matricula + " " + 
	           this.marca + " " +
			   this.modelo + " " +
	           this.color + "\n";
	}
	

}
