package com.example.android.movieapp.view;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.example.android.movieapp.R;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.pref_visualizer);
    }
}
