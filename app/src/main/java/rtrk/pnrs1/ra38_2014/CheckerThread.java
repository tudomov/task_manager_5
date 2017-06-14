package rtrk.pnrs1.ra38_2014;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by student on 14.6.2017.
 */

public class CheckerThread extends Thread{

    private boolean mRun;
    private long PERIOD = 5000;
    private SimpleDateFormat format;
    private Context mContext;
    private NotificationManager mNotificationManager;
    private Notification.Builder mBuilder;
    private TaskDBHelper mDBHelper;
    private Uri notificationSound;

    CheckerThread(Context context){
        super();
        mContext = context;
        format = new SimpleDateFormat("dd.MM.yyyy.HH:mm");
        mNotificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        mDBHelper = new TaskDBHelper(mContext);


    }

    @Override
    public synchronized void start() {
        mRun = true;
        super.start();
    }

    public synchronized void exit() {
        mRun = false;
    }

    @Override
    public void run() {
        while(mRun){
            String msg = "Tasks to be finished in 15 minutes: ";
            boolean notiHasItems=false;
            boolean notify = false;
            for(Task t:mDBHelper.readTasks()){
                Calendar current = Calendar.getInstance();
                Calendar taskTime = Calendar.getInstance();
                taskTime.set(t.getGodina(), t.getMjesec(), t.getDan(), t.getSat(), t.getMinut());

                Log.d("Sat i minut", ""+t.getDan()+" "+t.getMinut());

                if(current.get(Calendar.YEAR) == taskTime.get(Calendar.YEAR) &&
                        current.get(Calendar.MONTH) == taskTime.get(Calendar.MONTH) &&
                        current.get(Calendar.DAY_OF_MONTH) == taskTime.get(Calendar.DAY_OF_MONTH) &&
                        t.ismRadioButton() == true && t.mCheckBox == false){

                    if(current.get(Calendar.HOUR_OF_DAY) == current.get(Calendar.HOUR_OF_DAY)){
                        if(taskTime.get(Calendar.MINUTE ) - current.get(Calendar.MINUTE)<= 15 && taskTime.get(Calendar.MINUTE) - current.get(Calendar.MINUTE)>=0){
                            if (notiHasItems)
                                msg += " , " + t.getmText1();
                            else
                                msg += t.getmText1();
                            notiHasItems = true;
                        }
                        }else if(taskTime.get(Calendar.HOUR_OF_DAY) - current.get(Calendar.HOUR_OF_DAY) == 1){
                        if(taskTime.get(Calendar.MINUTE)+60 - current.get(Calendar.MINUTE) <= 15 && taskTime.get(Calendar.MINUTE)+60 - current.get(Calendar.MINUTE) >= 0){
                            if (notiHasItems)
                                msg += " , " + t.getmText1();
                            else
                                msg += t.getmText1();
                            notiHasItems = true;
                        }
                    }


                }
            }

            if(notiHasItems){
                mBuilder = new Notification.Builder(mContext)
                        .setContentTitle("Task remainder")
                        .setSmallIcon(R.drawable.notification)
                        .setContentText(msg);
                mNotificationManager.notify(0,mBuilder.build());
            }
            try{
                sleep(PERIOD);
            }catch (InterruptedException e){
                e.printStackTrace();
            }



        }
    }
}
