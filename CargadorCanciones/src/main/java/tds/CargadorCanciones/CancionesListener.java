package tds.CargadorCanciones;

import java.util.EventListener;


public interface CancionesListener extends EventListener {
	public void cambioNotificado(CancionesEvent e);
}
