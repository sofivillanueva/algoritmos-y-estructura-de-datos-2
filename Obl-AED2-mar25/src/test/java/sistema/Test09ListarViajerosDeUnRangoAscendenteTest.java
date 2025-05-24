package sistema;

import interfaz.Categoria;
import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test09ListarViajerosDeUnRangoAscendenteTest {
    private Retorno retorno;
    private final Sistema s = new ImplementacionSistema();

    @BeforeEach
    public void setUp() {
        s.inicializarSistema(10);
    }

    @Test
    void listarViajerosDeUnRangoAscendenteOk() {
        s.registrarViajero("8.314.817-2", "Sofía", "sofia@ort.edu.uy", 23, Categoria.FRECUENTE);
        s.registrarViajero("5.314.897-2", "Marcos", "marcos@ort.edu.uy", 22, Categoria.FRECUENTE);
        s.registrarViajero("5.314.897-1", "Carolina", "carolina@ort.edu.uy", 20, Categoria.FRECUENTE);
        s.registrarViajero("5.314.817-2", "Valentina", "valentina@ort.edu.uy", 33, Categoria.ESTANDAR);
        s.registrarViajero("6.314.817-2", "Manuel", "manuel@ort.edu.uy", 11, Categoria.PLATINO);

        retorno = s.listarViajerosDeUnRangoAscendente(2);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("5.314.897-1;Carolina;carolina@ort.edu.uy;20;Frecuente|5.314.897-2;Marcos;marcos@ort.edu.uy;22;Frecuente|8.314.817-2;Sofía;sofia@ort.edu.uy;23;Frecuente", retorno.getValorString());

        s.registrarViajero("6.315.823-2", "Mateo", "mateo@ort.edu.uy", 0, Categoria.PLATINO);
        s.registrarViajero("315.823-2", "Lucia", "lucia@ort.edu.uy", 3, Categoria.PLATINO);
        s.registrarViajero("2.345.233-1", "Micaela", "micaela@ort.edu.uy", 9, Categoria.PLATINO);

        retorno = s.listarViajerosDeUnRangoAscendente(0);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("315.823-2;Lucia;lucia@ort.edu.uy;3;Platino|2.345.233-1;Micaela;micaela@ort.edu.uy;9;Platino|6.315.823-2;Mateo;mateo@ort.edu.uy;0;Platino", retorno.getValorString());

        retorno = s.listarViajerosDeUnRangoAscendente(13);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("", retorno.getValorString());

        s.registrarViajero("1.342.123-1", "Alicia", "alicia@ort.edu.uy", 129, Categoria.PLATINO);
        s.registrarViajero("4.375.445-1", "Florencia", "florencia@ort.edu.uy", 130, Categoria.PLATINO);
        s.registrarViajero("5.365.448-9", "Alejandra", "alejandra@ort.edu.uy", 139, Categoria.PLATINO);

        retorno = s.listarViajerosDeUnRangoAscendente(13);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("4.375.445-1;Florencia;florencia@ort.edu.uy;130;Platino|5.365.448-9;Alejandra;alejandra@ort.edu.uy;139;Platino", retorno.getValorString());

    }

    @Test
    void listarViajerosDeUnRangoAscendenteError1() {
        retorno = s.listarViajerosDeUnRangoAscendente(-2);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void listarViajerosDeUnRangoAscendenteError2() {
        retorno = s.listarViajerosDeUnRangoAscendente(14);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }
}
