package lv.id.evil.mana_maja;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.view.View;

import Moka7.S7;
import Moka7.S7Client;

public class PlcConnection {
    private S7Client Client;
    private Runnable mStatusChecker;
    public boolean read = false;
    public boolean write = false;
    String IpAddr = "192.168.1.10";
    //int rack = 0;
    //int slot = 2;
    public int Status = 0;
    boolean useTSAP = false;

    public PlcConnection(final String IpAddr, final int rack, final int slot) {
        Client = new S7Client();
        mStatusChecker = new Runnable() {
            @Override
            public void run() {

                try {
                    Client.SetConnectionType(S7.OP);
                    Status = Client.ConnectTo(IpAddr, rack, slot);
                } catch (NumberFormatException e) {
                    Status = 100;
                }
            }
        };
    }

    public synchronized void Connect() {
        mStatusChecker.run();
    }
}