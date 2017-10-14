package com.veryworks.iyeongjun.seoulair;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.veryworks.iyeongjun.seoulair.domain.Const;
import com.veryworks.iyeongjun.seoulair.domain.NewsData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.go.seoul.airquality.AirQualityButtonTypeA;
import kr.go.seoul.airquality.AirQualityDetailTypeA;

public class MainActivity extends AppCompatActivity implements NaverNewsParser.SetView {

    @BindView(R.id.airQuality) AirQualityButtonTypeA airQuality;
    @BindView(R.id.tabLayout) TabLayout tab;
    @BindView(R.id.viewpager) ViewPager pager;

    NaverNewsParser naverNewsParser;

    boolean isRan = false;

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
        if (isRan == false) {
            Intent intent = new Intent(this, AirQualityDetailTypeA.class);
            intent.putExtra("OpenAPIKey", Const.Auth.SEOUL_API_KEY);
            isRan = true;
            startActivity(intent);
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

        // 1. ViewPager 위젯 연결
        tab.addTab(tab.newTab().setText("One"));
        tab.addTab(tab.newTab().setText("Two"));
        tab.addTab(tab.newTab().setText("Three"));

        // 2. 프래그먼트들 생성
        APIFragment apiFragment = new APIFragment();
        WordFragment wordFragment = new WordFragment();
        NewsFragment newsFragment = new NewsFragment();

        // 3. 프래그먼트를 datas 저장소에 담은 후
        List<Fragment> datas = new ArrayList<>();
        // 4. 프래그먼트 매니저와 함께 아답터에 전달
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), datas);
        // 5. 아답터를 페이저 위젯에 연결
        pager.setAdapter(adapter);
        // 6. 페이저가 변경되었을 때 탭을 변경해주는 리스너
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));
        // 7. 탭이 변경되었때 페이저를 변경해주는 리스너
        tab.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(pager));

    }

}
