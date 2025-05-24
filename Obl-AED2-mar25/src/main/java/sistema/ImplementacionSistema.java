package sistema;

import TAD.ABB.ABB;
import TAD.Grafo.Arista;
import TAD.Grafo.Grafo;
import TAD.Lista.Lista;
import dominio.*;
import interfaz.*;

/*
    Obligatorio AED2 - Marzo 2025
    Sofía Villanueva - 282442
*/

public class ImplementacionSistema implements Sistema {
    private int maxCiudadesSistema;

    private ABB<Viajero> ABBViajerosPorCedula;
    private ABB<ViajeroPorCorreo> ABBViajerosPorCorreoElectronico;

    private Lista<Viajero> viajerosPlatinos;
    private Lista<Viajero> viajerosFrecuentes;
    private Lista<Viajero> viajerosEstandars;

    private ABB<Viajero>[] viajerosPorRangoEdad = new ABB[14];

    private Grafo grafoCiudades;

    public ImplementacionSistema() {}

    @Override
    public Retorno inicializarSistema(int maxCiudades) {
        if (maxCiudades <= 4) {
            return Retorno.error1("maxCiudades no puede ser menor o igual a 4.");
        } else {
            ABBViajerosPorCedula = new ABB<>();
            ABBViajerosPorCorreoElectronico = new ABB<>();
            viajerosPlatinos = new Lista<>();
            viajerosFrecuentes = new Lista<>();
            viajerosEstandars = new Lista<>();
            this.maxCiudadesSistema = maxCiudades;
            grafoCiudades = new Grafo(maxCiudades, true);

            for (int i = 0; i <= 13; i++) {
                viajerosPorRangoEdad[i] = new ABB<>();
            }
            return Retorno.ok();
        }
    }

    @Override
    public Retorno registrarViajero(String cedula, String nombre, String correo, int edad, Categoria categoria) {
        if (cedula == null || cedula.trim().isBlank() || nombre == null || nombre.trim().isBlank() || correo == null || correo.trim().isBlank() || categoria == null) {
            return Retorno.error1("Ningún parámetro puede ser vacío o nulo.");
        } else if (!Utilities.validarCedula(cedula)) {
            return Retorno.error2("El formato de la cédula ingresada no es válido.");
        } else if (!Utilities.validarCorreo(correo)) {
            return Retorno.error3("El formato del correo ingresado no es válido.");
        } else if (edad < 0 || edad > 139) {
            return Retorno.error4("La edad ingresada no es válida.");
        } else if (buscarViajeroPorCedula(cedula).getResultado() == Retorno.Resultado.OK) {
            return Retorno.error5("Ya existe un viajero registrado con esa cédula en el sistema.");
        } else if (buscarViajeroPorCorreo(correo).getResultado() == Retorno.Resultado.OK) {
            return Retorno.error6("Ya existe un viajero registrado con ese correo en el sistema.");
        }

        Viajero viajeroPorCedula = new Viajero(cedula, nombre, correo, edad, categoria);
        ViajeroPorCorreo viajeroPorCorreo = new ViajeroPorCorreo(viajeroPorCedula);

        ABBViajerosPorCedula.insertar(viajeroPorCedula);
        ABBViajerosPorCorreoElectronico.insertar(viajeroPorCorreo);
        agregarViajeroSegunCategoria(viajeroPorCedula);
        agregarViajeroSegunEdad(viajeroPorCedula);
        return Retorno.ok();
    }

    private void agregarViajeroSegunEdad(Viajero viajero) {
        int rangoDeEdad = viajero.getEdad() / 10;
        ABB<Viajero> ABBViajerosPorRangoEdad = viajerosPorRangoEdad[rangoDeEdad];
        if (ABBViajerosPorRangoEdad != null) {
            ABBViajerosPorRangoEdad.insertar(viajero);
        } else {
            System.out.println("Error: No se pudo agregar el viajero al rango de edad correspondiente.");
        }
    }

    private void agregarViajeroSegunCategoria(Viajero viajero) {
        if (viajero.getCategoria() == Categoria.PLATINO) {
            viajerosPlatinos.insertarOrd(viajero);
        } else if (viajero.getCategoria() == Categoria.FRECUENTE) {
            viajerosFrecuentes.insertarOrd(viajero);
        } else {
            viajerosEstandars.insertarOrd(viajero);
        }
    }

    @Override
    public Retorno buscarViajeroPorCedula(String cedula) {
        if (cedula == null || cedula.trim().isBlank()) {
            return Retorno.error1("La cédula no puede estar vacía.");
        } else if (!Utilities.validarCedula(cedula)) {
            return Retorno.error2("La cédula ingresada no tiene un formato válido.");
        } else {
            Viajero viajeroCedula = new Viajero(cedula, "", "", 0, null);
            Viajero viajeroBuscado = ABBViajerosPorCedula.buscar(viajeroCedula);
            if (viajeroBuscado == null) {
                return Retorno.error3("No existe un viajero registrado con esa cédula.");
            } else {
                String valorString = viajeroBuscado.toString();
                int valorEntero = ABBViajerosPorCedula.getCantNodosBuscados();
                return Retorno.ok(valorEntero, valorString);
            }
        }
    }

    @Override
    public Retorno buscarViajeroPorCorreo(String correo) {
        Viajero viajeroCedula = new Viajero("", "", correo, 0, null);
        ViajeroPorCorreo viajeroPorCorreo = new ViajeroPorCorreo(viajeroCedula);
        if (correo == null || correo.isBlank()) {
            return Retorno.error1("El correo no puede estar vacío.");
        } else if (!Utilities.validarCorreo(correo)) {
            return Retorno.error2("El correo ingresado no tiene un formato válido.");
        } else {
            ViajeroPorCorreo viajeroBuscado = ABBViajerosPorCorreoElectronico.buscar(viajeroPorCorreo);
            if (viajeroBuscado == null) {
                return Retorno.error3("No existe un viajero registrado con ese correo.");
            } else {
                int valorEntero = ABBViajerosPorCorreoElectronico.getCantNodosBuscados();
                return Retorno.ok(valorEntero, viajeroBuscado.toString());
            }
        }
    }

    @Override
    public Retorno listarViajerosPorCedulaAscendente() {
        String listaDeViajerosFormateada = "";
        listaDeViajerosFormateada = ABBViajerosPorCedula.generarListaAsc().listaComoTexto();
        return Retorno.ok(listaDeViajerosFormateada);
    }

    @Override
    public Retorno listarViajerosPorCedulaDescendente() {
        String listaDeViajerosFormateada = "";
        listaDeViajerosFormateada = ABBViajerosPorCedula.generarListaDesc().listaComoTexto();
        return Retorno.ok(listaDeViajerosFormateada);
    }

    @Override
    public Retorno listarViajerosPorCorreoAscendente() {
        String listaDeViajerosFormateada = "";
        listaDeViajerosFormateada = ABBViajerosPorCorreoElectronico.generarListaAsc().listaComoTexto();
        return Retorno.ok(listaDeViajerosFormateada);
    }

    @Override
    public Retorno listarViajerosPorCategoria(Categoria unaCategoria) {
        if (unaCategoria == Categoria.PLATINO) {
            String listaDeViajerosFormateada = "";
            listaDeViajerosFormateada = viajerosPlatinos.listaComoTexto();
            return Retorno.ok(listaDeViajerosFormateada);
        } else if (unaCategoria == Categoria.FRECUENTE) {
            String listaDeViajerosFormateada = "";
            listaDeViajerosFormateada = viajerosFrecuentes.listaComoTexto();
            return Retorno.ok(listaDeViajerosFormateada);
        } else {
            String listaDeViajerosFormateada = "";
            listaDeViajerosFormateada = viajerosEstandars.listaComoTexto();
            return Retorno.ok(listaDeViajerosFormateada);
        }
    }

    @Override
    public Retorno listarViajerosDeUnRangoAscendente(int rango) {
        if (rango < 0) {
            return Retorno.error1("El rango no puede ser menor a 0");
        } else if (rango > 13) {
            return Retorno.error2("El rango no puede ser mayor a 13");
        }
        ABB<Viajero> ABBViajerosPorRangoEdad = viajerosPorRangoEdad[rango];
        String listaDeViajerosFormateada = "";
        listaDeViajerosFormateada = ABBViajerosPorRangoEdad.generarListaAsc().listaComoTexto();
        return Retorno.ok(listaDeViajerosFormateada);
    }

    @Override
    public Retorno registrarCiudad(String codigo, String nombre) {
        if (seAlcanzoElMaximoDeCiudades()) {
            return Retorno.error1("Ya se registró la máxima cantidad de ciudades disponibles.");
        } else if (codigo == null || codigo.trim().isBlank() || nombre == null || nombre.trim().isBlank()) {
            return Retorno.error2("El código o nombre no pueden ser vacíos ni nulos.");
        } else if (existeCiudad(codigo)) {
            return Retorno.error3("Ya existe una ciudad registrada con ese código.");
        }

        Ciudad ciudad = new Ciudad(codigo, nombre);
        grafoCiudades.agregarVertice(ciudad);
        return Retorno.ok();
    }

    private boolean seAlcanzoElMaximoDeCiudades() {
        return grafoCiudades.getCantActualvertices() == maxCiudadesSistema;
    }

    private boolean existeCiudad(String codigo) {
        Ciudad ciudad = new Ciudad(codigo, "");
        return grafoCiudades.existeVertice(ciudad);
    }

    @Override
    public Retorno registrarConexion(String codigoCiudadOrigen, String codigoCiudadDestino) {
        if (codigoCiudadOrigen == null || codigoCiudadOrigen.trim().isBlank() || codigoCiudadDestino == null || codigoCiudadDestino.trim().isBlank()) {
            return Retorno.error1("La ciudad de origen o la ciudad de destino no pueden ser vacías.");
        } else if (!existeCiudad(codigoCiudadOrigen)) {
            return Retorno.error2("La ciudad de origen ingresada no existe en el sistema.");
        } else if (!existeCiudad(codigoCiudadDestino)) {
            return Retorno.error3("La ciudad de destino ingresada no existe en el sistema.");
        } else if (existeConexion(codigoCiudadOrigen, codigoCiudadDestino)) {
            return Retorno.error4("Ya existe una conexión entre el origen y el destino ingresados.");
        }
        Ciudad ciudadOrigen = new Ciudad(codigoCiudadOrigen, "");
        Ciudad ciudadDestino = new Ciudad(codigoCiudadDestino, "");

        grafoCiudades.agregarArista(ciudadOrigen, ciudadDestino, 1);
        return Retorno.ok();
    }

    private boolean existeConexion(String codigoCiudadOrigen, String codigoCiudadDestino) {
        Ciudad ciudadOrigen = new Ciudad(codigoCiudadOrigen, "");
        Ciudad ciudadDestino = new Ciudad(codigoCiudadDestino, "");
        return grafoCiudades.hayConexion(ciudadOrigen, ciudadDestino);
    }

    @Override
    public Retorno registrarVuelo(String codigoCiudadOrigen, String codigoCiudadDestino, String codigoDeVuelo, double combustible, double minutos, double costoEnDolares, TipoVuelo tipoDeVuelo) {
        if (combustible <= 0 || minutos <= 0 || costoEnDolares <= 0) {
            return Retorno.error1("Ningún número puede ser menor o igual a 0.");
        } else if (codigoCiudadOrigen == null || codigoCiudadOrigen.trim().isEmpty() || codigoCiudadDestino == null || codigoCiudadDestino.trim().isEmpty() || codigoDeVuelo == null || codigoDeVuelo.trim().isEmpty() || tipoDeVuelo == null) {
            return Retorno.error2("La ciudad de origen, de destino y el código de vuelo no pueden ser vacíos.");
        } else if (!existeCiudad(codigoCiudadOrigen)) {
            return Retorno.error3("La ciudad de origen ingresada no existe en el sistema.");
        } else if (!existeCiudad(codigoCiudadDestino)) {
            return Retorno.error4("La ciudad de destino ingresada no existe en el sistema.");
        } else if (!existeConexion(codigoCiudadOrigen, codigoCiudadDestino)) {
            return Retorno.error5("No existe una conexión entre la ciudad de origen y destino previamente registrada en el sistema.");
        } else if (existeVuelo(codigoDeVuelo, codigoCiudadOrigen, codigoCiudadDestino)) {
            return Retorno.error6("Ya existe un vuelo registrado con el mismo código y conexión.");

        }
        Lista<Vuelo> vuelos = obtenerVuelosDeConexion(codigoCiudadOrigen, codigoCiudadDestino);
        Vuelo vuelo = new Vuelo(obtenerCiudad(codigoCiudadOrigen), obtenerCiudad(codigoCiudadDestino), codigoDeVuelo, combustible, minutos, costoEnDolares, tipoDeVuelo);
        vuelos.insertar(vuelo);
        return Retorno.ok();
    }

    private Ciudad obtenerCiudad(String codigoCiudad) {
        int ciudadPosicion = grafoCiudades.obtenerPos(new Ciudad(codigoCiudad, ""));
        return grafoCiudades.getVertices()[ciudadPosicion];
    }

    private Lista<Vuelo> obtenerVuelosDeConexion(String codigoCiudadOrigen, String codigoCiudadDestino) {
        Ciudad ciudadOrigen = obtenerCiudad(codigoCiudadOrigen);
        Ciudad ciudadDestino = obtenerCiudad(codigoCiudadDestino);
        Arista conexion = grafoCiudades.obtenerArista(ciudadOrigen, ciudadDestino);
        return conexion.getVuelos();
    }

    private boolean existeVuelo(String codigoDeVuelo, String codigoCiudadOrigen, String codigoCiudadDestino) {
        Arista conexion = grafoCiudades.obtenerArista(new Ciudad(codigoCiudadOrigen, ""), new Ciudad(codigoCiudadDestino, ""));
        if (conexion.getVuelos().esVacia()) {
            return false;
        }
        return conexion.getVuelos().existe(new Vuelo(null, null, codigoDeVuelo, 0, 0, 0, null));
    }

    @Override
    public Retorno actualizarVuelo(String codigoCiudadOrigen, String codigoCiudadDestino, String codigoDeVuelo, double combustible, double minutos, double costoEnDolares, TipoVuelo tipoDeVuelo) {
        if (combustible <= 0 || minutos <= 0 || costoEnDolares <= 0) {
            return Retorno.error1("Ningún número puede ser menor o igual a 0.");
        } else if (codigoCiudadOrigen == null || codigoCiudadOrigen.trim().isEmpty() || codigoCiudadDestino == null || codigoCiudadDestino.trim().isEmpty() || codigoDeVuelo == null || codigoDeVuelo.trim().isEmpty() || tipoDeVuelo == null) {
            return Retorno.error2("La ciudad de origen, de destino y el código de vuelo no pueden ser vacíos.");
        } else if (!existeCiudad(codigoCiudadOrigen)) {
            return Retorno.error3("La ciudad de origen ingresada no existe en el sistema.");
        } else if (!existeCiudad(codigoCiudadDestino)) {
            return Retorno.error4("La ciudad de destino ingresada no existe en el sistema.");
        } else if (!existeConexion(codigoCiudadOrigen, codigoCiudadDestino)) {
            return Retorno.error5("No existe una conexión entre la ciudad de origen y destino previamente registrada en el sistema.");
        } else if (!existeVuelo(codigoDeVuelo, codigoCiudadOrigen, codigoCiudadDestino)) {
            return Retorno.error6("No existe un vuelo registrado con el código y la conexión ingresada.");
        }

        Vuelo vueloActual = buscarVueloPorCodigoYConexion(codigoDeVuelo, codigoCiudadOrigen, codigoCiudadDestino);
        if (vueloActual != null) {
            vueloActual.setCombustible(combustible);
            vueloActual.setMinutos(minutos);
            vueloActual.setCostoEnDolares(costoEnDolares);
            vueloActual.setTipoDeVuelo(tipoDeVuelo);
        }

        return Retorno.ok();
    }

    private Vuelo buscarVueloPorCodigoYConexion(String codigoDeVuelo, String codigoCiudadOrigen, String codigoCiudadDestino) {
        Lista<Vuelo> vuelos = obtenerVuelosDeConexion(codigoCiudadOrigen, codigoCiudadDestino);
            for (int i = 0; i < vuelos.largo(); i++) {
                Vuelo vuelo = vuelos.obtenerPorPos(i);
                if (vuelo != null && vuelo.getCodigoDeVuelo().equals(codigoDeVuelo)) {
                    return vuelo;
                }
            }
        return null;
    }

    @Override
    public Retorno listadoCiudadesCantDeEscalas(String codigoCiudadOrigen, int cantidad) {
        if (cantidad < 0) {
            return Retorno.error1("La cantidad ingresada no puede ser menor a 0.");
        } else if (codigoCiudadOrigen == null || codigoCiudadOrigen.trim().isEmpty()) {
            return Retorno.error2("El código ingresado no puede ser vacío o nulo.");
        } else if (!existeCiudad(codigoCiudadOrigen)) {
            return Retorno.error3("La ciudad de origen ingresada no existe en el sistema.");
        }

        Ciudad ciudad = obtenerCiudad(codigoCiudadOrigen);

        Lista<Ciudad> ciudades = grafoCiudades.bfsConLimiteDeCamino(ciudad, cantidad);
        String ciudadesFormateadas = "";
        if (ciudades != null) {
            ciudadesFormateadas = formatear(ciudades);
        }
        return Retorno.ok(ciudadesFormateadas);
    }

    private String formatear(Lista<Ciudad> ciudades) {
        String listaDeCiudadesFormateada = "";
        listaDeCiudadesFormateada = ciudades.listaComoTexto();
        return listaDeCiudadesFormateada;
    }

    @Override
    public Retorno viajeCostoMinimoMinutos(String codigoCiudadOrigen, String codigoCiudadDestino, TipoVueloPermitido tipoVueloPermitido) {
//        Si el camino pudo ser calculado exitosamente.
//                Retorna en valorEntero la cantidad total minutos del camino.
//        Retorna en valorString el camino desde la ciudad origen a la ciudad destino
//        incluidas.
        if (codigoCiudadOrigen == null || codigoCiudadOrigen.trim().isEmpty() || codigoCiudadDestino == null || codigoCiudadDestino.trim().isEmpty() || tipoVueloPermitido == null) {
            return Retorno.error1("Los parámetros no pueden ser vacíos o nulos.");
        } else if (!existeCiudad(codigoCiudadOrigen)) {
            return Retorno.error2("La ciudad de origen ingresada no existe en el sistema.");
        } else if (!existeCiudad(codigoCiudadDestino)) {
            return Retorno.error3("La ciudad de destino ingresada no existe en el sistema.");
        }
        Ciudad ciudadOrigen = obtenerCiudad(codigoCiudadOrigen);
        Ciudad ciudadDestino = obtenerCiudad(codigoCiudadDestino);

        if (!grafoCiudades.hayCaminoBFS(ciudadOrigen, ciudadDestino)) {
            return Retorno.error4("No existe un camino entre la ciudades de origen y de destino ingresadas.");
        }

        RetornoString retornoString = grafoCiudades.dijkstra(ciudadOrigen, ciudadDestino,"minutos", tipoVueloPermitido);
        String listaFormateada = "";
        listaFormateada = retornoString.getLista().listaComoTexto();
        return Retorno.ok(retornoString.getCantidadResultado(), listaFormateada);
    }

    @Override
    public Retorno viajeCostoMinimoDolares(String codigoCiudadOrigen, String codigoCiudadDestino, TipoVueloPermitido tipoVueloPermitido) {
        // : Retorna el camino más corto en dólares (monto acumulado más barato) que podría realizar un
        //viajero para ir de la ciudad origen a la ciudad destino.
        if (codigoCiudadOrigen == null || codigoCiudadOrigen.trim().isEmpty() || codigoCiudadDestino == null || codigoCiudadDestino.trim().isEmpty() || tipoVueloPermitido == null) {
            return Retorno.error1("Los parámetros no pueden ser vacíos o nulos.");
        } else if (!existeCiudad(codigoCiudadOrigen)) {
            return Retorno.error2("La ciudad de origen ingresada no existe en el sistema.");
        } else if (!existeCiudad(codigoCiudadDestino)) {
            return Retorno.error3("La ciudad de destino ingresada no existe en el sistema.");
        }

        Ciudad ciudadOrigen = obtenerCiudad(codigoCiudadOrigen);
        Ciudad ciudadDestino = obtenerCiudad(codigoCiudadDestino);

        if (!grafoCiudades.hayCaminoBFS(ciudadOrigen, ciudadDestino)) {
            return Retorno.error4("No existe un camino entre la ciudades de origen y de destino ingresadas.");
        }

        RetornoString retornoString = grafoCiudades.dijkstra(ciudadOrigen, ciudadDestino,"precio", tipoVueloPermitido);
        String listaFormateada = "";
        listaFormateada = retornoString.getLista().listaComoTexto();
        return Retorno.ok(retornoString.getCantidadResultado(), listaFormateada);
    }
}

