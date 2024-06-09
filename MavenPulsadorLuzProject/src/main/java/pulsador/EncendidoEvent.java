package pulsador;

import java.util.EventObject;

public class EncendidoEvent extends EventObject{

	private static final long serialVersionUID = 1L; //default serial version ID
	protected boolean oldEncendido, newEncendido;	
	
	public EncendidoEvent(Object fuente, boolean oldEncendido, boolean newEncendido) {
		super(fuente);
		this.oldEncendido = oldEncendido;
		this.newEncendido = newEncendido;
	}
	public boolean getNewEncendido() { return newEncendido; }
	public boolean getOldEncendido() { return oldEncendido; }
}
