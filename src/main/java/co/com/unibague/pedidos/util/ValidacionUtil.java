package co.com.unibague.pedidos.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidacionUtil
{
    private final static String CORREO_EXPRESIONES_REGULARES = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";

    public static boolean isCorreoValido(String correo) {
        Pattern pat = Pattern.compile(CORREO_EXPRESIONES_REGULARES);
        Matcher mat = pat.matcher(correo);
        return mat.matches();
    }
}
