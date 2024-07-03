package tds.CargadorCanciones;

import java.io.Serializable;
import java.util.Vector;

/**
 * Clase que representa un cargador de canciones.
 * <p>
 * Esta clase se encarga de cargar las canciones de un fichero XML y notificar a los oyentes de los cambios.
 * 
 * @version 1.0
 * @author Ivan Garcia Alcaraz
 */
public class CargadorCanciones implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	// Definimos la propiedad ligada
	private Canciones archivoCanciones;
	
	// Implementa la lista de fuente de eventos para la gestion de listeners. 
	private Vector<CancionesListener> listeners; 

	
	// Constructor
	/**
	 * Constructor de la clase.
	 * Inicializa la lista de oyentes.
	 */
	public CargadorCanciones() {
		archivoCanciones = new Canciones();
		listeners = new Vector<>(); 
	}

	
	// Getters and setters
	/**
	 * Añade un oyente a la lista de oyentes.
	 * @param listener Oyente a añadir.
	 */
	public void addListener(CancionesListener listener) {
		this.listeners.add(listener);
	}
	
	/**
	 * Elimina un oyente de la lista de oyentes.
	 * @param listener Oyente a eliminar.
	 */
	public void removeListener(CancionesListener listener) {
		this.listeners.removeElement(listener);
	}
	
	/**
	 * Devuelve el archivo de canciones.
	 * @return Objeto Canciones.
	 */
	public Canciones getArchivoCanciones() {
		return archivoCanciones;
	}

	/**
	 * Establece el archivo de canciones.
	 * Carga el fihcero XML con las canciones y notifica a los oyentes.
	 * @param fichero Ruta del fichero XML con las canciones.
	 */
	public void setArchivoCanciones(String fichero) {
		
		// Llama al método cargarCanciones del Mapper transformado de fichero XML a Canciones.
		
		archivoCanciones = MapperCancionesXMLtoJava.cargarCanciones(fichero);
		
		// Se crea un evento para notificar a los clientes.
		
		CancionesEvent e = new CancionesEvent(this, archivoCanciones);
		
		//Se notifica a los oyentes del nuevo evento ocurrido.
		
		notifyListenner(e);
		
		
	}
	
	/**
	 * Notifica a los oyentes de un cambio en las canciones.
	 * @param e Evento a notificar.
	 */
	@SuppressWarnings("unchecked")
	public void notifyListenner(CancionesEvent e) {
		Vector<CancionesListener> lista;
		synchronized(this) {
			lista = (Vector<CancionesListener>)listeners.clone();
		}
		for (CancionesListener cl : lista) {
			cl.cambioNotificado(e);
		}
	}
	
	
}
