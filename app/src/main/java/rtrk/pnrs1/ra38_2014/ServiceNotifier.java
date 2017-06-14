package rtrk.pnrs1.ra38_2014;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.RemoteException;
import android.util.Log;

/**
 * Created by student on 14.6.2017.
 */

public class ServiceNotifier extends AidlInterface.Stub {

    private NotificationManager mNotificationManager;
    private Notification.Builder mBuilder;
    private Context mContext;

    ServiceNotifier(Context context){
        mContext = context;
        mNotificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        mBuilder = new Notification.Builder(mContext)
                .setContentTitle("Task alert")
                .setSmallIcon(R.drawable.notification);
    }

    @Override
    public void notifyAdd() throws RemoteException {
        mBuilder.setContentText("Task added!");
        Log.d("Ovdjeee", "bratee");
        mNotificationManager.notify(1, mBuilder.build());
    }

    @Override
    public void notifyDelete() throws RemoteException {
        mBuilder.setContentText("Task deleted!");
        mNotificationManager.notify(1, mBuilder.build());
    }

    @Override
    public void notifyEdit() throws RemoteException {
        mBuilder.setContentText("Task edited!");
        mNotificationManager.notify(1, mBuilder.build());
    }
}
