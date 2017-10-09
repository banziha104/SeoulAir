package com.veryworks.iyeongjun.seoulair;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.veryworks.iyeongjun.seoulair.domain.Const;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.go.seoul.airquality.AirQualityButtonTypeA;
import kr.go.seoul.airquality.AirQualityDetailTypeA;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.airQuality)
    AirQualityButtonTypeA airQuality;
    DisplayReceiver displayReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        airQuality.setOpenAPIKey(Const.Auth.SEOUL_API_KEY);
        airQuality.setButtonImage(R.drawable.img00);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "Resume", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, AirQualityDetailTypeA.class);
        intent.putExtra("OpenAPIKey", Const.Auth.SEOUL_API_KEY);
        startActivity(intent);
    }

    private void startShakeDetect(){
        Intent intent = new Intent(MainActivity.this, ShakeDetectService.class);
        startService(intent);
    }
}
