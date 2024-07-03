package tds.CargadorCanciones;

public class Programa {

	public static void main(String[] args) {

		// Ruta del archivo en la carpeta src/main/resources/xml
        String rutaArchivoResource = "xml/canciones.xml";
		
		Canciones canciones = null;
		
		canciones = MapperCancionesXMLtoJava
					.cargarCanciones(rutaArchivoResource);
		 //Obtener fichero a cargar mediante JFileChooser en Swing
	
		
		for(Cancion cancion: canciones.getCancion()){
				System.out.println("Titulo: " + cancion.getTitulo());
				System.out.println(" Interprete : " + cancion.getInterprete());
				System.out.println(" Estilo : " + cancion.getEstilo());
				System.out.println(" URL : " + cancion.getURL());
				System.out.println("***** ***** *****");    
		}
	
	}

}
