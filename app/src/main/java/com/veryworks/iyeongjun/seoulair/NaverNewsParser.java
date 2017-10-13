package com.veryworks.iyeongjun.seoulair;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.veryworks.iyeongjun.seoulair.domain.Const;
import com.veryworks.iyeongjun.seoulair.domain.Data;
import com.veryworks.iyeongjun.seoulair.domain.NewsData;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by iyeongjun on 2017. 10. 10..
 */

public class NaverNewsParser extends AsyncTask<Void, Void, Void> {
    private SetView context;
    @Override
    protected Void doInBackground(Void... voids) {
        String clientId = Const.Auth.NAVER_NEWS_API_ID;//애플리케이션 클라이언트 아이디값";
        String clientSecret = Const.Auth.NAVER_NEWS_API_PS;//애플리케이션 클라이언트 시크릿값";
        try {
            String text = URLEncoder.encode("naver", "UTF-8");
            String display = 100+"";
            String apiURL = "https://openapi.naver.com/v1/search/news.json?query="+ text
                    +"&display="+display;
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                Log.d("result",br.toString());
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                Log.d("result","404");
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            JSONtoPojoConvert(response.toString());
            Log.d("result",response+"");
            context.setView();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private void JSONtoPojoConvert(String str){
        NewsData.getInstance().setData(new Gson().fromJson(str,Data.class));
        Log.d("result",NewsData.getInstance().getData().getItems().toString());
    }

    public void setContext(Context context) {
        this.context = (SetView) context;
    }

    interface SetView{
        void setView();
    }
}
