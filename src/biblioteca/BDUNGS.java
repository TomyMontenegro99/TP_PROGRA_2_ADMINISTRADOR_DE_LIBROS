package biblioteca;

import java.util.*;
import java.util.Map.Entry;

public class BDUNGS {

	Integer cantEstantes = 0;
	float anchoEstantes;
	
	HashMap<Integer,Estante> Orden_Estante;

	public BDUNGS(Integer estantes, Integer anchoEstantes) {
		if (estantes > 0 && anchoEstantes >0) {

			this.cantEstantes = estantes;
			this.anchoEstantes = anchoEstantes;
			Orden_Estante = new HashMap<Integer,Estante>();
			agregarEstantes();
			
		} else 
			throw new RuntimeException("La cantidad de estantes debe ser mayor a 0");
	}

	private void agregarEstantes() {
		Integer ndeorden = 0;

		while (ndeorden < cantEstantes) {

			Estante nuevoEstante = new Estante(ndeorden, null, anchoEstantes);
			Orden_Estante.put(ndeorden, nuevoEstante);
			ndeorden++;

		}
	}
	
	public void ingresarLibro(String isbn, String cate, String titulo, float ancho) { // registrar un nuevo libro.
		
		for (Estante e : Orden_Estante.values()) {
			if (e.categoria == (cate)) {

				if (e.cantidadEspacioLibre() >= ancho) {
					Libro aux = new Libro(isbn, cate, titulo, ancho);
					e.agregarLibro(aux);

					return;
				}
			}
		}
		for (Estante e : Orden_Estante.values()) {

			if (e.categoria == null && e.cantidadEspacioLibre() >= ancho) {

				e.categoria = cate;
				Libro aux = new Libro(isbn, cate, titulo, ancho);
				e.agregarLibro(aux);
				return;
			}
		}

		Libro aux = new Libro(isbn, cate, titulo, ancho);
		Estante estanteNuevo = new Estante(Orden_Estante.values().size() + 1, aux.categoria, anchoEstantes);
		estanteNuevo.agregarLibro(aux);
		Orden_Estante.put(Orden_Estante.values().size() + 1, estanteNuevo);

	}


	public void rotularEstante(String nuevaCategoria, Integer numOrden) {
		
		int numOrdenUltimo = Orden_Estante.size()-1;
		
		if (numOrden > numOrdenUltimo || numOrden <0)
			throw new RuntimeException("PRUEBE CON UN Nº DE ORDEN IGUAL O MÁS CHICO QUE ***" + numOrdenUltimo + "***, Y >0");
		
		if (Orden_Estante.get(numOrden).espacioUsadoDelEstante >0)
			throw new RuntimeException("El estante ingresado ya tiene una categoria y tiene libros ");
		else {
			Orden_Estante.get(numOrden).categoria=nuevaCategoria;
		}
	}


	public HashMap<String, Integer> verLibrosCategoria(String cat) {//para este caso, consideramos libro igual, con solo compartir isbn
		HashMap<String, Integer> nuevo = new HashMap<String, Integer>();
		
		for (Estante e : Orden_Estante.values()) {
			if (e.categoria == cat) {
				for (Entry<Libro, Integer> libro :e.librosYcant.entrySet()) {
					if (!(nuevo.containsKey(libro.getKey().isbn)))
						nuevo.put(libro.getKey().isbn, libro.getValue());
					
					else {
						int cantLib=nuevo.get(libro.getKey().isbn);
						nuevo.put(libro.getKey().isbn, cantLib+libro.getValue());//cant, que estaban en nuevo + cant del estante
					}
				}
			}
		}
		
		if (nuevo.size()== 0) 
			throw new RuntimeException ("La categoria " + cat + " no existe en la biblioteca");
	
		return nuevo;
	}
	

	public float espacioLibre(int ndorden) {
		
		float espacioDisponible=0;
		
		if (Orden_Estante.get(ndorden).categoria!=null) {
			if (Orden_Estante.get(ndorden).espacioUsadoDelEstante <=anchoEstantes) {
				espacioDisponible= Orden_Estante.get(ndorden).cantidadEspacioLibre();
				
				if (espacioDisponible>0)
					return espacioDisponible;
				throw new RuntimeException("No hay espacio disponible");
				
			}
			throw new RuntimeException("El espacio usado de el estante" + " " + "es mayor a el ancho del estante");
		}
		else
			throw new RuntimeException("El estante ingresado no tiene categoria");
	}
	
	
	
	public void eliminarLibro(String isbn) {

		Iterator<HashMap.Entry<Integer, Estante>> estantes = Orden_Estante.entrySet().iterator();
		
		while (estantes.hasNext()) {
		    HashMap.Entry<Integer, Estante> est = estantes.next();
		    Iterator <HashMap.Entry<Libro,Integer>> libros =  est.getValue().librosYcant.entrySet().iterator();
		    
		   while (libros.hasNext()) {
			   HashMap.Entry<Libro, Integer> lib = libros.next();
			   if (lib.getKey().isbn.equals(isbn)) {
				   est.getValue().espacioUsadoDelEstante-=lib.getKey().ancho;

				   if (est.getValue().espacioUsadoDelEstante<0)
					   est.getValue().espacioUsadoDelEstante=0;
				   
				   libros.remove();
				}
			}
		}
	}

	public void ordenarPorAnchoMenor (ArrayList<Libro> PorCategoria) {
		int auxES;
		String auxISBN;
		String auxT;
		
		for (int i = 0; i < PorCategoria.size() - 1; i++) {// ordena nº de orden y espacio

			for (int j = 0; j < PorCategoria.size() - i - 1; j++) {
				if (PorCategoria.get(j + 1).ancho < PorCategoria.get(j).ancho) {
					auxES = (int) PorCategoria.get(j + 1).ancho;
					auxISBN = PorCategoria.get(j + 1).isbn;
					auxT = PorCategoria.get(j + 1).titulo;

					PorCategoria.get(j + 1).ancho = PorCategoria.get(j).ancho;
					PorCategoria.get(j + 1).isbn = PorCategoria.get(j).isbn;
					PorCategoria.get(j + 1).titulo = PorCategoria.get(j).titulo;

					PorCategoria.get(j).ancho = auxES;
					PorCategoria.get(j).isbn = auxISBN;
					PorCategoria.get(j).titulo = auxT;

				}
			}
		}
	}

	public ArrayList<Libro> obtenerLibrosDeUnaCategoriaYliberarEspacio(String cate) {
		ArrayList<Libro> porCategoria = new ArrayList<Libro>();

		for (Estante e : Orden_Estante.values()) {
			if (e.categoria == cate) {
				for (Entry<Libro, Integer> l : e.librosYcant.entrySet()) {
					Libro libro = new Libro(l.getKey().isbn, l.getKey().categoria, l.getKey().titulo, l.getKey().ancho);
					int bandera = l.getValue();
					while (bandera > 0) {
						porCategoria.add(libro);
						bandera = bandera - 1;
					}
				}
				e.espacioUsadoDelEstante = 0;
			}
		}
		return porCategoria;
	}
	
	
	public int reacomodarCategoria(String cate) {
		int espacioLiberado=0;
		
		ArrayList<Libro> porCategoria = new ArrayList<Libro>(obtenerLibrosDeUnaCategoriaYliberarEspacio(cate));

		if (porCategoria.isEmpty())
			throw new RuntimeException("NO HAY ESTANTES CON ESA CATEGORIA");
		
		ordenarPorAnchoMenor(porCategoria); //ordenamos el arraylist, por los libros con menor ancho, hasta los mas grandes
		
		for (Libro l : porCategoria) { //ELIMINA EL LIB DE LA POS VIEJA
			eliminarLibro(l.isbn);
		}

		for (Libro l : porCategoria) { //LO INGRESA REACOMODADO
			ingresarLibro(l.isbn, l.categoria, l.titulo, l.ancho);
		}

		Iterator<HashMap.Entry<Integer, Estante>> estantes = Orden_Estante.entrySet().iterator(); 

		while (estantes.hasNext()) {
			HashMap.Entry<Integer, Estante> est = estantes.next();
			if (est.getValue().categoria == cate && est.getValue().cantidadEspacioLibre() == anchoEstantes)
				espacioLiberado++;	//ENCONTRÓ UN ESTANTE QUE FUE VACIADO
		}
		return espacioLiberado;
	}
	

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(Orden_Estante.values().toString());
		return "              ANCHO TOTAL DE TODOS LOS ESTANTES "+(Orden_Estante.get(0).ancho)+sb.toString();
	}

}
