package sistema;

import interfaz.Retorno;
import interfaz.Sistema;
import interfaz.TipoVuelo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test13ActualizarVueloTest {
    private Retorno retorno;
    private final Sistema s = new ImplementacionSistema();

    @BeforeEach
    public void setUp() {
        s.inicializarSistema(10);
    }

    @Test
    void actualizarVueloOk(){
        s.registrarCiudad("MVD", "Montevideo");
        s.registrarCiudad("BUE", "Buenos Aires");
        s.registrarConexion("MVD", "BUE");
        s.registrarVuelo("MVD", "BUE", "AUX200", 120, 35, 233, TipoVuelo.PRIVADO);

        retorno = s.actualizarVuelo("MVD", "BUE", "AUX200", 130, 35, 233, TipoVuelo.PRIVADO);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = s.actualizarVuelo("MVD", "BUE", "AUX200", 130, 40, 233, TipoVuelo.PRIVADO);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = s.actualizarVuelo("MVD", "BUE", "AUX200", 130, 40, 250, TipoVuelo.PRIVADO);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = s.actualizarVuelo("MVD", "BUE", "AUX200", 130, 40, 250, TipoVuelo.COMERCIAL);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = s.actualizarVuelo("MVD", "BUE", "AUX200", 150, 50, 350, TipoVuelo.PRIVADO);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

    @Test
    void actualizarVueloError1(){
        s.registrarCiudad("MVD", "Montevideo");
        s.registrarCiudad("BUE", "Buenos Aires");
        s.registrarConexion("MVD", "BUE");

        s.registrarVuelo("MVD", "BUE", "AUX200", 0, 35, 233, TipoVuelo.PRIVADO);

        retorno = s.actualizarVuelo("MVD", "BUE", "AUX201", -13, 15, 233, TipoVuelo.PRIVADO);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.actualizarVuelo("MVD", "BUE", "AUX202", 120, 0, 233, TipoVuelo.PRIVADO);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.actualizarVuelo("MVD", "BUE", "AUX203", 120, -55, 233, TipoVuelo.PRIVADO);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.actualizarVuelo("MVD", "BUE", "AUX203", 120, 35, 0, TipoVuelo.PRIVADO);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.actualizarVuelo("MVD", "BUE", "AUX203", 120, 35, -1200, TipoVuelo.PRIVADO);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void actualizarVueloError2(){
        s.registrarCiudad("MVD", "Montevideo");
        s.registrarCiudad("BUE", "Buenos Aires");
        s.registrarConexion("MVD", "BUE");

        s.registrarVuelo("MVD", "BUE", "AUX200", 0, 35, 233, TipoVuelo.PRIVADO);

        retorno = s.actualizarVuelo(null, "BUE", "AUX200", 120, 35, 233, TipoVuelo.PRIVADO);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.actualizarVuelo("MVD", "", "AUX200", 120, 35, 233, TipoVuelo.PRIVADO);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.actualizarVuelo("MVD", null, "AUX200", 120, 35, 233, TipoVuelo.PRIVADO);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.actualizarVuelo("MVD", "BUE", "", 120, 35, 233, TipoVuelo.PRIVADO);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.actualizarVuelo("MVD", "BUE", null, 120, 35, 233, TipoVuelo.PRIVADO);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.actualizarVuelo("", "", "", 120, 35, 233, TipoVuelo.PRIVADO);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.actualizarVuelo(null, null, null, 120, 35, 233, TipoVuelo.PRIVADO);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.actualizarVuelo(null, "", null, 120, 35, 233, TipoVuelo.PRIVADO);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    void actualizarVueloError3(){
        s.registrarCiudad("BUE", "Buenos Aires");
        s.registrarCiudad("MVD", "Montevideo");
        s.registrarConexion("MVD", "BUE");

        retorno = s.actualizarVuelo("PDE", "BUE", "AUX200", 120, 35, 233, TipoVuelo.PRIVADO);
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }

    @Test
    void actualizarVueloError4(){
        s.registrarCiudad("BUE", "Buenos Aires");
        s.registrarCiudad("MVD", "Montevideo");
        s.registrarConexion("MVD", "BUE");

        retorno = s.actualizarVuelo("BUE", "PDE", "AUX200", 120, 35, 233, TipoVuelo.PRIVADO);
        assertEquals(Retorno.Resultado.ERROR_4, retorno.getResultado());
    }

    @Test
    void registrarVueloError5(){
        s.registrarCiudad("BUE", "Buenos Aires");
        s.registrarCiudad("MVD", "Montevideo");

        retorno = s.actualizarVuelo("BUE", "MVD", "AUX200", 120, 35, 233, TipoVuelo.PRIVADO);
        assertEquals(Retorno.Resultado.ERROR_5, retorno.getResultado());

        s.registrarConexion("MVD", "BUE");

        retorno = s.actualizarVuelo("BUE", "MVD", "AUX200", 120, 35, 233, TipoVuelo.PRIVADO);
        assertEquals(Retorno.Resultado.ERROR_5, retorno.getResultado());
    }

    @Test
    void actualizarVueloError6(){
        s.registrarCiudad("MVD", "Montevideo");
        s.registrarCiudad("BUE", "Buenos Aires");
        s.registrarConexion("MVD", "BUE");
        s.registrarConexion("BUE", "MVD");

        s.registrarVuelo("MVD", "BUE", "AUX200", 120, 35, 233, TipoVuelo.PRIVADO);
        // aunque se haya registrado un vuelo con igual codigo, el test debe fallar pues es una conexion distinta
        s.registrarVuelo("BUE", "MVDUE", "AUX201", 120, 35, 233, TipoVuelo.PRIVADO);

        retorno = s.actualizarVuelo("MVD", "BUE", "AUX201", 120, 35, 233, TipoVuelo.PRIVADO);
        assertEquals(Retorno.Resultado.ERROR_6, retorno.getResultado());
    }


}
