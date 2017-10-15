package com.veryworks.iyeongjun.seoulair;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.veryworks.iyeongjun.seoulair.domain.Data;
import com.veryworks.iyeongjun.seoulair.domain.Items;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by iyeongjun on 2017. 10. 11..
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    Items[] items;
    int MaxPostion = 40;
    public RecyclerAdapter(Data data) {
        items = data.getItems();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setTitle(items[position].getTitle());
    }

    @Override
    public int getItemCount() {
        return items.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.txtTitle) TextView itemTxtTitle;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
        public void setTitle(String str){
            itemTxtTitle.setText(str);
        }
    }
}
