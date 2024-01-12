/*The CronJob class is designed to automatically refresh the data after 10min
without the need for manual button clicks */


package API.Refresh;

import java.util.Timer;
import java.util.TimerTask;

public class CronJob {

    private InfoUpdater infoUpdater;
    private Refresher refresher;
    //Refresh every 10min
    private static final int REFRESH_RATE = 10 * 60 * 1000; 

    public CronJob(InfoUpdater infoUpdater, Refresher refresher) {
        this.infoUpdater = infoUpdater;
    }

    public void startAutomaticRefresh() {
        Timer timer = new Timer(true);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Perform automatic refresh
                infoUpdater.performRefresh();
                System.out.println("Data automatically refreshed.");
            }
        }, 0, REFRESH_RATE);
    }

    public static void main(String[] args) {
        // Create instances of InfoUpdater & Refresher
        InfoUpdater infoUpdater = new InfoUpdater();
        Refresher refresher = new Refresher(infoUpdater);

        // Create an instance of CronJob & start automatic refresh
        CronJob cronJob = new CronJob(infoUpdater, refresher);
        cronJob.startAutomaticRefresh();
    }

    
}
