package sistema;

import interfaz.Categoria;
import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test06ListarViajerosPorCedulaDescendenteTest {
    private Retorno retorno;
    private final Sistema s = new ImplementacionSistema();

    @BeforeEach
    public void setUp() {
        s.inicializarSistema(10);
    }

    @Test
    void listarViajerosPorCedulaDescendenteOk() {
        s.registrarViajero("8.314.817-2", "Sofía", "sofia@ort.edu.uy", 23, Categoria.PLATINO);
        s.registrarViajero("5.314.897-2", "Marcos", "marcos@ort.edu.uy", 22, Categoria.FRECUENTE);
        s.registrarViajero("5.314.897-1", "Carolina", "carolina@ort.edu.uy", 20, Categoria.FRECUENTE);
        s.registrarViajero("5.314.817-2", "Valentina", "valentina@ort.edu.uy", 19, Categoria.ESTANDAR);
        s.registrarViajero("6.314.817-2", "Manuel", "manuel@ort.edu.uy", 11, Categoria.PLATINO);

        retorno = s.listarViajerosPorCedulaDescendente();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("8.314.817-2;Sofía;sofia@ort.edu.uy;23;Platino|6.314.817-2;Manuel;manuel@ort.edu.uy;11;Platino|5.314.897-2;Marcos;marcos@ort.edu.uy;22;Frecuente|5.314.897-1;Carolina;carolina@ort.edu.uy;20;Frecuente|5.314.817-2;Valentina;valentina@ort.edu.uy;19;Estándar", retorno.getValorString());
    }
}
