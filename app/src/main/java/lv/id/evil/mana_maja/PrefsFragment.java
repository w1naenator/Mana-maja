package lv.id.evil.mana_maja;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceFragment;


@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class PrefsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String category = getArguments().getString("category");
        if (category != null) {
            if (category.equals(getString(R.string.connection_settings))) {
                addPreferencesFromResource(R.xml.connection_preferences);
            } else if (category.equals(getString(R.string.advanced_settings))) {
                addPreferencesFromResource(R.xml.advanced_preferences);
            }
        }
    }
}
