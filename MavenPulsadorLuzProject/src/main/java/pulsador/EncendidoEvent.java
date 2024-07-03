package pulsador;

import java.util.EventObject;
/**
 * Clase que representa un evento de encendido de la luz.
 * @since 1.0
 * @author Iván García Alcaraz
 */
public class EncendidoEvent extends EventObject{

	private static final long serialVersionUID = 1L; //default serial version ID
	protected boolean oldEncendido, newEncendido;	
	
	/**
	 * Constructor de la clase EncendidoEvent.
	 * @param fuente Objeto que ha generado el evento.
	 * @param oldEncendido Estado anterior de la luz.
	 * @param newEncendido Estado nuevo de la luz.
	 */
	public EncendidoEvent(Object fuente, boolean oldEncendido, boolean newEncendido) {
		super(fuente);
		this.oldEncendido = oldEncendido;
		this.newEncendido = newEncendido;
	}
	/**
	 * Devuelve el estado nueveo de la luz.
	 * @return Estado nuevo de la luz.
	 */
	public boolean getNewEncendido() { return newEncendido; }
	/**
	 * Devuelve el estado anterior de la luz.
	 * @return Estado anterior de la luz.
	 */
	public boolean getOldEncendido() { return oldEncendido; }
}
