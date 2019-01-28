package android.example.com.visualizerpreferences;

/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.PreferenceFragmentCompat;


public class SettingsFragment extends PreferenceFragmentCompat
        implements SharedPreferences.OnSharedPreferenceChangeListener
{

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {

        // Add visualizer preferences, defined in the XML file in res->xml->pref_visualizer
        addPreferencesFromResource(R.xml.pref_visualizer);
    
        setColorPreferenceSummary();
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }
    
    @Override
    public void onDestroy()
    {
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
        super.onDestroy();
    }
    
    private void setColorPreferenceSummary()
    {
        ListPreference prefColor = (ListPreference) findPreference(getString(R.string.pref_color_key));
        if (prefColor != null)
        {
            prefColor.setSummary(prefColor.getEntry());
        }
    }
    
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key)
    {
        if(key.equals(getString(R.string.pref_color_key)))
            setColorPreferenceSummary();
    }
}