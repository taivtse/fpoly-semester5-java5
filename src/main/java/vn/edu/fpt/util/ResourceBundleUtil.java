package vn.edu.fpt.util;

import java.util.ResourceBundle;

public interface ResourceBundleUtil {
    ResourceBundle COMMON_BUNDLE = ResourceBundle.getBundle("common");
    ResourceBundle MESSAGE_BUNDLE = ResourceBundle.getBundle("common");

    String get(String key);

    static ResourceBundleUtil getCommonBundle(){
        ResourceBundleUtilImpl.getInstance().setBundle(COMMON_BUNDLE);
        return (ResourceBundleUtil) ResourceBundleUtilImpl.getInstance();
    }
}
