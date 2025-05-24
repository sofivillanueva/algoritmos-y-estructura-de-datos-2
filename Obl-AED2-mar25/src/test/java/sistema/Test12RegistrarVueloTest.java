package sistema;

import interfaz.Retorno;
import interfaz.Sistema;
import interfaz.TipoVuelo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test12RegistrarVueloTest {
    private Retorno retorno;
    private final Sistema s = new ImplementacionSistema();

    @BeforeEach
    public void setUp() {
        s.inicializarSistema(10);
    }

    @Test
    void registrarVueloOk(){
        s.registrarCiudad("MVD", "Montevideo");
        s.registrarCiudad("BUE", "Buenos Aires");
        s.registrarConexion("MVD", "BUE");
        s.registrarConexion("BUE", "MVD");

        retorno = s.registrarVuelo("MVD", "BUE", "AUX200", 120, 35, 233, TipoVuelo.PRIVADO);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = s.registrarVuelo("MVD", "BUE", "AUX201", 120, 35, 150, TipoVuelo.COMERCIAL);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = s.registrarVuelo("BUE", "MVD", "AUX200", 150, 45, 150, TipoVuelo.COMERCIAL);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado()); // vuelo con = codigo pero != conexion

    }

    @Test
    void registrarVueloError1(){
        s.registrarCiudad("MVD", "Montevideo");
        s.registrarCiudad("BUE", "Buenos Aires");
        s.registrarConexion("MVD", "BUE");

        retorno = s.registrarVuelo("MVD", "BUE", "AUX200", 0, 35, 233, TipoVuelo.PRIVADO);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarVuelo("MVD", "BUE", "AUX201", -13, 15, 233, TipoVuelo.PRIVADO);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarVuelo("MVD", "BUE", "AUX202", 120, 0, 233, TipoVuelo.PRIVADO);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarVuelo("MVD", "BUE", "AUX203", 120, -55, 233, TipoVuelo.PRIVADO);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarVuelo("MVD", "BUE", "AUX203", 120, 35, 0, TipoVuelo.PRIVADO);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarVuelo("MVD", "BUE", "AUX203", 120, 35, -1200, TipoVuelo.PRIVADO);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void registrarVueloError2(){
        s.registrarCiudad("MVD", "Montevideo");
        s.registrarCiudad("BUE", "Buenos Aires");
        s.registrarConexion("MVD", "BUE");

        retorno = s.registrarVuelo("", "BUE", "AUX200", 120, 35, 233, TipoVuelo.PRIVADO);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarVuelo(null, "BUE", "AUX200", 120, 35, 233, TipoVuelo.PRIVADO);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarVuelo("MVD", "", "AUX200", 120, 35, 233, TipoVuelo.PRIVADO);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarVuelo("MVD", null, "AUX200", 120, 35, 233, TipoVuelo.PRIVADO);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarVuelo("MVD", "BUE", "", 120, 35, 233, TipoVuelo.PRIVADO);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarVuelo("MVD", "BUE", null, 120, 35, 233, TipoVuelo.PRIVADO);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarVuelo("", "", "", 120, 35, 233, TipoVuelo.PRIVADO);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarVuelo(null, null, null, 120, 35, 233, TipoVuelo.PRIVADO);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarVuelo(null, "", null, 120, 35, 233, TipoVuelo.PRIVADO);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    void registrarVueloError3(){
        s.registrarCiudad("BUE", "Buenos Aires");
        s.registrarCiudad("MVD", "Montevideo");
        s.registrarConexion("MVD", "BUE");

        retorno = s.registrarVuelo("PDE", "BUE", "AUX200", 120, 35, 233, TipoVuelo.PRIVADO);
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }

    @Test
    void registrarVueloError4(){
        s.registrarCiudad("BUE", "Buenos Aires");
        s.registrarCiudad("MVD", "Montevideo");
        s.registrarConexion("MVD", "BUE");

        retorno = s.registrarVuelo("BUE", "PDE", "AUX200", 120, 35, 233, TipoVuelo.PRIVADO);
        assertEquals(Retorno.Resultado.ERROR_4, retorno.getResultado());
    }

    @Test
    void registrarVueloError5(){
        s.registrarCiudad("BUE", "Buenos Aires");
        s.registrarCiudad("MVD", "Montevideo");

        retorno = s.registrarVuelo("BUE", "MVD", "AUX200", 120, 35, 233, TipoVuelo.PRIVADO);
        assertEquals(Retorno.Resultado.ERROR_5, retorno.getResultado());

        s.registrarConexion("MVD", "BUE");

        retorno = s.registrarVuelo("BUE", "MVD", "AUX200", 120, 35, 233, TipoVuelo.PRIVADO);
        assertEquals(Retorno.Resultado.ERROR_5, retorno.getResultado());
    }

    @Test
    void registrarVueloError6(){
        s.registrarCiudad("MVD", "Montevideo");
        s.registrarCiudad("BUE", "Buenos Aires");
        s.registrarConexion("MVD", "BUE");
        s.registrarConexion("BUE", "MVD");

        s.registrarVuelo("MVD", "BUE", "AUX200", 120, 35, 233, TipoVuelo.PRIVADO);

        retorno = s.registrarVuelo("MVD", "BUE", "AUX200", 120, 35, 233, TipoVuelo.PRIVADO);
        assertEquals(Retorno.Resultado.ERROR_6, retorno.getResultado());
    }


}
