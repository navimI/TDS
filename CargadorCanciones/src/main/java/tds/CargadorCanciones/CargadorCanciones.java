package tds.CargadorCanciones;





import static java.util.stream.Collectors.*;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Vector;

public class CargadorCanciones implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	// Definimos la propiedad ligada
	private Canciones archivoCanciones;
	
	// Implementa la lista de fuente de eventos para la gestion de listeners. 
	private Vector<CancionesListener> listeners; 

	
	// Constructor
	
	public CargadorCanciones() {
		archivoCanciones = new Canciones();
		listeners = new Vector<>(); 
	}

	
	// Getters and setters
	
	public void addListener(CancionesListener listener) {
		this.listeners.add(listener);
	}
	
	public void removeListener(CancionesListener listener) {
		this.listeners.removeElement(listener);
	}
	
	
	public Canciones getArchivoCanciones() {
		return archivoCanciones;
	}

	// Método que necesita notificar los 
	
	public void setArchivoCanciones(String fichero) {
		
		// Llama al método cargarCanciones del Mapper transformado de fichero XML a Canciones.
		
		archivoCanciones = MapperCancionesXMLtoJava.cargarCanciones(fichero);
		
		// Se crea un evento para notificar a los clientes.
		
		CancionesEvent e = new CancionesEvent(this, archivoCanciones);
		
		//Se notifica a los oyentes del nuevo evento ocurrido.
		
		notifyListenner(e);
		
		
	}
	
	// Método para notificar a los oyentes de los cambios en el cargador
	
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
