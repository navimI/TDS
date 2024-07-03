package tds.CargadorCanciones;

import java.util.EventListener;

/**
 * Interfaz que define el método que se llamará cuando se produzca un cambio en las canciones.
 * 
 * @version 1.0
 * @author Ivan Garcia Alcaraz
 */
public interface CancionesListener extends EventListener {
	public void cambioNotificado(CancionesEvent e);
}
