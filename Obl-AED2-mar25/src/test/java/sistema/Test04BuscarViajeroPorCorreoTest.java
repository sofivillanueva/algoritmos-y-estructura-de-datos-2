package sistema;

import interfaz.Categoria;
import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test04BuscarViajeroPorCorreoTest {
    private Retorno retorno;
    private final Sistema s = new ImplementacionSistema();

    @BeforeEach
    public void setUp() {
        s.inicializarSistema(10);
    }

    @Test
    void buscarViajeroOk() {
        s.registrarViajero("5.314.669-5", "Sofía", "sofia@ort.edu.uy", 25, Categoria.FRECUENTE);
        retorno = s.buscarViajeroPorCorreo("sofia@ort.edu.uy");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(1, retorno.getValorInteger());
        assertEquals("5.314.669-5;Sofía;sofia@ort.edu.uy;25;Frecuente", retorno.getValorString());

        s.registrarViajero("3.444.669-5", "Marcos", "marcos@ort.edu.uy", 27, Categoria.PLATINO);
        retorno = s.buscarViajeroPorCorreo("marcos@ort.edu.uy");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(2, retorno.getValorInteger());
        assertEquals("3.444.669-5;Marcos;marcos@ort.edu.uy;27;Platino", retorno.getValorString());
    }

    @Test
    void buscarViajeroError1() {
        retorno = s.buscarViajeroPorCorreo("");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.buscarViajeroPorCorreo(null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void buscarViajeroError2() {
        retorno = s.buscarViajeroPorCorreo("53208760");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.buscarViajeroPorCorreo("marcosort.edu.uy");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

//        retorno = s.buscarViajeroPorCorreo("marcos@ortedu.uy");
//        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    void buscarViajeroError3() {
        retorno = s.buscarViajeroPorCorreo("marcos@ort.edu.uy");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());

        s.registrarViajero("5.314.669-5", "Sofía", "sofia@ort.edu.uy", 25, Categoria.FRECUENTE);
        retorno = s.buscarViajeroPorCorreo("marcos@ort.edu.uy");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }
}
