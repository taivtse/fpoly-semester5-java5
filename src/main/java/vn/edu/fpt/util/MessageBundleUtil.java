package vn.edu.fpt.util;

import java.util.ResourceBundle;

public class MessageBundleUtil {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("message");

    public static String get(String key) {
        return resourceBundle.getString(key);
    }
}
