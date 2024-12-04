package hmd.teatroABC.util;

import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Davy Lopes, Murilo Nunes, Hartur Sales
 * @date 04/12/2024
 * @brief Class FXMLLoaderUtil
 */

public class FXMLLoaderUtil {
    private static final Locale LOCALE = Locale.getDefault();
    public static final ResourceBundle BUNDLE = ResourceBundle.getBundle("teatro", LOCALE);

    public static FXMLLoader loadFXML(String fxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(FXMLLoaderUtil.class.getResource(fxmlPath), BUNDLE);
        loader.load();
        return loader;
    }
}
