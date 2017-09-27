package com.veryworks.iyeongjun.seoulair;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.veryworks.iyeongjun.seoulair.domain.Const;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.go.seoul.airquality.AirQualityButtonTypeA;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.airQuality)
    AirQualityButtonTypeA airQuality;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        airQuality.setOpenAPIKey(Const.Auth.SEOUL_API_KEY);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "Resume", Toast.LENGTH_SHORT).show();
        airQuality.performClick();
    }
}
