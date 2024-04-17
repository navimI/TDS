package umu.tds.utils;

import java.io.FileNotFoundException;
import java.net.URL;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import umu.tds.dominio.Cancion;

public class Player {
	// canciones almacenadas en src/main/resources
	private Cancion cancionActual = null;
	private MediaPlayer mediaPlayer;
	private String carpetaCanciones = "/canciones/";
	
	public Player(){
		//existen otras formas de lanzar JavaFX desde Swing
		try {
			com.sun.javafx.application.PlatformImpl.startup(()->{});			
		} catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("Exception: " + ex.getMessage());
		}
	}
	public void play(String boton, Cancion cancion){
		switch (boton) { 
		case "play":
			try {
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
	private void setCancionActual(Cancion cancion) throws FileNotFoundException {
		if (cancionActual != cancion){
			cancionActual = cancion;
			String rutaCancion = cancion.getRutaFichero();
		    System.out.println(rutaCancion);
			URL resourceURL = getClass().getResource(carpetaCanciones + rutaCancion);

			if (resourceURL == null) {
			    throw new FileNotFoundException("Resource not found: " + rutaCancion);
			}

			Media hit = new Media(resourceURL.toExternalForm());
			mediaPlayer = new MediaPlayer(hit);

		}
		
		
	}

	//Para integrar en el proyecto
	// public void Player() throws FileNotFoundException{
    // 	try {
    // 		com.sun.javafx.application.PlatformImpl.startup(()->{});
    // 		} catch(Exception ex) {
    // 		ex.printStackTrace();
    // 		System.out.println("Exception: " + ex.getMessage());
    // 		}
    // 	//TODO poner la cancion que hay en la ruta "/cancion/It's Pizza Time!.mp3
    // 	String rutaCancion = "It's Pizza Time!.mp3";
    // 	URL resourceURL = getClass().getResource("/canciones"+"/" + rutaCancion);
    // 	if (resourceURL == null) {
	// 	    throw new FileNotFoundException("Resource not found: " + rutaCancion);
	// 	}
    // 	Media hit = new Media(resourceURL.toExternalForm());
    // 	mediaPlayer = new MediaPlayer(hit);
	// }

}