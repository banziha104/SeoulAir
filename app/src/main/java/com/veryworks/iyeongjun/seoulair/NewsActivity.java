package com.veryworks.iyeongjun.seoulair;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class NewsActivity extends AppCompatActivity implements NaverNewsParser.SetView {
    NaverNewsParser naverNewsParser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        naverNewsParser();
    }

    private void naverNewsParser(){
        naverNewsParser = new NaverNewsParser();
        naverNewsParser.setContext(this);
        naverNewsParser.execute();
    }
    @Override
    public void setView() {
        Log.d("result","NewsActivity");
    }

}
