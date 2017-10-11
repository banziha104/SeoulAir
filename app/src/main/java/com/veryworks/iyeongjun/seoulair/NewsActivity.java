package com.veryworks.iyeongjun.seoulair;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.veryworks.iyeongjun.seoulair.domain.Data;
import com.veryworks.iyeongjun.seoulair.domain.NewsData;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsActivity extends AppCompatActivity implements NaverNewsParser.SetView {
    NaverNewsParser naverNewsParser;
    @BindView(R.id.recycler) RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);
        naverNewsParser();
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
