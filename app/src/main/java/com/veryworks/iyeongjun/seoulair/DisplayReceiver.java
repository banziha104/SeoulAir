package com.veryworks.iyeongjun.seoulair;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.veryworks.iyeongjun.seoulair.domain.Const;

import static com.veryworks.iyeongjun.seoulair.StaticStatus.ScreenStatus;

public class DisplayReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        switch (intent.getAction()){
            case Intent.ACTION_SCREEN_ON:
                ScreenStatus = Const.Status.SCREEN_ON;
                Toast.makeText(context, "Screen On", Toast.LENGTH_SHORT).show();
                break;

            case Intent.ACTION_SCREEN_OFF:
                Toast.makeText(context, "Screen Off", Toast.LENGTH_SHORT).show();
                break;
            case Intent.ACTION_BOOT_COMPLETED:

                break;
        }
    }
}
