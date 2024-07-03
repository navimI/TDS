package pulsador;

import java.util.EventListener;
import java.util.EventObject;

/**
 * Interfaz que define el método que debe implementar un objeto que quiera ser informado de cambios en el estado de encendido de la luz.
 * @version 1.0
 * @author Iván García Alcaraz
 */
public interface IEncendidoListener extends EventListener{
	public void enteradoCambioEncendido(EventObject e);
}
