package com.siecolasystems.helloworldturbo.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.siecolasystems.helloworldturbo.R;

/**
 * Created by siecola on 5/14/16.
 */
public class WSUtil {

    private WSUtil(){}

    public static String getHostAddress (Context context) {
        String host;
        String baseAddress;

        int port;

        SharedPreferences sharedSettings =
                PreferenceManager.getDefaultSharedPreferences(context);
        host = sharedSettings.getString(
                context.getString(R.string.pref_ws_host),
                context.getString(R.string.pref_ws_default_host));
        port = sharedSettings.getInt(
                context.getString(R.string.pref_host_port),
                Integer.parseInt(context.getString(R.string.pref_ws_default_port)));

        if (host.endsWith("/")) {
            host = host.substring(0, host.length() - 1);
        }

        if (!host.startsWith("http://")) {
            host = "http://" + host;
        }

        baseAddress = host + ":" + port;

        return baseAddress;
    }
}