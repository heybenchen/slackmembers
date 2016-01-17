package bschen.slackprofile.utils;

import android.content.Context;
import android.content.SharedPreferences;

public final class DataUtils {

    public static void cacheJson(final Context context, final String key, final String jsonString) {
        final String appName = context.getApplicationInfo().name;
        final SharedPreferences mPrefs =
                context.getSharedPreferences(appName, Context.MODE_PRIVATE);
        final SharedPreferences.Editor ed = mPrefs.edit();
        ed.putString(key, jsonString);
        ed.apply();
    }

    public static String restoreJson(final Context context, final String key) {
        final String appName = context.getApplicationInfo().name;
        final SharedPreferences mPrefs =
                context.getSharedPreferences(appName, Context.MODE_PRIVATE);
        return mPrefs.getString(key, "");
    }

}
