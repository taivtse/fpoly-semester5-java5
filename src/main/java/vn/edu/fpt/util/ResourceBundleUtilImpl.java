package vn.edu.fpt.util;


import java.util.ResourceBundle;

public class ResourceBundleUtilImpl implements ResourceBundleUtil {
    private ResourceBundle resourceBundle;
    private static ResourceBundleUtilImpl RESOURCE_BUNDLE_UTIL = null;

    public static ResourceBundleUtilImpl getInstance() {
        if (RESOURCE_BUNDLE_UTIL == null) {
            RESOURCE_BUNDLE_UTIL = new ResourceBundleUtilImpl();
        }
        return RESOURCE_BUNDLE_UTIL;
    }

    public ResourceBundleUtilImpl setBundle(ResourceBundle bundle) {
        this.resourceBundle = bundle;
        return this;
    }

    @Override
    public String get(String key) {
        return this.resourceBundle.getString(key);
    }
}
