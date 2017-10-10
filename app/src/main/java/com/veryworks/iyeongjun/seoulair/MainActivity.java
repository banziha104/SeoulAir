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

    @BindView(R.id.airQuality)
    AirQualityButtonTypeA airQuality;
    DisplayReceiver displayReceiver;
    boolean isRan = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        airQuality.setOpenAPIKey(Const.Auth.SEOUL_API_KEY);
        airQuality.setButtonImage(R.drawable.img00);
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

    public void googleNewsParser() {
        String clientId = Const.Auth.NAVER_NEWS_API_ID;//애플리케이션 클라이언트 아이디값";
        String clientSecret = Const.Auth.NAVER_NEWS_API_PS;//애플리케이션 클라이언트 시크릿값";
        try {
            String text = URLEncoder.encode("서울 대기", "UTF-8");
            String apiURL = "https://openapi.naver.com/v1/search/news?query="+ text; // json 결과=
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            System.out.println(response.toString());
            Log.d("result",inputLine);
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
