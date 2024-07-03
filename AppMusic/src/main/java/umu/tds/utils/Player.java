package umu.tds.utils;

import java.io.FileNotFoundException;
import java.net.URL;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import umu.tds.dominio.Cancion;

/**
 * Clase que permite la reproducción de canciones.
 * La clase utiliza JavaFX para la reproducción de canciones.
 * La carpeta de las canciones se encuentra en src/main/resources/canciones.
 * 
 * @version 1.0
 * @see <a href="https://openjfx.io/">JavaFX</a>
 */

@SuppressWarnings("restriction")
public class Player {
	private Cancion cancionActual = null;
	private MediaPlayer mediaPlayer;
	private String carpetaCanciones = "/canciones/"; //Carpeta donde busca los ficheros de las canciones.
	
	/**
	 * Constructor de la clase.
	 * Inicializa la reproducción de canciones.
	 */
	public Player(){
		//existen otras formas de lanzar JavaFX desde Swing
		try {
			com.sun.javafx.application.PlatformImpl.startup(()->{});			
		} catch(Exception ex) {
			ex.printStackTrace();
			
		}
	}

	/**
	 * Reproduce una canción.
	 * 
	 * La canción se reproduce en función del botón pulsado.
	 * Los botones pueden ser: play, stop o pause.
	 * 
	 * @param boton   Botón de reproducción.
	 * @param cancion Canción a reproducir.
	 */
	public void play(String boton, Cancion cancion){
		switch (boton) { 
		case "play":
			try {
				// Si hay una canción en reproducción, se detiene
				if (mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
					mediaPlayer.stop();
				}
				setCancionActual(cancion);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			mediaPlayer.play();
			break;
		case "stop": 
			mediaPlayer.stop();
			break;
		case "pause": 
			mediaPlayer.pause();
			break;
	}
	}

	/**
	 * Establece la canción actual.
	 * @param cancion Canción a establecer.
	 * @throws FileNotFoundException
	 */
	private void setCancionActual(Cancion cancion) throws FileNotFoundException {
		if (cancionActual != cancion){
			cancionActual = cancion;
			String rutaCancion = cancion.getRutaFichero();
			
			
		    
			URL resourceURL = getClass().getResource(carpetaCanciones + rutaCancion);

			if (resourceURL == null) {
			    throw new FileNotFoundException("Resource not found: " + rutaCancion);
			}

			Media hit = new Media(resourceURL.toExternalForm());
			mediaPlayer = new MediaPlayer(hit);

		}
		
		
	}

	

}