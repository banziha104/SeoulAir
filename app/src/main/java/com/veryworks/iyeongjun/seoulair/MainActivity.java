package com.veryworks.iyeongjun.seoulair;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.veryworks.iyeongjun.seoulair.domain.Const;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.go.seoul.airquality.AirQualityButtonTypeA;
import kr.go.seoul.airquality.AirQualityDetailTypeA;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.airQuality) AirQualityButtonTypeA airQuality;

    boolean isRan = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        airQuality.setOpenAPIKey(Const.Auth.SEOUL_API_KEY);
        airQuality.setButtonImage(R.drawable.img00);
        new NaverNewsParser().execute();

        startShakeDetect();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isRan == false){
            Toast.makeText(this, "Resume", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, AirQualityDetailTypeA.class);
            intent.putExtra("OpenAPIKey", Const.Auth.SEOUL_API_KEY);
            isRan = true;
            startActivity(intent);
        }
    }

    private void startShakeDetect(){
        Intent intent = new Intent(MainActivity.this, ShakeDetectService.class);
        startService(intent);
    }


}
