package com.example.eps;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;

public class SliderView extends SliderViewAdapter <SliderView.SliderHolder> {
    Context context;
    ArrayList<Integer> imageSliderModelList;
    OnSlideClickListener listener;
    int delimite ;

    public SliderView(Context context, ArrayList<Integer> imageSliderModelList) {
        this.context = context;
        this.imageSliderModelList = imageSliderModelList;
    }

    public SliderView(Context context, ArrayList<Integer> imageSliderModelList, OnSlideClickListener listener) {
        this.context = context;
        this.imageSliderModelList = imageSliderModelList;
        this.listener = listener;
    }

    public SliderView(Context context, ArrayList<Integer> imageSliderModelList, int delimite, OnSlideClickListener listener) {
        this.context = context;
        this.imageSliderModelList = imageSliderModelList;
        this.delimite = delimite;
        this.listener = listener;
    }

    @Override
    public SliderHolder onCreateViewHolder(ViewGroup parent) {
        View view= LayoutInflater.from(context).inflate(R.layout.activity_sliderview,null,false);
        return new SliderHolder(view);
    }

    @Override
    public void onBindViewHolder(SliderHolder viewHolder, int position) {
        if ( delimite == 1 ){
            viewHolder.sliderImageView.setImageResource(imageSliderModelList.get(position));
            viewHolder.sliderImageView.setTag(position);
        }
    }

    @Override
    public int getCount() {
        return imageSliderModelList.size();
    }

    class SliderHolder extends ViewHolder {
        ImageView sliderImageView;
        public SliderHolder(View itemView) {
            super(itemView);
            sliderImageView=itemView.findViewById(R.id.imageView) ;

            sliderImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        listener.onClickListener((Integer) sliderImageView.getTag());
                    }
                }
            });
        }
    }

    interface OnSlideClickListener{
        void onClickListener(int position);
    }

}


