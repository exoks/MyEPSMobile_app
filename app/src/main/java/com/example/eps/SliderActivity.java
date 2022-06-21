package com.example.eps;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;


import com.smarteist.autoimageslider.SliderAnimations;

import java.util.ArrayList;


public class SliderActivity extends AppCompatActivity {

    com.smarteist.autoimageslider.SliderView sliderView;
    com.smarteist.autoimageslider.SliderView sliderView1;
    ArrayList<Integer> imageSliderModelList;
    ArrayList<Integer> imageSliderModelList1;
    public static final String BUNDLE_KEY = "key";
    public static final String ROOT = "root";
    Animation btnanim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LayoutInflater.from(this).inflate(R.layout.activity_slider, null, false));

        // Inflating SliderView
        sliderView = findViewById(R.id.imageSlider);
        sliderView1 = findViewById(R.id.imageSlider1);
        // Buttons
        Button formation = findViewById(R.id.start);
        Button docs = findViewById(R.id.setting);
        Button share = findViewById(R.id.rate);
        this.btnanim = AnimationUtils.loadAnimation((Context) this, R.anim.btnanim);

        // Data for SliderView
        imageSliderModelList = new ArrayList<>();
        imageSliderModelList.add(R.drawable.sliderimage1);
        imageSliderModelList.add(R.drawable.sliderimage2);
        imageSliderModelList.add(R.drawable.sliderimage3);
        imageSliderModelList.add(R.drawable.sliderimage4);

        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(com.smarteist.autoimageslider.SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.WHITE);
        sliderView.setScrollTimeInSec(2);
        sliderView.setSliderAdapter(new SliderView(this, imageSliderModelList, 1, new SliderView.OnSlideClickListener() {
            @Override
            public void onClickListener(int position) {
                switch (position) {
                    case 0:
                        Intent j = new Intent(SliderActivity.this,MainActivity.class);
                        j.putExtra("TabsKey",2);
                        j.putExtra(ROOT,getString(R.string.btn2));
                        startActivity(j);
                        break;
                    case 1:
                        Intent d = new Intent(SliderActivity.this,MainActivity.class);
                        d.putExtra("TabsKey",2);
                        d.putExtra(ROOT,getString(R.string.btn2));
                        startActivity(d);
                        break;
                    case 2:
                        Intent i = new Intent(SliderActivity.this,Webview.class);
                        //i.putExtra("TabsKey",1);
                        //i.putExtra(ROOT,getString(R.string.btn1));
                        startActivity(i);
                        break;
                    case 3:
                        MyFragmentDialog dialog = MyFragmentDialog.newInstance(null,null);
                        dialog.show(getSupportFragmentManager(),null);
                        break;
                    default:
                        Toast.makeText(SliderActivity.this, "walu", Toast.LENGTH_SHORT).show();

                }
            }
        }));
        sliderView.startAutoCycle();
        sliderView1.setSliderAdapter(new SliderView(this, imageSliderModelList));
        sliderView1.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView1.setAutoCycleDirection(com.smarteist.autoimageslider.SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView1.setIndicatorSelectedColor(Color.RED);
        sliderView1.setIndicatorUnselectedColor(Color.GRAY);
        sliderView1.setScrollTimeInSec(2);
        sliderView1.startAutoCycle();

        // Formation Button
        formation.startAnimation(btnanim);
        formation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SliderActivity.this.startActivity(new Intent(SliderActivity.this.getApplicationContext(), Webview.class));
            }
        });

        // Docs Button
        docs.startAnimation(btnanim);
        docs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SliderActivity.this,MainActivity.class);
                i.putExtra(ROOT,getString(R.string.btn2));
                i.putExtra("TabsKey",2);
                startActivity(i);
            }
        });

        // Share Button
        share.startAnimation(btnanim);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyFragmentDialog dialog = MyFragmentDialog.newInstance(null,null);
                dialog.show(getSupportFragmentManager(),null);
            }
        });
    }

}
