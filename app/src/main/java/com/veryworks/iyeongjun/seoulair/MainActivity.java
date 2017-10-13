package com.veryworks.iyeongjun.seoulair;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.veryworks.iyeongjun.seoulair.domain.Const;
import com.veryworks.iyeongjun.seoulair.domain.NewsData;


import butterknife.BindView;
import butterknife.ButterKnife;
import kr.go.seoul.airquality.AirQualityButtonTypeA;
import kr.go.seoul.airquality.AirQualityDetailTypeA;

public class MainActivity extends AppCompatActivity implements NaverNewsParser.SetView{

    @BindView(R.id.recycler) RecyclerView recycler;
    @BindView(R.id.airQuality) AirQualityButtonTypeA airQuality;

    boolean isRan = false;

    NaverNewsParser naverNewsParser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        naverNewsParser();
        airQuality.setOpenAPIKey(Const.Auth.SEOUL_API_KEY);
        airQuality.setButtonImage(R.drawable.img00);

        startShakeDetect();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isRan == false){
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
    private void naverNewsParser() {
        naverNewsParser = new NaverNewsParser();
        naverNewsParser.setContext(this);
        naverNewsParser.execute();
    }

    @Override
    public void setView() {
        recycler.setAdapter(new RecyclerAdapter(NewsData.getInstance().getData()));
        recycler.setLayoutManager(new LinearLayoutManager(this));
    }
}
