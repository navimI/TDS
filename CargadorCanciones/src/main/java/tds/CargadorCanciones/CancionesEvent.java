package tds.CargadorCanciones;

import java.util.EventObject;

/**
 * Clase que representa un evento de carga de canciones.
 * 
 * @version 1.0
 * @author Ivan Garcia Alcaraz
 */

@SuppressWarnings("serial")
public class CancionesEvent extends EventObject {
	protected Canciones cancionesCargadas;

	/**
	 * Constructor de la clase.
	 * 
	 * @param fuente Objeto que ha generado el evento.
	 * @param cancionesCargadas Canciones cargadas.
	 */
	public CancionesEvent(Object fuente,  Canciones cancionesCargadas) {
		super(fuente);
		
		this.cancionesCargadas = cancionesCargadas;
	}

	/**
	 * Devuelve las canciones cargadas.
	 * @return
	 */

	public Canciones getCancionesNuevas() {

		return cancionesCargadas;

	}
}
