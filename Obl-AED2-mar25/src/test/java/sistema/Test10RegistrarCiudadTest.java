package sistema;

import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test10RegistrarCiudadTest {
    private Retorno retorno;
    private final Sistema s = new ImplementacionSistema();

    @BeforeEach
    public void setUp() {
        s.inicializarSistema(10);
    }

    @Test
    void registrarCiudadOk(){
        retorno = s.registrarCiudad("MVD", "Montevideo");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = s.registrarCiudad("PDE", "Punta del Este");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = s.registrarCiudad("BUE", "Buenos Aires");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = s.registrarCiudad("SCL", "Santiago");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = s.registrarCiudad("GRU", "São Paulo");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = s.registrarCiudad("RIO", "Rio de Janeiro");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = s.registrarCiudad("LIM", "Lima");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = s.registrarCiudad("BOG", "Bogotá");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = s.registrarCiudad("MAD", "Madrid");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = s.registrarCiudad("MIA", "Miami");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

    @Test
    void registrarCiudadError1(){
        retorno = s.registrarCiudad("MVD", "Montevideo");
        retorno = s.registrarCiudad("PDE", "Punta del Este");
        retorno = s.registrarCiudad("BUE", "Buenos Aires");
        retorno = s.registrarCiudad("SCL", "Santiago");
        retorno = s.registrarCiudad("GRU", "São Paulo");
        retorno = s.registrarCiudad("RIO", "Rio de Janeiro");
        retorno = s.registrarCiudad("LIM", "Lima");
        retorno = s.registrarCiudad("BOG", "Bogotá");
        retorno = s.registrarCiudad("MAD", "Madrid");
        retorno = s.registrarCiudad("MIA", "Miami");

        retorno = s.registrarCiudad("MIA", "Miami");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void registrarCiudadError2(){
        retorno = s.registrarCiudad("", "Miami");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarCiudad(null, "Miami");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarCiudad("MIA", "");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarCiudad("MIA", null);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarCiudad(null, null);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarCiudad("", "");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarCiudad("", null);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarCiudad(null, "");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    void registrarCiudadError3(){
        s.registrarCiudad("MVD", "Montevideo");
        retorno = s.registrarCiudad("MVD", "Montevideo");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());

        s.registrarCiudad("PDE", "Punta del Este");
        retorno = s.registrarCiudad("PDE", "Montevideo");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }
}
