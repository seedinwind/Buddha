package org.buddha.wise.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Yuan Jiwei on 16/7/29.
 */
public class SharePreferencesUtil {
    private static SharePreferencesUtil sInstance;
    private static Context sAppContext;
    private SharedPreferences mSp;

    private SharePreferencesUtil(Context context) {
        mSp = context.getSharedPreferences("wise", Context.MODE_PRIVATE);
    }

    public static void init(Context context) {
        sAppContext = context.getApplicationContext();
    }

    public static SharePreferencesUtil getInstance() {
        if (sInstance == null) {
            sInstance = new SharePreferencesUtil(sAppContext);
        }
        return sInstance;
    }

    public void put(String key, String value) {
        SharedPreferences.Editor editor = mSp.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public void put(String key, boolean value) {
        SharedPreferences.Editor editor = mSp.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public void put(String key, int value) {
        SharedPreferences.Editor editor = mSp.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public String get(String key) {
        return mSp.getString(key, "");
    }

    public boolean getBoolean(String key) {
        return mSp.getBoolean(key, false);
    }

    public int getInt(String key) {
        return mSp.getInt(key, 0);
    }

    public void remove(String[] keys) {
        SharedPreferences.Editor editor = mSp.edit();
        for (String key : keys) {
            editor.remove(key);
        }
        editor.commit();
    }
}
