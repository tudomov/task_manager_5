package rtrk.pnrs1.ra38_2014;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Lana on 14.6.2017..
 */

public class NotificationService extends Service {
    private CheckerThread mCheckerThread;
    private ServiceNotifier mServiceNotifier;

    @Override
    public void onCreate() {
        mServiceNotifier = new ServiceNotifier(this);
        mCheckerThread = new CheckerThread(this);
        mCheckerThread.start();
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        mCheckerThread.exit();
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mServiceNotifier;
    }
}
