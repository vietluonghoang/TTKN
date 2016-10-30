package entities;

import android.content.Context;

import java.util.Map;

/**
 * Created by VietLH on 10/30/2016.
 */

public class LoaderAttributes {
    private static String targetUrl;
    private static Map<String, String> params;
    private static int method;
    private static Object targetClass;

    public static void initAttribute(String tarUrl, int med, Map<String, String> pams, Object tClass) {
        targetUrl = tarUrl;
        params = pams;
        method = med;
        targetClass = tClass;
    }

    public static String getTargetUrl() {
        return targetUrl;
    }

    public static Map<String, String> getParams() {
        return params;
    }

    public static int getMethod() {
        return method;
    }

    public static Object getTargetClass() {
        return targetClass;
    }
}
