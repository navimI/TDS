package utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import tds.CargadorCanciones.CancionesEvent;
import tds.CargadorCanciones.CancionesListener;
import tds.CargadorCanciones.CargadorCanciones;
import umu.tds.dominio.Cancion;
import umu.tds.utils.CancionCargadorAdapter;

/**
 * <h1>Clase CancionCargadorAdapterTest</h1>
 * Esta clase de prueba, CancionCargadorAdapterTest, se utiliza para probar la funcionalidad de la clase CancionCargadorAdapter.
 * Implementa la interfaz CancionesListener para escuchar los cambios en la lista de canciones.
 * El caso de prueba en esta clase verifica que el número de reproducciones de las canciones sea 1 después de cargarlas.
 * @autor   Ivan Garcia
 * @version 1.0
 * @since   2023-06-06
 */

public class CancionCargadorAdapterTest implements CancionesListener{
	
	List<Cancion> modifiedCanciones ;
	
	List<String> listaDescargas;

	private void cargarCanciones() {
		
		// Carga la lista
		listaDescargas = new LinkedList<String>();

        // Crea una instancia del cargador de canciones
        CargadorCanciones loader = new CargadorCanciones();


        // Añade esta clase como listener del cargador de canciones
        loader.addListener(this);

        // Establece la ruta del archivo XML que contiene las canciones

        loader.setArchivoCanciones("C:\\Users\\igalc\\git\\TDS\\AppMusic\\src\\main\\resources\\xml\\canciones.xml");
    }

    @Override
    public void cambioNotificado(CancionesEvent e) {
        // Retrieve the list of songs from the event
        e.getCancionesNuevas().getCancion().stream().forEach(c -> System.out.println(c.getTitulo()+" | "+c.getEstilo()+" | "+c.getInterprete()+" | "));
        

        // Create a new list to store the modified songs
        modifiedCanciones = new LinkedList<Cancion>();
        List<tds.CargadorCanciones.Cancion> listaCancionesCargador = e.getCancionesNuevas().getCancion();

        // Iterate over each song
        for (tds.CargadorCanciones.Cancion cancion : listaCancionesCargador) {
            // Create a new instance of the Song class
            Cancion cancionAdapter = new CancionCargadorAdapter(cancion);
            modifiedCanciones.add(cancionAdapter);
            
        }
        
        // Imprimir lista de canciones
        System.out.println(modifiedCanciones.toString());

        // Incrementar el número de reproducciones de cada canción
        modifiedCanciones.stream().forEach(c -> c.addReproduccion());

        // Imprimir lista de canciones con número de reproducciones incrementado
        System.out.println(modifiedCanciones.toString());
    
    }

    private void almacenarNombresFicheros() {
        // Obtener la ruta de los ficheros ubicados en la carpeta "canciones"
        String rutaCanciones = getClass().getResource("/canciones").getPath();

        // Obtener los ficheros ubicados en la carpeta "canciones"
        File carpetaCanciones = new File(rutaCanciones);
        File[] ficheros = carpetaCanciones.listFiles();

        // Almacenar los nombres de los ficheros en la lista
        for (File fichero : ficheros) {
            listaDescargas.add(fichero.getName());
        }
    }
    
    
	// Test 1 - Comprobar que el número de reproducciones de las canciones es 1 al cargarlas
	@Test
	public void testGetNumReproducciones() {
		cargarCanciones();
		for (Cancion cancion : modifiedCanciones) {
			assertEquals("resultado prueba1",1,cancion.getNumReproducciones());
		}
	}
	
	@Test
	public void testDescargarCancion() {
		
		cargarCanciones();

		for(Cancion cancion : modifiedCanciones) {
			((CancionCargadorAdapter) cancion).descargarCancion();
		}
		
		almacenarNombresFicheros();
		
		for (Cancion cancion : modifiedCanciones) {
            assertTrue("resultado descargar Canciones", listaDescargas.contains(cancion.getRutaFichero()));
		}
	}

}
