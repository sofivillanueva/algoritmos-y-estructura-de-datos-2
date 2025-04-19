package sistema;

import interfaz.*;

import java.util.regex.Pattern;

public class ImplementacionSistema implements Sistema  {

    private int maxCiudades;

    public ImplementacionSistema(){};

    @Override
    public Retorno inicializarSistema(int maxCiudades) {
        if (maxCiudades <= 4){
            return Retorno.error1("maxCiudades no puede ser menor o igual a 4.");
        } else {
            return Retorno.ok();
        }
    }

    @Override
    public Retorno registrarViajero(String cedula, String nombre, String correo, int edad, Categoria categoria) {
        if(cedula.isBlank() || cedula.isEmpty() || nombre.isBlank() || nombre.isEmpty() || correo.isBlank() || correo.isEmpty() || categoria ==null){
            return Retorno.error1("Ningún parámetro puede ser vacío o nulo.");
        } else if (!validarCedula(cedula)){
            return Retorno.error2("El formato de la cédula ingresada no es válido.");
        } else if (!validarCorreo(correo)){
            return Retorno.error3("El formato del correo ingresado no es válido.");
        } else if (edad < 0 || edad > 139){
            return Retorno.error4("La edad ingresada no es válida.");
        } else if (buscarViajeroPorCedula(cedula) == Retorno.ok()){
            return Retorno.error5("Ya existe un viajero registrado con esa cédula en el sistema.");
        } else if (buscarViajeroPorCorreo(correo) == Retorno.ok()){
            return Retorno.error5("Ya existe un viajero registrado con ese correo en el sistema.");
        }
        return Retorno.ok();
    }

    // TODO: Esto se deberia pasar a la clase viajero despues creo
    private boolean validarCedula(String cedula){
        String patron = "";
        if (cedula.length() == 11){
            patron = "^\\d\\.\\d{3}\\.\\d{3}-\\d$";
        } else if (cedula.length() == 9){
            patron = "^\\d{3}\\.\\d{3}-\\d$";
        } else {
            return false;
        }
        return Pattern.matches(patron, cedula);
    }

    private boolean validarCorreo(String correo){
        String patron = "^[^@]+@[^@]+$";
        return Pattern.matches(patron,correo);
    }

    @Override
    public Retorno buscarViajeroPorCedula(String cedula) {
        Viajero viajero = new Viajero();
        if( cedula.isBlank() || cedula == null){
            return Retorno.error1("La cédula no puede estar vacía.");
        } else if (!validarCedula(cedula)){
            return Retorno.error2("La cédula ingresada no tiene un formato válido.");
        } else { //TODO
            viajero = buscarViajeroPorCedula(cedula);
            if (viajero == null){
                return Retorno.error3("No existe un viajero registrado con esa cédula.");
            } else {
                return Retorno.ok(); // TODO: agregar el valorString y el valorEntero
            }
        }
    }

    @Override
    public Retorno buscarViajeroPorCorreo(String correo) {
        Viajero viajero = new Viajero();
        if(correo.isBlank() || correo == null){
            return Retorno.error1("El correo no puede estar vacío.");
        } else if (!validarCorreo(correo)){
            return Retorno.error2("El correo ingresado no tiene un formato válido.");
        } else { //TODO
            viajero = buscarViajeroPorCorreo(correo);
            if (viajero == null){
                return Retorno.error3("No existe un viajero registrado con ese correo.");
            } else {
                return Retorno.ok(); // TODO: agregar el valorString y el valorEntero
            }
        }
    }

    @Override
    public Retorno listarViajerosPorCedulaAscendente() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarViajerosPorCedulaDescendente() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarViajerosPorCorreoAscendente() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarViajerosPorCategoria(Categoria unaCategoria) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarViajerosDeUnRangoAscendente(int rango) {
        if (rango < 0){
            return Retorno.error1("El rango no puede ser menor a 0");
        } else if (rango>13){
            return Retorno.error2("El rango no puede ser mayor a 13");
        }
        return Retorno.ok();
    }

    @Override
    public Retorno registrarCiudad(String codigo, String nombre) {
        if (cantidadDeCiudadesRegistradas == this.maxCiudades){
            return Retorno.error1("Ya se registró la máxima cantidad de ciudades disponibles.")
        } else if (codigo.isBlank() || codigo == null || nombre.isBlank() || nombre == null){
            return Retorno.error2("El código o nombre no pueden ser vacíos ni nulos.");
        } else if (existeCiudad(codigo)){
            return Retorno.error3("Ya existe una ciudad registrada con ese código.");
        }
        return Retorno.ok();
    }

    @Override
    public Retorno registrarConexion(String codigoCiudadOrigen, String codigoCiudadDestino) {
        if (codigoCiudadOrigen.isBlank() || codigoCiudadOrigen == null || codigoCiudadDestino.isBlank() || codigoCiudadDestino == null){
            return Retorno.error1("La ciudad de origen o la ciudad de destino no pueden ser vacías.");
        } else if (!existeCiudadOrigen(codigoCiudadOrigen)){
            return Retorno.error2("La ciudad de origen ingresada no existe en el sistema.");
        } else if (!existeCiudadDestino(codigoCiudadDestino)){
            return Retorno.error3("La ciudad de destino ingresada no existe en el sistema.");
        } else if (existeConexion(codigoCiudadOrigen, codigoCiudadDestino)){
            return Retorno.error4("Ya existe una conexión entre el origen y el destino ingresados.");
        }
        return Retorno.ok();
    }

    @Override
    public Retorno registrarVuelo(String codigoCiudadOrigen, String codigoCiudadDestino, String codigoDeVuelo, double combustible, double minutos, double costoEnDolares, TipoVuelo tipoDeVuelo) {
        if(combustible<=0 || minutos<=0 || costoEnDolares<=0){
            return Retorno.error1("Ningún número puede ser menor o igual a 0.");
        } else if (codigoCiudadOrigen == null || codigoCiudadOrigen.isEmpty() || codigoCiudadDestino == null || codigoCiudadDestino.isEmpty() || codigoDeVuelo == null || codigoDeVuelo.isEmpty()){
            return Retorno.error2("La ciudad de origen, de destino y el código de vuelo no pueden ser vacíos.");
        } else if (!existeCiudadOrigen(codigoCiudadOrigen)){
            return Retorno.error3("La ciudad de origen ingresada no existe en el sistema.");
        } else if (!existeCiudadDestino(codigoCiudadDestino)){
            return Retorno.error4("La ciudad de destino ingresada no existe en el sistema.");
        } else if (!existeConexion(codigoCiudadOrigen,codigoCiudadDestino)){
            return Retorno.error5("No existe una conexión entre la ciudad de origeb y destino previamente registrada en el sistema.");
        } else if (existeVuelo(codigoDeVuelo, codigoCiudadOrigen,codigoCiudadDestino)){ // TODO: ver si es mejor buscar y guardar la conexion antes
            return Retorno.error6("Ya existe un vuelo registrado con el mismo código y conexión.");
        }

        // TODO: registrar vuelo
        return Retorno.ok();
    }

    @Override
    public Retorno actualizarVuelo(String codigoCiudadOrigen, String codigoCiudadDestino, String codigoDeVuelo, double combustible, double minutos, double costoEnDolares, TipoVuelo tipoDeVuelo) {
        if(combustible<=0 || minutos<=0 || costoEnDolares<=0){
            return Retorno.error1("Ningún número puede ser menor o igual a 0.");
        } else if (codigoCiudadOrigen == null || codigoCiudadOrigen.isEmpty() || codigoCiudadDestino == null || codigoCiudadDestino.isEmpty() || codigoDeVuelo == null || codigoDeVuelo.isEmpty()){
            return Retorno.error2("La ciudad de origen, de destino y el código de vuelo no pueden ser vacíos.");
        } else if (!existeCiudadOrigen(codigoCiudadOrigen)){
            return Retorno.error3("La ciudad de origen ingresada no existe en el sistema.");
        } else if (!existeCiudadDestino(codigoCiudadDestino)){
            return Retorno.error4("La ciudad de destino ingresada no existe en el sistema.");
        } else if (!existeConexion(codigoCiudadOrigen,codigoCiudadDestino)){
            return Retorno.error5("No existe una conexión entre la ciudad de origeb y destino previamente registrada en el sistema.");
        } else if (!existeVuelo(codigoDeVuelo, codigoCiudadOrigen,codigoCiudadDestino)){ // TODO: ver si es mejor buscar y guardar la conexion antes
            return Retorno.error6("No existe un vuelo registrado con el código y la conexión ingresada.");
        }

        return Retorno.ok();
    }

    @Override
    public Retorno listadoCiudadesCantDeEscalas(String codigoCiudadOrigen, int cantidad) {
//        Retorna en valorString los datos de las ciudades a las que se pueda llegar con
//        hasta “cantidad” de escalas ordenadas de forma creciente por código.
        if(cantidad<=0){
            return Retorno.error1("La cantidad ingresada no puede ser menor o igual a 0.");
        } else if (codigoCiudadOrigen == null || codigoCiudadOrigen.isEmpty()){
            return Retorno.error2("El código ingresado no puede ser vacío o nulo.");
        } else if (!existeCiudadOrigen(codigoCiudadOrigen)){
            return Retorno.error3("La ciudad de origen ingresada no existe en el sistema.");
        }

        return Retorno.ok();
    }

    @Override
    public Retorno viajeCostoMinimoMinutos(String codigoCiudadOrigen, String codigoCiudadDestino, TipoVueloPermitido tipoVueloPermitido) {
//        Si el camino pudo ser calculado exitosamente.
//                Retorna en valorEntero la cantidad total minutos del camino.
//        Retorna en valorString el camino desde la ciudad origen a la ciudad destino
//        incluidas.
        if (codigoCiudadOrigen == null || codigoCiudadOrigen.isEmpty() || codigoCiudadDestino == null || codigoCiudadDestino.isEmpty() || tipoVueloPermitido == null){
            return Retorno.error1("Los parámetros no pueden ser vacíos o nulos.");
        } else if (!existeCiudadOrigen(codigoCiudadOrigen)){
            return Retorno.error2("La ciudad de origen ingresada no existe en el sistema.");
        } else if (!existeCiudadDestino(codigoCiudadDestino)){
            return Retorno.error3("La ciudad de destino ingresada no existe en el sistema.");
        } else if (!existeCamino(codigoCiudadOrigen,codigoCiudadDestino)){
            return Retorno.error4("No existe un camino entre las ciudades de origen y destino ingresadas.");
        }

        return Retorno.ok();

    }

    @Override
    public Retorno viajeCostoMinimoDolares(String codigoCiudadOrigen, String codigoCiudadDestino, TipoVueloPermitido tipoVueloPermitido) {
        // : Retorna el camino más corto en dólares (monto acumulado más barato) que podría realizar un
        //viajero para ir de la ciudad origen a la ciudad destino.
        if (codigoCiudadOrigen == null || codigoCiudadOrigen.isEmpty() || codigoCiudadDestino == null || codigoCiudadDestino.isEmpty() || tipoVueloPermitido == null){
            return Retorno.error1("Los parámetros no pueden ser vacíos o nulos.");
        } else if (!existeCiudadOrigen(codigoCiudadOrigen)){
            return Retorno.error2("La ciudad de origen ingresada no existe en el sistema.");
        } else if (!existeCiudadDestino(codigoCiudadDestino)){
            return Retorno.error3("La ciudad de destino ingresada no existe en el sistema.");
        } else if (!existeCamino(codigoCiudadOrigen,codigoCiudadDestino)){
            return Retorno.error4("No existe un camino entre las ciudades de origen y destino ingresadas.");
        }

        return Retorno.ok();
    }

}
