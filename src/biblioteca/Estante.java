package biblioteca;
import java.util.HashMap;

public class Estante {
	int numOrden;
	String categoria;
	float ancho;
	
	HashMap <Libro,Integer> librosYcant;
	
	float espacioUsadoDelEstante=0;
	
	public Estante(int num, String cat, float ancho){
		
		if (ancho > 0 && num >=0) {
			this.numOrden = num;
			this.categoria = cat;
			this.ancho = ancho;
			
			librosYcant = new HashMap <Libro,Integer>();
		}
		else 
			throw new RuntimeException ("Parametros del estante Incorrectos"); 
	}
	
	public float cantidadEspacioLibre () {
		return ancho-espacioUsadoDelEstante;
	}
	
	public void agregarLibro(Libro l) {
		if (l.ancho <= cantidadEspacioLibre()) {

			if (!librosYcant.containsKey(l))
				this.librosYcant.put(l, 1);

			else {
				Integer cantidad = librosYcant.get(l) + 1;
				this.librosYcant.put(l, cantidad);
			}
			espacioUsadoDelEstante = espacioUsadoDelEstante + l.ancho;
		} else
			throw new RuntimeException("USTED INGRESÓ UN LIBRO DEMASIADO GRANDE");

	}


	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		
		sb.append("\n"+"[Estante nº: " + numOrden);
		sb.append(", CATEGORIA::" + categoria);
		sb.append(", ESPACIO USADO:" + espacioUsadoDelEstante + "]");
		
		sb.append(librosYcant);
		sb.append("\n");
		
		return sb.toString();
	}


	
}
