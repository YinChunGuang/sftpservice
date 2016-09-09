package  socialengineer.utils;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by chunguang.yin on 9/8/2016.
 */
public class PropertiesUtils {

    public final static ResourceBundle rb = ResourceBundle.getBundle("check", Locale.getDefault());

    public static String getStringValue(String key){

       return rb.getString(key);

    }
    public static int getIntValue(String key){

        return Integer.parseInt(rb.getString(key));

    }


}
