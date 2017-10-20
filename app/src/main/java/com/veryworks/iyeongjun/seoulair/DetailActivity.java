package com.veryworks.iyeongjun.seoulair;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.veryworks.iyeongjun.seoulair.domain.DummyTermData;
import com.veryworks.iyeongjun.seoulair.domain.TermData;

import butterknife.BindView;
import butterknife.Unbinder;
import se.emilsjolander.flipview.FlipView;
import se.emilsjolander.flipview.OverFlipMode;

public class DetailActivity extends AppCompatActivity implements FlipView.OnFlipListener,FlipView.OnOverFlipListener{
    FlipAdapter adapter;
    TermData data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
    }


    @Override
    public void onFlippedToPage(FlipView v, int position, long id) {

    }

    @Override
    public void onOverFlip(FlipView v, OverFlipMode mode, boolean overFlippingPrevious, float overFlipDistance, float flipDistancePerPage) {

    }
}
