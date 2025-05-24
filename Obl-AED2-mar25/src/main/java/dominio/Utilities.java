package dominio;

import java.util.regex.Pattern;

public class Utilities {

    public static boolean validarCedula(String cedula){
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

    public static boolean validarCorreo(String correo){
        String patron = "^[^@]+@[^@]+$";
        return Pattern.matches(patron,correo);
    }
}
