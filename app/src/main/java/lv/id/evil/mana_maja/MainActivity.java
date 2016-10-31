package lv.id.evil.mana_maja;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import Moka7.S7;
import Moka7.S7Client;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final PlcConnection PLC_Conn = new PlcConnection("evil.id.lv", 0, 2);

        final SharedPreferences ConnectionSettings = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        final String IpAddr = ConnectionSettings.getString("host_address", null);
        final int rack = Integer.parseInt(ConnectionSettings.getString("rack_number", "0"));
        final int slot = Integer.parseInt(ConnectionSettings.getString("slot_number", "2"));
        final boolean useTSAP = false;

        //final ProgressBar wait_cursor = (ProgressBar) findViewById(R.id.wait_cursor);
        //wait_cursor.setVisibility(View.INVISIBLE);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        final ToggleButton bt_kitchen_group1 = (ToggleButton) findViewById(R.id.button_kitchen_group1);
        final EditText editTextDebug = (EditText) findViewById(R.id.editTextDebug);
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        final ViewGroup root_view = (ViewGroup) ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);


//================================listeners==========================

        bt_kitchen_group1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Snackbar.make(buttonView, "Kitchen light group 1 is switched on", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                } else {
                    Snackbar.make(buttonView, "Kitchen light group 1 is switched off", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
            }
        });



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Connecting...", Snackbar.LENGTH_LONG).setAction("Action", null).show();

                //wait_cursor.setVisibility(View.VISIBLE);

                PLC_Conn.Connect();
                String Test = Integer.toString(PLC_Conn.Status);
                editTextDebug.setText(Test, TextView.BufferType.EDITABLE);

            }

        });


        //===========================cyclic operations============================
        Runnable uiUpdater = new Runnable() {
            @Override
            public void run() {
                Snackbar.make(root_view, "exec", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        };
        CyclicUpdate updater = new CyclicUpdate(uiUpdater, 10000);
        //CyclicUpdate(uiUpdater);

        updater.startUpdates();



    }












    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_exit) {
            System.exit(0);
            return true;
        }

        if (id == R.id.action_settings) {
            Intent LaunchSettings = new Intent(MainActivity.this, PreferenceWithHeaders.class);
            MainActivity.this.startActivity(LaunchSettings);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
