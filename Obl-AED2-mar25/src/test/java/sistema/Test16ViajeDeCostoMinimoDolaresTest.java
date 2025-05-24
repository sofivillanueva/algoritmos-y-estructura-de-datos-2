package sistema;

import interfaz.Retorno;
import interfaz.Sistema;
import interfaz.TipoVuelo;
import interfaz.TipoVueloPermitido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test16ViajeDeCostoMinimoDolaresTest {
    private Retorno retorno;
    private final Sistema s = new ImplementacionSistema();

    @BeforeEach
    public void setUp() {
        s.inicializarSistema(10);
    }

    @Test
    void viajeDeCostoMinimoDolaresOk(){
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
        retorno = s.registrarVuelo("BUE", "MDR", "AUX101", 200, 180, 210, TipoVuelo.PRIVADO);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = s.registrarVuelo("MVD", "MDR", "AUX102", 300, 120, 500, TipoVuelo.PRIVADO);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = s.viajeCostoMinimoDolares("MVD", "BUE", TipoVueloPermitido.AMBOS);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("MVD;Montevideo|BUE;Buenos Aires", retorno.getValorString());
        assertEquals(130, retorno.getValorInteger().intValue());

        retorno = s.viajeCostoMinimoDolares("MVD", "MDR", TipoVueloPermitido.AMBOS);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("MVD;Montevideo|BUE;Buenos Aires|MDR;Madrid", retorno.getValorString());
        assertEquals(340, retorno.getValorInteger().intValue());

        s.actualizarVuelo("MVD", "BUE", "AUX100", 100, 62, 430, TipoVuelo.PRIVADO);
        s.actualizarVuelo("MVD", "MDR", "AUX102", 100, 235, 190, TipoVuelo.PRIVADO);
        retorno = s.viajeCostoMinimoDolares("MVD", "MDR", TipoVueloPermitido.AMBOS);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("MVD;Montevideo|MDR;Madrid", retorno.getValorString());
        assertEquals(190, retorno.getValorInteger().intValue());

        s.registrarVuelo("MVD", "MDR", "AUX112", 300, 60, 180, TipoVuelo.PRIVADO);
        s.registrarVuelo("MVD", "MDR", "AUX110", 300, 120, 100, TipoVuelo.COMERCIAL);
        retorno = s.viajeCostoMinimoDolares("MVD", "MDR", TipoVueloPermitido.PRIVADO);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("MVD;Montevideo|MDR;Madrid", retorno.getValorString());
        assertEquals(180, retorno.getValorInteger().intValue());
    }

    @Test
    void viajeDeCostoMinimoDolaresError1(){
        s.registrarCiudad("BUE", "Buenos Aires");
        s.registrarCiudad("MVD", "Montevideo");


        retorno = s.viajeCostoMinimoDolares("", "BUE", TipoVueloPermitido.AMBOS);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.viajeCostoMinimoDolares("MVD", "", TipoVueloPermitido.AMBOS);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.viajeCostoMinimoDolares("MVD", null, TipoVueloPermitido.AMBOS);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.viajeCostoMinimoDolares(null, "BUE", TipoVueloPermitido.AMBOS);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.viajeCostoMinimoDolares(null, null, TipoVueloPermitido.AMBOS);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.viajeCostoMinimoDolares("", "", TipoVueloPermitido.AMBOS);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.viajeCostoMinimoDolares("MVD", "BUE", null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void viajeDeCostoMinimoDolaresError2(){
        s.registrarCiudad("MVD", "Montevideo");

        retorno = s.viajeCostoMinimoDolares("BUE", "MVD", TipoVueloPermitido.AMBOS);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    void viajeDeCostoMinimoDolaresError3(){
        s.registrarCiudad("MVD", "Montevideo");

        retorno = s.viajeCostoMinimoDolares("MVD", "BUE", TipoVueloPermitido.PRIVADO);
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }

    @Test
    void viajeDeCostoMinimoDolaresError4(){
        s.registrarCiudad("BUE", "Buenos Aires");
        s.registrarCiudad("MVD", "Montevideo");

        retorno = s.viajeCostoMinimoDolares("MVD", "BUE", TipoVueloPermitido.PRIVADO);
        assertEquals(Retorno.Resultado.ERROR_4, retorno.getResultado());
    }
}
