package com.veryworks.iyeongjun.seoulair;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.veryworks.iyeongjun.seoulair.domain.NewsData;

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

    private void setRecycler(){
        while (flag){
            if(NewsData.getInstance().getData() == null){
                try {
                    Thread.sleep(500);
                    Log.d("DATA","sleep");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                Log.d("DATA","getData");
                flag = false;
                RecyclerAdapter adapter = new RecyclerAdapter(NewsData.getInstance().getData(),getActivity());
                recycler.setAdapter(adapter);
                recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
            }
        }
    }
}
