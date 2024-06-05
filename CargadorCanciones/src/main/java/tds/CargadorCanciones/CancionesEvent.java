package tds.CargadorCanciones;

import java.util.EventObject;


@SuppressWarnings("serial")
public class CancionesEvent extends EventObject {
	protected Canciones cancionesCargadas;

	public CancionesEvent(Object fuente,  Canciones cancionesCargadas) {
		super(fuente);
		
		this.cancionesCargadas = cancionesCargadas;
	}

	public Canciones getCancionesNuevas() {

		return cancionesCargadas;

	}
}
