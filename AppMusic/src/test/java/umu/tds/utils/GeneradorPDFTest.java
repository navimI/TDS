package umu.tds.utils;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.itextpdf.text.DocumentException;

import umu.tds.dominio.Cancion;
import umu.tds.dominio.PlayList;

/**
 * Clase de test de la clase GeneradorPDF
 * En la clase se prueba que se genera correctamente un pdf con las playlist
 * La ruta del fichero sera el directorio de documentos del usuario de 
 * @version 1.0
 * @author Ivan Garcia Alcaraz
 * @see  umu.tds.utils.GeneradorPDF
 */

public class GeneradorPDFTest {

	Cancion c1 = new Cancion("Cosas que pasan", "Rap", "Chojin");
	Cancion c2 = new Cancion("Cosas que no pasan", "Rap", "Chojin");
	Cancion c3 = new Cancion("Cosas que deberian pasa", "Rap", "Chojin");	
	Cancion c4 = new Cancion("Energia", "Rap", "Chojin");
	Cancion c5 = new Cancion("Caravan", "Jazz", "John Wasson");
	Cancion c6 = new Cancion("Whiplash", "Jazz", "Hank Levy");
	

	List<Cancion> lcancion1 ;
	List<Cancion> lcancion2 ;
	List<Cancion> lcancion3 ;
	
	PlayList p1;
	PlayList p2;
	PlayList p3;
	
	List<PlayList> lp;
	
	GeneradorPDF generador;

	/**
	 * Inicializa el generador y las listas de canciones y playlist
	 * @see umu.tds.utils.GeneradorPDF
	 */
	
	private void inicializarGenerador() {
		
		lcancion1 = new LinkedList<Cancion>();
		lcancion1.add(c1);
		lcancion1.add(c2);
		lcancion1.add(c3);
		
		lcancion2 = new LinkedList<Cancion>();
		lcancion2.add(c5);
		lcancion2.add(c6);
		
		lcancion3 = new LinkedList<Cancion>();
		lcancion3.add(c1);
		lcancion3.add(c2);
		lcancion3.add(c3);
		lcancion3.add(c4);
		
		p1 = new PlayList("Cosas que ...",lcancion1);
		
		p2 = new PlayList("Whiplash BSO",lcancion2);
		
		p3 = new PlayList("Chojin",lcancion3);
		
		lp = new LinkedList<PlayList>();
		
		lp.add(p1);
		lp.add(p2);
		lp.add(p3);
		
		generador = new GeneradorPDF();
		
	}

	/**
	 * Test del método generarFichero de la clase GeneradorPDF
	 * Se comprueba que se genera un fichero pdf en la carpeta de documentos del usuario
	 * @see umu.tds.utils.GeneradorPDF#generarFichero(String, String, List)
	 * @throws FileNotFoundException
	 * @throws DocumentException
	 */
	
	
	@Test
	public void testGenerarFichero() throws FileNotFoundException, DocumentException {
		inicializarGenerador();
		
		String userHome = System.getProperty("user.home");
		String filePath = userHome + File.separator + "Documents" + File.separator ;

		generador.generarFichero(filePath, "Ivan", lp);

		assertTrue(new File(filePath).exists());
		
	}

}
