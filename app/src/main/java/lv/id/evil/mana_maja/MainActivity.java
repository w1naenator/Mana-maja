package lv.id.evil.mana_maja;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import Moka7.S7;
import Moka7.S7Client;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        final ProgressBar wait_cursor = (ProgressBar) findViewById(R.id.wait_cursor);
        wait_cursor.setVisibility(View.INVISIBLE);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //preferences



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();

                wait_cursor.setVisibility(View.VISIBLE);
                PlcConnection p = new PlcConnection();
                p.read = true;
                new Thread(p).start();
            }

        });
    }

    private class PlcConnection implements Runnable{
        private final S7Client Client;
        public boolean read=false;
        public boolean write=false;

        public PlcConnection(){
            Client = new S7Client();
        }

        public void run() {
            final SharedPreferences ConnectionSettings = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

            String IpAddr = ConnectionSettings.getString("host_address", null);
            int rack = Integer.parseInt(ConnectionSettings.getString("rack_number", "0"));
            int slot = Integer.parseInt(ConnectionSettings.getString("slot_number", "2"));
            boolean useTSAP = true;
            int res = 0;
            View view = (View) findViewById(R.id.root_view);
            ProgressBar wait_cursor = (ProgressBar) findViewById(R.id.wait_cursor);
            //collect connection data
            try {
                //EditText t = (EditText) findViewById(R.id.editText_host);
                //IpAddr = t.getText().toString();


                //final SharedPreferences ConnectionPreferences = PreferredPreferences prefs = this.getSharedPreferences("general_settings", Context.MODE_PRIVATE);enceManager.getDefaultSharedPreferences(c);


                //wait_cursor.setVisibility(VISIBLE);
                Client.SetConnectionType(S7.OP);
                res = Client.ConnectTo(IpAddr, rack, slot);


            }catch (NumberFormatException e) {

                Snackbar.make(view, "System error!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }



            if (res==0){
                Snackbar.make(view, "Connected to " + IpAddr, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();


            }
            else {
                Snackbar.make(view, "Connection error: "+ S7Client.ErrorText(res), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }

        }
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
