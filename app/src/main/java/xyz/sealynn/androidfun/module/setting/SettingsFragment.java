package xyz.sealynn.androidfun.module.setting;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import xyz.sealynn.androidfun.R;

/**
 * Created by MichaelLynn on 2019/12/27 11:37
 * <p>
 * Email: sealynndev@gamil.com
 */
public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
    }
}
