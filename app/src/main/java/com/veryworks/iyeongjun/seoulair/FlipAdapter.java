package com.veryworks.iyeongjun.seoulair;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.veryworks.iyeongjun.seoulair.domain.TermData;
import com.veryworks.iyeongjun.seoulair.util.CustomBitmapPool;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by iyeongjun on 2017. 10. 11..
 */

public class FlipAdapter extends BaseAdapter {
    TermData data;
    Context context;

    public FlipAdapter(Context context, TermData data) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Object getItem(int i) {
        return data;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private LayoutInflater inflater;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FirstHolder firstHolder;
        SecondHolder secondHolder;
        if (position == 0) {
            convertView = inflater.inflate(R.layout.page_first, parent, false);
            firstHolder = new FirstHolder(convertView);
            firstHolder.firstTxtTitle.setText(data.getTitle());
            firstHolder.firstTxtContents.setText(data.getFirstContents());
            Glide.with(context)
                    .load(data.getImgSource()).bitmapTransform(new CropCircleTransformation(new CustomBitmapPool()))
                    .into(firstHolder.imageView);
        } else {
            convertView = inflater.inflate(R.layout.page, parent, false);
            secondHolder = new SecondHolder(convertView);
            secondHolder.txtContent.setText(data.getSecondContents());

        }
        return convertView;
    }

    class FirstHolder {
        @BindView(R.id.firstTxtTitle) TextView firstTxtTitle;
        @BindView(R.id.imageView) ImageView imageView;
        @BindView(R.id.firstTxtContents) TextView firstTxtContents;

        public FirstHolder(View view) {
            ButterKnife.bind(this, view);
        }

    }

    class SecondHolder {
        @BindView(R.id.txtContent) TextView txtContent;

        public SecondHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}