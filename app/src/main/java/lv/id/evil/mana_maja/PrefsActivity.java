package lv.id.evil.mana_maja;

import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;


public class PrefsActivity extends PreferenceActivity {
    private Toolbar mToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prepareLayout();

        buildLegacyPreferences();
    }

    private void prepareLayout() {
        ViewGroup root = (ViewGroup) findViewById(android.R.id.content);
        View content = root.getChildAt(0);
        LinearLayout toolbarContainer = (LinearLayout) View.inflate(this, R.layout.activity_prefs, null);

        root.removeAllViews();
        toolbarContainer.addView(content);
        root.addView(toolbarContainer);


        mToolBar = (Toolbar) toolbarContainer.findViewById(R.id.toolbar);
        mToolBar.setTitle(getTitle());
        mToolBar.setNavigationIcon(android.R.drawable.ic_menu_close_clear_cancel);
        mToolBar.setTitleTextColor( getResources().getColor(android.R.color.white));
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }

    private void buildLegacyPreferences() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            String action = getIntent().getAction();
            if (action == null) {
                addPreferencesFromResource(R.xml.headers_legacy);
            } else if (action.equals(getString(R.string.category_general))) {
                mToolBar.setTitle(getString(R.string.header_general));
                addPreferencesFromResource(R.xml.connection_preferences);
            } else if (action.equals(getString(R.string.category_advanced))) {
                mToolBar.setTitle(getString(R.string.header_general));
                addPreferencesFromResource(R.xml.advanced_preferences);
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onBuildHeaders(List<Header> target) {
        super.onBuildHeaders(target);
        loadHeadersFromResource(R.xml.headers, target);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected boolean isValidFragment(String fragmentName) {
        return fragmentName.equals(PrefsFragment.class.getName());
    }

    //final SharedPreferences appPreferences = (SharedPreferences) PreferenceManager.getDefaultSharedPreferences(getBaseContext());
    SharedPreferences.OnSharedPreferenceChangeListener spChanged = new
            SharedPreferences.OnSharedPreferenceChangeListener() {
                @Override
                public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
                                                      String key) {
                    if (key == "app_language"){
                        Toast.makeText(PrefsActivity.this, "Language changed", Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(PrefsActivity.this, "Language changed", Toast.LENGTH_SHORT).show();
                }
            };
}