package tds.CargadorCanciones;

import java.util.EventListener;
import java.util.EventObject;

public interface CancionesListener extends EventListener {
	public void cambioNotificado(EventObject e);
}
