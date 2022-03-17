package biblioteca;

public class Libro {

	String isbn;
	String titulo;
	String categoria;
	float ancho; // en cm.
	
	public Libro (String isbn, String categoria, String titulo, float anch) {
		if(anch > 0 && titulo.length()>0 && isbn.length()>0 && categoria!=null) { 
			this.isbn = isbn;
			this.titulo = titulo;
			this.categoria = categoria;
			this.ancho = anch;
		}
		else
			throw new RuntimeException ("Parametros Incorrecto"); 
	}


	@Override
	public boolean equals(Object obj) {

		if (obj==null)
			return false;
		
		if (!(obj instanceof Libro))
			return false;

		Libro aux = (Libro)obj;
		if (this.isbn.equals(aux.isbn) && this.categoria.equals(aux.categoria) && this.ancho == aux.ancho) 
			return true;
		return false;
	}
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("\n	ISBN:" + isbn );
		sb.append(", TITULO:" + titulo);
		sb.append(", ANCHO(c/u):" + ancho + " , CANTIDAD");
		
		return sb.toString();
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(ancho);
		result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		return result;
	}




}
