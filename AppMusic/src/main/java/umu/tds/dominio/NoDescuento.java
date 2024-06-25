package umu.tds.dominio;

/**
 * Descuento que no aplica ningun descuento
 * @version 1.0
 * @author Ivan Garcia Alcaraz
 * @see Descuento
 */
public class NoDescuento implements Descuento {

	@Override
	public double precioFinal() {
		return  Usuario.PRECIO_PREMIUM;
	}

	@Override
	public boolean isApplicable(Usuario usuario) {
		return true;
	}

	@Override
	public String asString() {
		return "Ningun descuento";
	}

}
