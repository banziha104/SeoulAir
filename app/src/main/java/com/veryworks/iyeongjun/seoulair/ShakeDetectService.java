package com.veryworks.iyeongjun.seoulair;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.squareup.seismic.ShakeDetector;

import static com.veryworks.iyeongjun.seoulair.MainActivity.isShaked;
import static com.veryworks.iyeongjun.seoulair.StaticStatus.isServiceRan;

public class ShakeDetectService extends Service implements DisplayReceiver.ShakeController, ShakeDetector.Listener{
    DisplayReceiver displayReceiver;
    SensorManager sensorManager;
    ShakeDetector sd;
    boolean isShakedDetect = false;
    private final String TAG = "Service";
    public ShakeDetectService() {

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        addDisplayReceiver();
        Log.d(TAG,"ServiceCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent == null) addDisplayReceiver();
        startForeground(1,new Notification());
        isServiceRan = true;
        Log.d(TAG,"ServiceOnStartCommand");
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.d(TAG,"ServiceOnDestroy");
        isServiceRan = false;
        super.onDestroy();
    }

    private void addDisplayReceiver(){
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        displayReceiver = new DisplayReceiver();
        displayReceiver.setShakeServiceContext(this);
        registerReceiver(displayReceiver, filter);
        startShakeListener();
        Log.d(TAG,"BroadCastReceiverStart");
    }

    @Override
    public void onShakeDetector() {
        startShakeListener();
    }

    @Override
    public void offShakeDetector() {
        stopShakeListener();
    }

    @Override
    public void hearShake() {
        startMainActivity();
    }

    private void startShakeListener(){
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sd = new ShakeDetector(this);
        sd.start(sensorManager);
        Log.d(TAG,"onShakeDetector");
    }
    private void stopShakeListener(){
        sd.stop();
        Log.d(TAG,"offShakeDetector");
    }

    private void startMainActivity(){
        if(isShakedDetect == false) {
            Log.d(TAG, "Shake Detected");
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            isShaked = true;
            isShakedDetect = true;
            startActivity(intent);
        }else{
            try {
                Thread.sleep(1000);
                isShakedDetect = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
