package sistema;

import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test11RegistrarConexionTest {
    private Retorno retorno;
    private final Sistema s = new ImplementacionSistema();

    @BeforeEach
    public void setUp() {
        s.inicializarSistema(10);
    }

    @Test
    void registrarConexionOk(){
        s.registrarCiudad("MVD", "Montevideo");
        s.registrarCiudad("PDE", "Punta del Este");
        s.registrarCiudad("MDR", "Madrid");

        retorno = s.registrarConexion("MVD", "PDE");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = s.registrarConexion("PDE", "MVD");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = s.registrarConexion("PDE", "MDR");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = s.registrarConexion("MVD", "MDR");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

    @Test
    void registrarConexionError1(){
        s.registrarCiudad("MVD", "Montevideo");
        s.registrarCiudad("PDE", "Punta del Este");

        retorno = s.registrarConexion("", "PDE");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarConexion(null, "PDE");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarConexion("MVD", "");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarConexion("MVD", null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarConexion("", "");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarConexion(null, null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void registrarConexionError2(){
        retorno = s.registrarConexion("MVD", "PDE");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        s.registrarCiudad("PDE", "Punta del Este");

        retorno = s.registrarConexion("MVD", "PDE");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    void registrarConexionError3(){
        s.registrarCiudad("PDE", "Punta del Este");

        retorno = s.registrarConexion("PDE", "MVD");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());

    }

    @Test
    void registrarConexionError4(){
        s.registrarCiudad("MVD", "Montevideo");
        s.registrarCiudad("BUE", "Buenos Aires");

        s.registrarConexion("MVD", "BUE");

        retorno = s.registrarConexion("MVD", "BUE");
        assertEquals(Retorno.Resultado.ERROR_4, retorno.getResultado());
    }
}
