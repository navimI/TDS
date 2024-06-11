package umu.tds.dominio;

import java.time.LocalDate;

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
