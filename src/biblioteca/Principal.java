package biblioteca;

public class Principal {
	public static void main(String[] args) {

		BDUNGS bd = new BDUNGS(10, 100); 
		
		bd.ingresarLibro("96898151222", "Computacion", "LIBRO MAS GRANDE", 80);
		bd.ingresarLibro("9788415552222", "Computacion", "Estructuras de datos en Java 1", 10); 
		bd.ingresarLibro("9788415552222", "Computacion", "Estructuras de datos en Java 1", 10);
		bd.ingresarLibro("9788415552223", "Computacion", "Estructuras de datos en Java 2", 20);

		bd.ingresarLibro("5123517253", "Computacion", "How To hack WPA Wifi", 30);
		bd.ingresarLibro("5123517253", "Computacion", "How To hack WPA Wifi", 30);
		bd.ingresarLibro("5123517253", "Computacion", "How To hack WPA Wifi", 30);
		bd.ingresarLibro("5123517253", "Computacion", "How To hack WPA Wifi", 30);
		
		bd.ingresarLibro("243435", "Computacion", "Programming in java", 50);
		
		bd.ingresarLibro("1289557783457", "Literatura", "El principito", 4);
		
		bd.ingresarLibro("9389557783457", "Matematica", "Introducción al Álgebra Lineal", 3);
		
		bd.ingresarLibro("3389557783457", "Sociales", "Historia argentina", 49);
		 
		bd.eliminarLibro("3389557783457");
		
		bd.reacomodarCategoria("Computacion");
		
		
		System.out.println(bd);
		System.out.println("------------------------------");
		System.out.println(bd.verLibrosCategoria("Computacion"));
		

		}
		
}






