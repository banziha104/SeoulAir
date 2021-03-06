package com.veryworks.iyeongjun.seoulair;


import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.igalata.bubblepicker.BubblePickerListener;
import com.igalata.bubblepicker.adapter.BubblePickerAdapter;
import com.igalata.bubblepicker.model.BubbleGradient;
import com.igalata.bubblepicker.model.PickerItem;
import com.igalata.bubblepicker.rendering.BubblePicker;
import com.veryworks.iyeongjun.seoulair.domain.DummyTermData;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class WordFragment extends Fragment {
    TypedArray colors;
    @BindView(R.id.bubble)
    BubblePicker bubblePicker;
    Unbinder unbinder;

    public WordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_word, container, false);
        unbinder = ButterKnife.bind(this, view);
        colors = getResources().obtainTypedArray(R.array.colors);
        setBubble();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    private void setBubble() {
        bubblePicker.setBubbleSize(5);
        bubblePicker.setCenterImmediately(true);
        bubblePicker.setAdapter(new BubblePickerAdapter() {
            @Override
            public int getTotalCount() {
                return DummyTermData.getInstance().getData().size();
            }

            @NotNull
            @Override
            public PickerItem getItem(int position) {
                PickerItem item = new PickerItem();
                item.setTitle(DummyTermData.getInstance().getData().get(position).getTitle());
                item.setBackgroundImage(getResources().getDrawable(DummyTermData.getInstance().getData().get(position).getImgSource()));
                item.setGradient(new BubbleGradient(colors.getColor((position * 2) % 8, 0),
                        colors.getColor((position * 2) % 8 + 1, 0), BubbleGradient.VERTICAL));
                item.setTextColor(ContextCompat.getColor(getActivity(), android.R.color.white));
                return item;
            }
        });
        bubblePicker.setListener(new BubblePickerListener() {
            @Override
            public void onBubbleSelected(@NotNull PickerItem pickerItem) {

            }

            @Override
            public void onBubbleDeselected(@NotNull PickerItem pickerItem) {
                MainActivity activity = (MainActivity)getActivity();
                Intent intent = new Intent(activity,DetailActivity.class);
                intent.putExtra("pos",setPosition(pickerItem.getTitle()));
                startActivity(intent);
            }
        });
    }
    public int setPosition(String str){
        for(int i = 0; i < DummyTermData.getInstance().getData().size(); i++){
            if(DummyTermData.getInstance().getData().get(i).getTitle().equals(str)){
                return i;
            }
        }
        return 0;
    }
}
