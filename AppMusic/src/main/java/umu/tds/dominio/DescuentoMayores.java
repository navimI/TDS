package umu.tds.dominio;

import java.time.LocalDate;
import java.time.Period;

public class DescuentoMayores implements Descuento{

	@Override
	public double precioFinal() {
		return Usuario.PRECIO_PREMIUM * 0.5;
	}

	@Override
	public boolean isApplicable(Usuario usuario) {
		Period edad = Period.between(usuario.getDateNacimiento(), LocalDate.now());
		return edad.getYears() > 64;
	}
	
	@Override
	public String asString() {
		return "Descuento Mayores de 65 (50 %)";
	}
	
}
