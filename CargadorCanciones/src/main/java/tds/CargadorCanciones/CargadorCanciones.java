package tds.CargadorCanciones;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;



import static java.util.stream.Collectors.*;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CargadorCanciones implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private Canciones archivoCanciones;
	private final PropertyChangeSupport listenersArchivoCancion = new
			PropertyChangeSupport(this); 

	
	
	public void addArchivoCancionesChangeListener(PropertyChangeListener pcl) {
		 listenersArchivoCancion.addPropertyChangeListener(pcl);
		 }
	
	public void removeArchivoCancionesChangeListener(PropertyChangeListener pcl) {
		 listenersArchivoCancion.removePropertyChangeListener(pcl);
		 } 

	
	public Canciones getArchivoCanciones() {
		return archivoCanciones;
	}
	
	public void setArchivoCanciones(String fichero) {
		
		Canciones nuevoArchivoCanciones = null;
		
		nuevoArchivoCanciones = MapperCancionesXMLtoJava.cargarCanciones(fichero);
		
		Canciones antesArchivoCanciones = archivoCanciones;
		this.archivoCanciones = nuevoArchivoCanciones;
		listenersArchivoCancion.firePropertyChange("archivoCanciones",antesArchivoCanciones, nuevoArchivoCanciones);
		}
	
	
}
