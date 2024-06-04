package tds.CargadorCanciones;

import java.util.EventObject;


@SuppressWarnings("serial")
public class CancionesEvent extends EventObject {
	protected Canciones cancionesAnteriores;
	protected Canciones cancionesNuevas;

	public CancionesEvent(Object fuente, Canciones cancionesAnteriores, Canciones cancionesNuevas) {
		super(fuente);
		this.cancionesAnteriores = cancionesAnteriores;
		this.cancionesNuevas = cancionesNuevas;
	}

	public Canciones getCancionesAnteriores() {

		return cancionesAnteriores;

	}

	public Canciones getCancionesNuevas() {

		return cancionesNuevas;

	}
}
