package com.veryworks.iyeongjun.seoulair;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTouch;
import butterknife.Unbinder;


public class APIFragment extends Fragment {

    Unbinder unbinder;
    GoAirQuiltyAPI context;
    @BindView(R.id.APIbutton)
    ImageButton APIbutton;

    public APIFragment() {
        // Required empty public constructor
    }

    public void setContext(Context context) {
        this.context = (GoAirQuiltyAPI) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_api, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @OnTouch(R.id.APIbutton)
    public boolean onClickedCardView(MotionEvent e, View v) {
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            APIbutton.setImageResource(R.drawable.touched_screen);
            APIbutton.setBackgroundColor(Color.argb(0,100,100,100));
        } else if (e.getAction() == MotionEvent.ACTION_UP) {
            context.goAirQuilty();
            APIbutton.setImageResource(R.drawable.touch_screen);
            APIbutton.setBackgroundColor(Color.argb(0,255,255,255));
        }
        return true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    interface GoAirQuiltyAPI {
        void goAirQuilty();
    }
}
