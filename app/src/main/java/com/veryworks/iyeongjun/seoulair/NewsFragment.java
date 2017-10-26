package com.veryworks.iyeongjun.seoulair;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.veryworks.iyeongjun.seoulair.domain.NewsData;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class NewsFragment extends Fragment {
    Context context;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    Unbinder unbinder;
    boolean flag = true;

    public NewsFragment() {
        // Required empty public constructor

    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        unbinder = ButterKnife.bind(this, view);
        setRecycler();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void setRecycler() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (NewsData.getInstance().getData() != null) {
                    Log.d("DATA", "getData");
                    handler.obtainMessage(1).sendToTarget();
                } else {
                    Log.d("DATA", "no data");
                }
            }
        }, 500);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            RecyclerAdapter adapter = new RecyclerAdapter(NewsData.getInstance().getData(), getActivity());
            recycler.setAdapter(adapter);
            recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
    };
}


