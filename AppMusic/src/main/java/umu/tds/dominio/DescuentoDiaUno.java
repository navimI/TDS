package umu.tds.dominio;

import java.time.LocalDate;

/**
 * Descuento para el primer dia del mes
 * Aplica un 20% de descuento a los usuarios el primer dia del mes
 * @version 1.0
 * @author Ivan Garcia Alcaraz
 * @see Descuento
 */
public class DescuentoDiaUno implements Descuento{
	@Override
	public double precioFinal() {
		return Usuario.PRECIO_PREMIUM * 0.8;
	}

	@Override
	public boolean isApplicable(Usuario usuario) {
		return LocalDate.now().getDayOfMonth() == 1;
	}
	
	@Override
	public String asString() {
		return "Descuento primer dia del mes (20 %)";
	}
	

}
