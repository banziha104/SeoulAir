package com.veryworks.iyeongjun.seoulair;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import android.support.v7.app.AppCompatActivity;

import com.tsengvn.typekit.Typekit;
import com.tsengvn.typekit.TypekitContextWrapper;
import com.veryworks.iyeongjun.seoulair.domain.Const;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.go.seoul.airquality.AirQualityButtonTypeA;
import kr.go.seoul.airquality.AirQualityDetailTypeA;

public class MainActivity extends AppCompatActivity implements NaverNewsParser.SetView, APIFragment.GoAirQuiltyAPI {

    @BindView(R.id.airQuality) AirQualityButtonTypeA airQuality;
    @BindView(R.id.tabLayout) TabLayout tab;
    @BindView(R.id.viewpager) ViewPager pager;

    NaverNewsParser naverNewsParser;

    public static boolean isAirRan = false;
    public static boolean isShaked = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        setPager();
        naverNewsParser();
        Typekit.getInstance().addNormal(Typekit.createFromAsset(this, "BMDOHYEON_ttf.ttf"));
        airQuality.setOpenAPIKey(Const.Auth.SEOUL_API_KEY);
        airQuality.setButtonImage(R.drawable.img00);
        startShakeDetect();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(isAirRan == false) goAirQuilty();
        else if(isShaked == true){
            goAirQuilty();
            isShaked = false;
        }
    }

    private void startShakeDetect() {
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

    }
    public void setPager(){

        tab.addTab(tab.newTab().setText("One"));
        tab.addTab(tab.newTab().setText("Two"));
        tab.addTab(tab.newTab().setText("Three"));
        List<Fragment> datas = new ArrayList<>();

        WordFragment wordfragment = new WordFragment();
        NewsFragment newsFragment = new NewsFragment();
        APIFragment apiFragment = new APIFragment();

        apiFragment.setContext(this);

        datas.add(wordfragment);
        datas.add(newsFragment);
        datas.add(apiFragment);

        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(),datas);
        // 5. 아답터를 페이저 위젯에 연결
        pager.setAdapter(adapter);
        // 6. 페이저가 변경되었을 때 탭을 변경해주는 리스너
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));
        // 7. 탭이 변경되었때 페이저를 변경해주는 리스너
        tab.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(pager));
    }

    @Override
    public void goAirQuilty() {

        Intent intent = new Intent(this, AirQualityDetailTypeA.class);
        intent.putExtra("OpenAPIKey", Const.Auth.SEOUL_API_KEY);
        isAirRan = true;
        startActivity(intent);

    }
}
