package umu.tds.dominio;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

/**
 * Clase CancionTest
 * Clase de testeo de la clase Cancion del paquete umu.tds.dominio
 * La clase realiza test sobre todos los métodos de la clase Cancion
 * 
 * @version 1.0
 * @author Ivan Garcia Alcaraz
 * @see umu.tds.dominio.Cancion
 */

public class CancionTest {

	Cancion c1 = new Cancion("Cosas que pasan", "Rap", "Chojin");
	Cancion c2 = new Cancion("Cosas que no pasan", "Rap", "Chojin");
	Cancion c1dup = new Cancion("Cosas que pasan", "Rap", "Chojin");
	Cancion c3 = new Cancion("Caravan", "Jazz", "Juan Tizol","Duke Ellington");
	Cancion c4 = new Cancion("Whiplash", "Jazz", "Hank Levy");

	/**
	 * Test del método getTitulo de la clase Cancion
	 * @see umu.tds.dominio.Cancion#getTitulo()
	 */

	@Test
	public void testGetTitulo() {
		assertEquals("Test c1.getTitulo","Cosas que pasan", c1.getTitulo());
		assertEquals("Test c2.getTitulo","Cosas que no pasan", c2.getTitulo());
		assertEquals("Test c3.getTitulo","Caravan", c3.getTitulo());
		assertEquals("Test c4.getTitulo","Whiplash", c4.getTitulo());
	}

	/**
	 * Test del método containsInterprete de la clase Cancion
	 * @see umu.tds.dominio.Cancion#containsInterprete(String)
	 */

	@Test
	public void testContainsInterprete() {
		assertTrue("Test c1.containsInterprete", c1.containsInterprete("Chojin"));
		assertTrue("Test c2.containsInterprete", c2.containsInterprete("Chojin"));
		assertTrue("Test c3.containsInterprete", c3.containsInterprete("Juan Tizol"));
		assertTrue("Test c4.containsInterprete", c4.containsInterprete("Hank Levy"));
		assertFalse("Test !c1.containsInterprete", c1.containsInterprete("John Wasson"));
	}

	/**
	 * Test del método getRutaFichero de la clase Cancion
	 * @see umu.tds.dominio.Cancion#getRutaFichero()
	 */

	@Test
	public void testGetRutaFichero() {
		assertEquals("Test c1.getRutaFichero","Rap/Chojin-Cosas que pasan.mp3", c1.getRutaFichero());
		assertEquals("Test c3.getRutaFichero","Jazz/Juan Tizol&Duke Ellington-Caravan.mp3", c3.getRutaFichero());
	}

	/**
	 * Test del método setNumReproducciones getNumReproducciones de la clase Cancion
	 * @see umu.tds.dominio.Cancion#setNumReproducciones(int)
	 * @see umu.tds.dominio.Cancion#getNumReproducciones()
	 */
	
	@Test
	public void testNumReproducciones() {
		
			// Arrange
			int nReproducciones = 10;
			
			// Act
			c1.setNumReproducciones(nReproducciones);
			
			// Assert
			assertEquals("Test c1.setNumReproducciones y getNumReproducciones", nReproducciones, c1.getNumReproducciones());
		
	}

	/**
	 * Test del método addReproduccion de la clase Cancion
	 * @see umu.tds.dominio.Cancion#addReproduccion()
	 */

	@Test
	public void testAddReproduccion() {
		c3.addReproduccion();
		assertEquals("Test c3.addReproduccion", 1, c3.getNumReproducciones());
	}

	/**
	 * Test del método getListaInterpretes de la clase Cancion
	 * @see umu.tds.dominio.Cancion#getListaInterpretes()
	 */

	@Test
	public void testGetListaInterpretes() {
		assertEquals("Test c1.getListaInterpretes", Arrays.asList("Chojin"), c1.getListaInterpretes());
		assertEquals("Test c3.getListaInterpretes", Arrays.asList("Juan Tizol", "Duke Ellington"), c3.getListaInterpretes());
	}

	/**
	 * Test del método setID y getID de la clase Cancion
	 * @see umu.tds.dominio.Cancion#setID(int)
	 * @see umu.tds.dominio.Cancion#getID()
	 */

	@Test
	public void testGetID() {
			int newID = 5;
			c1.setID(newID);
			assertEquals("Test c1.setID", newID, c1.getID());
		
	}

	/**
	 * Test del método equals de la clase Cancion
	 * @see umu.tds.dominio.Cancion#cancionesIguales(Object)
	 */

	@Test
	public void testCancionesIguales() {
		assertTrue("Test c1.equals(c1dup)", c1.cancionesIguales(c1dup));
	}

	/**
	 * Test del método getEstilo de la clase Cancion
	 * @see umu.tds.dominio.Cancion#getEstilo()
	 */

	@Test
	public void testGetEstilo() {
		assertEquals("Test c1.getEstilo", "Rap", c1.getEstilo());
		assertEquals("Test c3.getEstilo", "Jazz", c3.getEstilo());
	}

	/**
	 * Test del método getStringInterpretes de la clase Cancion
	 * @see umu.tds.dominio.Cancion#getStringInterpretes()
	 */

	@Test
	public void testGetStringInterpretes() {
		assertEquals("Test c1.getStringInterpretes", "Chojin", c1.getStringInterpretes());
		assertEquals("Test c3.getStringInterpretes", "Juan Tizol, Duke Ellington", c3.getStringInterpretes());
	}

	/**
	 * Test del método toString de la clase Cancion
	 * @see umu.tds.dominio.Cancion#toString()
	 */

	@Test
	public void testToString() {
		String test ="Cancion [ID=" +c1.getID()+ ", url="+c1.getRutaFichero()+", titulo="+c1.getTitulo()+  ", numReproducciones=" + c1.getNumReproducciones()+ ", listaInterpretes="+c1.getStringInterpretes()+ "]";
		assertEquals("Test c1.toString", test, c1.toString());
	}

}
