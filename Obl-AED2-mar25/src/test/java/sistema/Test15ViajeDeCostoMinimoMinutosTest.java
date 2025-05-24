package sistema;

import interfaz.Retorno;
import interfaz.Sistema;
import interfaz.TipoVuelo;
import interfaz.TipoVueloPermitido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test15ViajeDeCostoMinimoMinutosTest {
    private Retorno retorno;
    private final Sistema s = new ImplementacionSistema();

    @BeforeEach
    public void setUp() {
        s.inicializarSistema(10);
    }

    @Test
    void viajeDeCostoMinimoMinutosOk(){
        retorno = s.registrarCiudad("BUE", "Buenos Aires");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = s.registrarCiudad("MVD", "Montevideo");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = s.registrarCiudad("MDR", "Madrid");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = s.registrarConexion("MVD", "BUE");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = s.registrarConexion("BUE", "MDR");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = s.registrarConexion("MVD", "MDR");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = s.registrarVuelo("MVD", "BUE", "AUX100", 100, 10, 230, TipoVuelo.PRIVADO);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = s.registrarVuelo("MVD", "BUE", "AUX105", 120, 30, 130, TipoVuelo.PRIVADO);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = s.registrarVuelo("MVD", "BUE", "AUX106", 100, 8, 340, TipoVuelo.PRIVADO);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = s.registrarVuelo("BUE", "MDR", "AUX101", 200, 180, 430, TipoVuelo.PRIVADO);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = s.registrarVuelo("MVD", "MDR", "AUX102", 300, 120, 500, TipoVuelo.PRIVADO);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = s.viajeCostoMinimoMinutos("MVD", "BUE", TipoVueloPermitido.AMBOS);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("MVD;Montevideo|BUE;Buenos Aires", retorno.getValorString());
        assertEquals(8, retorno.getValorInteger().intValue());

        retorno = s.viajeCostoMinimoMinutos("MVD", "MDR", TipoVueloPermitido.AMBOS);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("MVD;Montevideo|MDR;Madrid", retorno.getValorString());
        assertEquals(120, retorno.getValorInteger().intValue());

        s.actualizarVuelo("MVD", "BUE", "AUX100", 100, 5, 230, TipoVuelo.PRIVADO);
        s.actualizarVuelo("MVD", "MDR", "AUX102", 100, 235, 190, TipoVuelo.PRIVADO);
        retorno = s.viajeCostoMinimoMinutos("MVD", "MDR", TipoVueloPermitido.AMBOS);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("MVD;Montevideo|BUE;Buenos Aires|MDR;Madrid", retorno.getValorString());
        assertEquals(185, retorno.getValorInteger().intValue());
    }

    @Test
    void viajeDeCostoMinimoMinutosError1(){
        s.registrarCiudad("BUE", "Buenos Aires");
        s.registrarCiudad("MVD", "Montevideo");


        retorno = s.viajeCostoMinimoMinutos("", "BUE", TipoVueloPermitido.AMBOS);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.viajeCostoMinimoMinutos("MVD", "", TipoVueloPermitido.AMBOS);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.viajeCostoMinimoMinutos("MVD", null, TipoVueloPermitido.AMBOS);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.viajeCostoMinimoMinutos(null, "BUE", TipoVueloPermitido.AMBOS);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.viajeCostoMinimoMinutos(null, null, TipoVueloPermitido.AMBOS);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.viajeCostoMinimoMinutos("", "", TipoVueloPermitido.AMBOS);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.viajeCostoMinimoMinutos("MVD", "BUE", null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void viajeDeCostoMinimoMinutosError2(){
        s.registrarCiudad("MVD", "Montevideo");

        retorno = s.viajeCostoMinimoMinutos("BUE", "MVD", TipoVueloPermitido.AMBOS);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    void viajeDeCostoMinimoMinutosError3(){
        s.registrarCiudad("MVD", "Montevideo");

        retorno = s.viajeCostoMinimoMinutos("MVD", "BUE", TipoVueloPermitido.PRIVADO);
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }

    @Test
    void viajeDeCostoMinimoMinutosError4(){
        s.registrarCiudad("BUE", "Buenos Aires");
        s.registrarCiudad("MVD", "Montevideo");

        retorno = s.viajeCostoMinimoMinutos("MVD", "BUE", TipoVueloPermitido.PRIVADO);
        assertEquals(Retorno.Resultado.ERROR_4, retorno.getResultado());
    }
}
