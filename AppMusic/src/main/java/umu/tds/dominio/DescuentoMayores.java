package umu.tds.dominio;

public class DescuentoMayores implements Descuento{
    @Override
    public double aplicarDescuento(int precio) {
        return precio / 1.5;
    }
}
