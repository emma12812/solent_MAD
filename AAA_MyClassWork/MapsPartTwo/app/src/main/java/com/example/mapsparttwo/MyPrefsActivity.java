package com.example.mapsparttwo;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class MyPrefsActivity extends PreferenceActivity
{
    @SuppressWarnings("deprecation")
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

    }
}