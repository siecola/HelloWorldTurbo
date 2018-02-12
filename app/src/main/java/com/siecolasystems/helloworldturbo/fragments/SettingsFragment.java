package com.siecolasystems.helloworldturbo.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.Log;

import com.siecolasystems.helloworldturbo.R;
import com.siecolasystems.helloworldturbo.webservice.WebServiceClient;

/**
 * Created by siecola on 5/14/16.
 */
public class SettingsFragment extends PreferenceFragment  {

    private static final String TAG = "SettingsFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.fragment_preferences);

        getActivity().setTitle("Configurações");


    }
}
