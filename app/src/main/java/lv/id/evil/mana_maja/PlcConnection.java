package lv.id.evil.mana_maja;

import Moka7.S7;
import Moka7.S7Client;

public class PlcConnection {
    private S7Client Client;
    private Runnable ConnectRunnable;
    private Runnable ReadRunnable;
    private Runnable WriteRunnable;
    public boolean read = false;
    public boolean write = false;
    public boolean isConnected = false;
    public int Status = 0;
    public String Host;
    public byte[] buffer;
    public String host = "evil.id.lv";
    public  int rack = 0;
    public int slot = 2;
    public int db_offset = 0;



    public PlcConnection() {
        Client = new S7Client();
        ConnectRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Host = host;
                    Client.SetConnectionType(S7.OP);
                    Status = Client.ConnectTo(host, rack, slot);
                } catch (NumberFormatException e) {
                    Status = -1;
                }
                if (Status == 0){
                    isConnected = true;
                }
                else{
                    isConnected = false;
                }
            }
        };
    //}

    //public PlcConnection() {
        ReadRunnable = new Runnable() {
            @Override
            public void run() {

                try {

                    buffer = new byte[1];
                    Status = Client.ReadArea(S7.S7AreaDB,1,db_offset,1,buffer);


                } catch (NumberFormatException e) {
                    Status = -1;
                }
            }
        };

        WriteRunnable = new Runnable() {
            @Override
            public void run() {

                try {


                    Status = Client.WriteArea(S7.S7AreaDB,1,db_offset,1,buffer);


                } catch (NumberFormatException e) {
                    Status = -1;
                }
            }
        };
    }

    public synchronized void Connect(final String new_host, final int new_rack, final int new_slot) {
        host = new_host;
        rack = new_rack;
        slot = new_slot;
        slot = new_slot;
        ConnectRunnable.run();

    }
    public synchronized Boolean Read(final int Offset) {
        db_offset = Offset;
        ReadRunnable.run();
        if (buffer[0] == 0){
            return false;
        }else{
            return true;
        }
    }

    public synchronized void Write(final int Offset, final boolean sw_state) {
        db_offset = Offset;
        if (sw_state == true){
            buffer[0] = 1;
        }else{
            buffer[0] = 0;
        }
        WriteRunnable.run();
    }
}