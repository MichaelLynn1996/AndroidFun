package cn.mlynn.androidfun.module.setting;

import android.os.Bundle;

import androidx.navigation.Navigation;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import cn.mlynn.androidfun.R;

/**
 * Created by MichaelLynn on 2019/12/27 11:37
 * <p>
 * Email: sealynndev@gamil.com
 */
public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);

        Preference aboutUs = findPreference("about_us");
        if (aboutUs != null)
            aboutUs.setOnPreferenceClickListener(preference -> {
                Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate(R.id.about_us);
                return true;
            });
    }

}
