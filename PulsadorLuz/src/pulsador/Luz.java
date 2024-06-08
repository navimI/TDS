package pulsador;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.EventObject;
import java.util.Vector;

/**
 * La clase Luz representa una luz que puede ser encendida o apagada mediante un
 * pulsador. Hereda de Canvas y es Serializable.
 */
public class Luz extends Canvas implements Serializable {

	private static final long serialVersionUID = 1L;

	// propiedades
	private Color colorEncendido;
	private Color colorApagado;
	private boolean encendido = false;
	private String nombre;

	// atributos
	// private Vector encendidoListeners = new Vector(); //oyentes
	private Vector<IEncendidoListener> oyentes;
	private boolean bPulsado = false; // para si está presionado o no el botón

	/**
	 * Constructor de la clase Luz.
	 */
	public Luz() { // constructor
		setSize(20, 20); // tamañado inicial
		setMinimumSize(new Dimension(20, 20));
		repaint();

		// Con el ratón:
		this.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				luzPressed(e); // método de la clase
			}

			public void mouseReleased(MouseEvent e) {
				luzReleased(e); // método de la clase
			}
		});

		oyentes = new Vector<>();
	}

	// Funcionalidad básica:
	/**
	 * Obtiene el color que se muestra cuando la luz está encendida.
	 * 
	 * @return El color de la luz encendida.
	 */
	public Color getColorEncendido() {
		return colorEncendido;
	}

	/**
	 * Establece el color que se muestra cuando la luz está encendida.
	 * 
	 * @param colorEncendido El nuevo color de la luz encendida.
	 */

	public void setColorEncendido(Color colorEncendido) {
		this.colorEncendido = colorEncendido;
		repaint();
	}

	/**
	 * Obtiene el color que se muestra cuando la luz está apagada.
	 * 
	 * @return El color de la luz apagada.
	 */

	public Color getColorApagado() {
		return colorApagado;
	}

	/**
	 * Establece el color que se muestra cuando la luz está apagada.
	 * 
	 * @param colorApagado El nuevo color de la luz apagada.
	 */
	public void setColorApagado(Color colorApagado) {
		this.colorApagado = colorApagado;
		repaint();
	}

	/**
	 * Comprueba si la luz está encendida.
	 * 
	 * @return true si la luz está encendida, false si está apagada.
	 */
	public boolean isEncendido() {
		return encendido;
	}

	/**
	 * Establece el estado de la luz (encendido o apagado).
	 * 
	 * @param nuevo El nuevo estado de la luz.
	 */

	public void setEncendido(boolean nuevo) {
		boolean anterior = this.encendido;
		encendido = nuevo;

		// si cambia el estado del pulsador, notificar a oyentes
		if (anterior != nuevo) {
			EncendidoEvent o = new EncendidoEvent(this, anterior, nuevo);
			notificarOyentes(o);
		}
	}

	/**
	 * Obtiene el nombre de la luz.
	 * 
	 * @return El nombre de la luz.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre de la luz.
	 * 
	 * @param nombre El nuevo nombre de la luz.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Obtiene el tamaño preferido de la luz.
	 * 
	 * @return El tamaño preferido de la luz.
	 */
	public Dimension getPreferredSize() {
		return new Dimension(30, 30);
	}

	public Dimension getMinimumSize() {
		return getPreferredSize();
	}

	// Funcionalidad avanzada:
	/**
	 * Método que se llama cuando se presiona la luz.
	 * 
	 * @param e El evento del ratón.
	 */
	public void luzPressed(MouseEvent e) {
		bPulsado = true;
		repaint();
	}

	/**
	 * Método que se llama cuando se suelta la luz.
	 * 
	 * @param e El evento del ratón.
	 */
	public void luzReleased(MouseEvent e) {
		if (bPulsado) {
			bPulsado = false;
			setEncendido(!encendido); // TODO if (encendido){setEncendido(false);}else{seEncendido(true);}
			repaint();
		}
	}

	/**
	 * Notifica a todos los oyentes registrados sobre un cambio en el estado de la
	 * luz.
	 * 
	 * @param e El evento que contiene la información sobre el cambio de estado.
	 */
	@SuppressWarnings("unchecked") // por la mezcla de tipos
	public void notificarOyentes(EventObject e) {
		Vector<IEncendidoListener> copia;
		synchronized (this) {
			copia = (Vector<IEncendidoListener>) oyentes.clone();
		}
		// copia.stream().forEach(o -> o.enteradoCambioEncendido(e));
		copia.forEach(o -> o.enteradoCambioEncendido(e)); // TODO ¿quién define este método?
	}

	/**
	 * Añade un oyente de eventos de encendido.
	 * 
	 * @param oyente El oyente que se añade.
	 */
	public synchronized void addEncendidoListener(IEncendidoListener oyente) {
		oyentes.add(oyente);
	}

	/**
	 * Elimina un oyente de eventos de encendido.
	 * 
	 * @param oyente El oyente que se elimina.
	 */
	public synchronized void removeEncendidoListener(IEncendidoListener oyente) {
		oyentes.removeElement(oyente);
	}

	/**
	 * Pinta la luz en el componente.
	 * 
	 * @param g El contexto gráfico.
	 */
	public void paint(Graphics g) {
		int ancho = getSize().width; // tamaño de ancho
		int alto = getSize().height; // tamaño de alto

		// para que no se modifique:
		if (ancho != alto) {
			if (ancho < alto) {
				alto = ancho;
			} else {
				ancho = alto;
			}
			setSize(ancho, alto);
			invalidate();
		}

		int grosor = 3; // grosor del botón
		int anchuraBoton = ancho - grosor;
		int bordeBoton = anchuraBoton / 5;
		int anchuraLuz = anchuraBoton - 2 * bordeBoton;

		int x = 0; // desplazamiento;
		if (!bPulsado) {
			x = 0;
		} else {
			x = grosor;
		}

		if (encendido) {
			g.setColor(colorEncendido);
		} else {
			g.setColor(colorApagado);
		}

		g.fillOval(x + bordeBoton, x + bordeBoton, anchuraLuz, anchuraLuz); // Dibujar la luz
		g.setColor(Color.BLACK); // dibujar círculos negros
		g.drawOval(x, x, anchuraBoton - 1, anchuraBoton - 1);
		g.drawOval(x + bordeBoton, x + bordeBoton, anchuraLuz - 1, anchuraLuz - 1);
		g.setColor(getForeground()); // restituir el color
	}

}