package org.buddha.wise.utils;

import android.os.Build;
import android.webkit.CookieManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Yuan Jiwei on 17/3/25.
 */

public class CookieUtil {
    public static void setCookie(String url, String key, String value) {
        CookieManager cookieMgr = CookieManager.getInstance();
        cookieMgr.setCookie(url, key + "=" + value);
    }

    public static void setCookie(String url, String cookie) {
        CookieManager cookieMgr = CookieManager.getInstance();
        cookieMgr.setCookie(url, cookie);
    }

    public static void setCookies(String url, HashMap<String, String> mCookies) {
        CookieManager cookieMgr = CookieManager.getInstance();
        Set<Map.Entry<String, String>> entry = mCookies.entrySet();
        for (Map.Entry<String, String> e : entry) {
            cookieMgr.setCookie(url, e.getKey() + "=" + e.getValue());
        }
    }

    public static void clearCookies() {
        CookieManager cookieMgr = CookieManager.getInstance();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            cookieMgr.removeAllCookie();
        } else {
            cookieMgr.removeAllCookies(null);
        }
    }
}
