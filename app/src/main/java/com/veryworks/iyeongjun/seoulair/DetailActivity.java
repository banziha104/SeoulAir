package com.veryworks.iyeongjun.seoulair;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tsengvn.typekit.Typekit;
import com.tsengvn.typekit.TypekitContextWrapper;
import com.veryworks.iyeongjun.seoulair.domain.DummyTermData;
import com.veryworks.iyeongjun.seoulair.domain.TermData;

import butterknife.BindView;
import butterknife.ButterKnife;
import se.emilsjolander.flipview.FlipView;
import se.emilsjolander.flipview.OverFlipMode;

public class DetailActivity extends AppCompatActivity implements FlipView.OnFlipListener, FlipView.OnOverFlipListener {
    FlipAdapter adapter;
    @BindView(R.id.flipview)
    FlipView flipview;
    int curPos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        Typekit.getInstance().addNormal(Typekit.createFromAsset(this, "BMDOHYEON_ttf.ttf"));
        Intent intent = getIntent();
        curPos = intent.getIntExtra("pos",0);
        adapter = new FlipAdapter(this, DummyTermData.getInstance().getData().get(curPos));
        flipview.setAdapter(adapter);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }


    @Override
    public void onFlippedToPage(FlipView v, int position, long id) {

    }

    @Override
    public void onOverFlip(FlipView v, OverFlipMode mode, boolean overFlippingPrevious, float overFlipDistance, float flipDistancePerPage) {

    }
}
