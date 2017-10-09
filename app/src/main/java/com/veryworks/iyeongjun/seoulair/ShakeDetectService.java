package com.veryworks.iyeongjun.seoulair;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class ShakeDetectService extends Service {
    public ShakeDetectService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
