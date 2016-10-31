package lv.id.evil.mana_maja;

import android.os.Handler;
import android.os.Looper;




public class CyclicUpdate {
    // Create a Handler that uses the Main Looper to run in
    private Handler mHandler = new Handler(Looper.getMainLooper());

    private Runnable mStatusChecker;
    private int UPDATE_INTERVAL = 2000;

    /**
     * Creates an UIUpdater object, that can be used to
     * perform UIUpdates on a specified time interval.
     *
     * @param uiUpdater A runnable containing the update routine.
     */
    public CyclicUpdate(final Runnable uiUpdater) {
        mStatusChecker = new Runnable() {
            @Override
            public void run() {
                // Run the passed runnable
                uiUpdater.run();
                // Re-run it after the update interval
                mHandler.postDelayed(this, UPDATE_INTERVAL);
            }
        };
    }

    /**
     * The same as the default constructor, but specifying the
     * intended update interval.
     *
     * @param uiUpdater A runnable containing the update routine.
     * @param interval  The interval over which the routine
     *                  should run (milliseconds).
     */
    public CyclicUpdate(final Runnable uiUpdater, final int interval){
        mStatusChecker = new Runnable() {
            @Override
            public void run() {
                // Run the passed runnable
                uiUpdater.run();
                // Re-run it after the update interval
                mHandler.postDelayed(this, interval);
            }
        };
    }

    /**
     * Starts the periodical update routine (mStatusChecker
     * adds the callback to the handler).
     */
    public synchronized void startUpdates(){
        mStatusChecker.run();
    }

    /**
     * Stops the periodical update routine from running,
     * by removing the callback.
     */
    public synchronized void stopUpdates(){
        mHandler.removeCallbacks(mStatusChecker);
    }
}