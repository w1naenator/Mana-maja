package lv.id.evil.mana_maja;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
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
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            //your codes here

        }


        final boolean useTSAP = false;
        final PlcConnection PLC_Conn = new PlcConnection();

        //final ProgressBar wait_cursor = (ProgressBar) findViewById(R.id.wait_cursor);
        //wait_cursor.setVisibility(View.INVISIBLE);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //final int i = 0;
        final int dimmer;
        final ToggleButton toggle_hall1 = (ToggleButton) findViewById(R.id.toggle_hall1);
        final ToggleButton toggle_kitchen1 = (ToggleButton) findViewById(R.id.toggle_kitchen1);
        final ToggleButton toggle_kidsroom1 = (ToggleButton) findViewById(R.id.toggle_kidsroom1);
        final ToggleButton toggle_guestroom1 = (ToggleButton) findViewById(R.id.toggle_guestroom1);
        final ToggleButton toggle_bedroom1 = (ToggleButton) findViewById(R.id.toggle_bedroom1);
        final ToggleButton toggle_wc1 = (ToggleButton) findViewById(R.id.toggle_wc1);
        final ToggleButton toggle_bathroom1 = (ToggleButton) findViewById(R.id.toggle_bathroom1);
        final SeekBar seek_dimmer=(SeekBar) findViewById(R.id.seek_dimmer);
        final SharedPreferences ConnectionSettings = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        final String host_address = ConnectionSettings.getString("host_address", null);
        final int rack_number = Integer.parseInt(ConnectionSettings.getString("rack_number", "0"));
        final int slot_number = Integer.parseInt(ConnectionSettings.getString("slot_number", "2"));
        final TextView TextView_Dimmer = (TextView) findViewById(R.id.TextView_Dimmer);
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        final ViewGroup root_view = (ViewGroup) ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);

        final int HALL1 = 832,
                  KITCHEN1 = 820,
                  KIDSROOM1 = 844,
                  GUESTROOM1 = 850,
                  BEDROOM1 = 950,
                  WC1 = 838,
                  BATHROOM1 = 826,
                  DIMMER = 944;

        //PLC_Conn.Connect(host_address, rack_number, slot_number);
//================================listeners==========================

        toggle_hall1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (PLC_Conn.isConnected){
                    PLC_Conn.Write(HALL1, isChecked);
                    if (PLC_Conn.Status == 0){
                        if (isChecked) {
                            Snackbar.make(buttonView, R.string.fab_hall_light_group1_is_switched_on, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                        } else {
                            Snackbar.make(buttonView, R.string.fab_hall_light_group1_is_switched_off, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                        }
                    }
                }
            }
        });

        toggle_kitchen1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (PLC_Conn.isConnected){
                    PLC_Conn.Write(KITCHEN1, isChecked);
                    if (PLC_Conn.Status == 0){
                        if (isChecked) {
                            Snackbar.make(buttonView, R.string.fab_kitchen_light_group1_is_switched_on, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                        } else {
                            Snackbar.make(buttonView, R.string.fab_kitchen_light_group1_is_switched_off, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                        }
                    }
                }
            }
        });


        toggle_kidsroom1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (PLC_Conn.isConnected){
                    PLC_Conn.Write(KIDSROOM1, isChecked);
                    if (PLC_Conn.Status == 0){
                        if (isChecked) {
                            Snackbar.make(buttonView, R.string.fab_kids_room_light_group1_is_switched_on, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                        } else {
                            Snackbar.make(buttonView, R.string.fab_kids_room_light_group_1_is_switched_off, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                        }
                    }
                }
            }
        });


        toggle_guestroom1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (PLC_Conn.isConnected){
                    PLC_Conn.Write(GUESTROOM1, isChecked);
                    if (PLC_Conn.Status == 0){
                        if (isChecked) {
                            Snackbar.make(buttonView, R.string.fab_guest_room_light_group1_is_switched_on, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                        } else {
                            Snackbar.make(buttonView, R.string.fab_guest_room_light_group1_is_switched_off, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                        }
                    }
                }
            }
        });

        toggle_bedroom1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (PLC_Conn.isConnected){
                    PLC_Conn.Write(BEDROOM1, isChecked);
                    if (PLC_Conn.Status == 0){
                        if (isChecked) {
                            Snackbar.make(buttonView, R.string.fab_bedroom_light_group1_is_switched_on, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                        } else {
                            Snackbar.make(buttonView, R.string.fab_bedroom_light_group1_is_switched_off, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                        }
                    }
                }
            }
        });

        toggle_wc1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (PLC_Conn.isConnected){
                    PLC_Conn.Write(WC1, isChecked);
                    if (PLC_Conn.Status == 0){
                        if (isChecked) {
                            Snackbar.make(buttonView, R.string.fab_wc_light_is_switched_on, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                        } else {
                            Snackbar.make(buttonView, R.string.fab_wc_light_is_switched_off, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                        }
                    }
                }
            }
        });

        toggle_bathroom1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (PLC_Conn.isConnected){
                    PLC_Conn.Write(BATHROOM1, isChecked);
                    if (PLC_Conn.Status == 0){
                        if (isChecked) {
                            Snackbar.make(buttonView, R.string.fab_bathroom_light_is_switched_on, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                        } else {
                            Snackbar.make(buttonView, R.string.fab_bathroom_light_is_switched_off, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                        }
                    }
                }
            }
        });

        seek_dimmer.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                // TODO Auto-generated method stub
                TextView_Dimmer.setText(String.valueOf(seek_dimmer.getProgress()));
                //seek_dimmer.setProgress(PLC_Conn.ReadInt(DIMMER));
                PLC_Conn.WriteInt(DIMMER,seek_dimmer.getProgress());
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (PLC_Conn.isConnected == false) {

                    Snackbar.make(view, R.string.fab_connecting, Snackbar.LENGTH_LONG).setAction("Action", null).show();

                    final SharedPreferences ConnectionSettings = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                    final String host_address = ConnectionSettings.getString("host_address", null);
                    final int rack_number = Integer.parseInt(ConnectionSettings.getString("rack_number", "0"));
                    final int slot_number = Integer.parseInt(ConnectionSettings.getString("slot_number", "2"));

                    PLC_Conn.Connect(host_address, rack_number, slot_number);

                    if (PLC_Conn.Status < 0) {
                        Snackbar.make(view, R.string.fab_system_error, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    }
                    else if (PLC_Conn.Status == 0) {
                        Snackbar.make(view, R.string.fab_connection_successful, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    }
                    else {
                        Snackbar.make(view, getString(R.string.fab_connection_failed)+" (e "+PLC_Conn.Status+")", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    }
                }
                else {
                    //editTextDebug.setText("Disconnecting");
                    PLC_Conn.Disconnect();
                    if (PLC_Conn.Status == 0) {
                        Snackbar.make(view, R.string.fab_disconnected, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    }
                }
            }
        });


        //===========================cyclic operations============================
        Runnable uiUpdater = new Runnable() {
            //@Override
            int i = 0;
            public void run() {
                boolean state = false;
                i++;
                //editTextDebug.setText(String.valueOf(i) + ", isConnected = " + String.valueOf(PLC_Conn.isConnected), TextView.BufferType.EDITABLE);
                if(PLC_Conn.isConnected == true) {
                    state = PLC_Conn.Read(HALL1);
                    if(PLC_Conn.Status == 0) {
                        toggle_hall1.setChecked(state);
                    }
                    state = PLC_Conn.Read(KITCHEN1);
                    if(PLC_Conn.Status == 0) {
                        toggle_kitchen1.setChecked(state);
                    }
                    state = PLC_Conn.Read(KIDSROOM1);
                    if(PLC_Conn.Status == 0) {
                        toggle_kidsroom1.setChecked(state);
                    }
                    state = PLC_Conn.Read(GUESTROOM1);
                    if(PLC_Conn.Status == 0) {
                        toggle_guestroom1.setChecked(state);
                    }
                    state = PLC_Conn.Read(BEDROOM1);
                    if(PLC_Conn.Status == 0) {
                        toggle_bedroom1.setChecked(state);
                    }
                    state = PLC_Conn.Read(WC1);
                    if(PLC_Conn.Status == 0) {
                        toggle_wc1.setChecked(state);
                    }
                    state = PLC_Conn.Read(BATHROOM1);
                    if(PLC_Conn.Status == 0) {
                        toggle_bathroom1.setChecked(state);
                    }

                    seek_dimmer.setProgress(PLC_Conn.ReadInt(DIMMER));
                    TextView_Dimmer.setText(String.valueOf(seek_dimmer.getProgress()));

                    fab.setImageResource(R.drawable.ic_action_disconnect);
                    fab.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff3399")));
                    toggle_hall1.setEnabled(true);
                    toggle_kitchen1.setEnabled(true);
                    toggle_kidsroom1.setEnabled(true);
                    toggle_guestroom1.setEnabled(true);
                    toggle_bedroom1.setEnabled(true);
                    toggle_wc1.setEnabled(true);
                    toggle_bathroom1.setEnabled(true);
                    seek_dimmer.setEnabled(true);
                }
                else{
                    fab.setImageResource(R.drawable.ic_action_connect);
                    fab.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#00ff99")));
                    toggle_hall1.setEnabled(false);
                    toggle_kitchen1.setEnabled(false);
                    toggle_kidsroom1.setEnabled(false);
                    toggle_guestroom1.setEnabled(false);
                    toggle_bedroom1.setEnabled(false);
                    toggle_wc1.setEnabled(false);
                    toggle_bathroom1.setEnabled(false);
                    seek_dimmer.setEnabled(false);
                }
            }
        };
        CyclicUpdate updater = new CyclicUpdate(uiUpdater, 500);
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
