package com.veryworks.iyeongjun.seoulair;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.veryworks.iyeongjun.seoulair.domain.Const;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import kr.go.seoul.airquality.AirQualityButtonTypeA;
import kr.go.seoul.airquality.AirQualityDetailTypeA;


public class APIFragment extends Fragment {

    @BindView(R.id.airQuality)
    AirQualityButtonTypeA airQuality;
    Unbinder unbinder;
    Context context;
    public APIFragment() {
        // Required empty public constructor
    }
    public void setContext(Context context){
        this.context = context;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_api, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    interface goAirQuiltyAPI{
        void goAirQuilty();
    }
}
