package com.veryworks.iyeongjun.seoulair;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.veryworks.iyeongjun.seoulair.domain.Data;
import com.veryworks.iyeongjun.seoulair.domain.Items;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;

/**
 * Created by iyeongjun on 2017. 10. 11..
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    Items[] items;
    Context context;
    public RecyclerAdapter(Data data, Context context) {
        items = data.getItems();
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setTitle(items[position].getTitle());
        holder.setPostion(position);
    }

    @Override
    public int getItemCount() {
        return items.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtTitle) TextView itemTxtTitle;
        private int postion;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemTxtTitle.setTextColor(Color.BLACK);
        }

        public void setTitle(String str) {
            itemTxtTitle.setText("  " + Html.fromHtml(str));
        }

        @OnTouch(R.id.txtTitle)
        public boolean onClickedCardView(MotionEvent e, View v) {
            if(e.getAction() == MotionEvent.ACTION_DOWN){
                itemTxtTitle.setTextColor(Color.GRAY);
            }else if (e.getAction() == MotionEvent.ACTION_UP){
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(items[postion].getOriginallink()));
                context.startActivity(intent);
                itemTxtTitle.setTextColor(Color.BLACK);
            }
            return true;
        }

        public void setPostion(int postion) {
            this.postion = postion;
        }
    }
}
