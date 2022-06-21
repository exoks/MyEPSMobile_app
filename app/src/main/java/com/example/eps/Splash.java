package com.example.eps;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;



public class Splash extends AppCompatActivity {

    private Animation botanimation;
    private ImageView image;
    public static final String INDEX_AT_BTN = "indexAtBtn";
    public static final String TAB_REF = "collége";
    public static final String MODULE_NAME = "moduleName";
    private ImageView textView;
    private Animation topanimation;
    private int indexAtBtn1;
    private int indexAtBtn2;
    private String moduleName1;
    private String moduleName2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getIntent().getIntExtra(MainActivity.POSITION_AT_SPLASH,0) != 1){
            setContentView(R.layout.activity_splash);
            DataBase db = new DataBase(this);
            //Toast.makeText(this, "the count is : "+db.getCount(), Toast.LENGTH_SHORT).show();
            topanimation = AnimationUtils.loadAnimation(this, R.anim.animation_bottom);
            botanimation = AnimationUtils.loadAnimation(this, R.anim.animation_top);
            image = findViewById(R.id.imagejoker);
            textView = findViewById(R.id.textjoker);
            image.setAnimation(this.topanimation);
            textView.setAnimation(this.botanimation);

            (new Thread() {
                public void run() {
                    try {
                        sleep(4000);
                        Intent intent = new Intent(Splash.this.getApplicationContext(), SliderActivity.class);
                        startActivity(intent);
                        finish();
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                }
            }).start();
        }else {
            setContentView(R.layout.module_activity);
            Button module1 = findViewById(R.id.module_1);
            Button module2 = findViewById(R.id.module_2);
            ImageView moduleBack = findViewById(R.id.module_back);
            moduleBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
            String tapName = getIntent().getStringExtra(MainActivity.TAB_NAME);
            int pos = getIntent().getIntExtra(MainActivity.LIST_ACTIVITY_KEY,-1);
            switch (pos){
                case 0:
                    if(tapName.equals(TAB_REF)){
                        //module1.setText("moudle 1");
                        module1.setBackgroundResource(R.drawable.m1);
                        //module2.setText("moudle 2");
                        module2.setBackgroundResource(R.drawable.m2);
                        moduleName1 = "Module 1";
                        moduleName2 = "Module 2";

                        indexAtBtn1 = 0;
                        indexAtBtn2= 1;
                    }else {
                        //module1.setText("moudle 1");
                        module1.setBackgroundResource(R.drawable.m3);
                        //module2.setText("moudle 2");
                        module2.setBackgroundResource(R.drawable.m4);
                        moduleName1 = "Module 1";
                        moduleName2 = "Module 2";
                        indexAtBtn1 = 6;
                        indexAtBtn2= 7;
                    }
                    break;
                case 1:
                    if(tapName.equals(TAB_REF)){
                        //module1.setText("moudle 3");
                        module1.setBackgroundResource(R.drawable.m5);
                        //module2.setText("moudle 4");
                        module2.setBackgroundResource(R.drawable.m6);
                        moduleName1 = "Module 1";
                        moduleName2 = "Module 2";
                        indexAtBtn1 = 2;
                        indexAtBtn2= 3;
                    }else {
                        //module1.setText("moudle 3");
                        module1.setBackgroundResource(R.drawable.m1l);
                        //module2.setText("moudle 4");
                        module2.setBackgroundResource(R.drawable.m2l);
                        moduleName1 = "Module 1";
                        moduleName2 = "Module 2";
                        indexAtBtn1 = 8;
                        indexAtBtn2= 9;
                    }
                    break;
                case 2:
                    if(tapName.equals(TAB_REF)){
                        //module1.setText("moudle 1");
                        module1.setBackgroundResource(R.drawable.m3l);
                        //module2.setText("moudle 2");
                        module2.setBackgroundResource(R.drawable.m4l);
                        moduleName1 = "Module 1";
                        moduleName2 = "Module 2";
                        indexAtBtn1 = 4;
                        indexAtBtn2= 5;
                    }else {
                        //module1.setText("moudle 5");
                        module1.setBackgroundResource(R.drawable.m5l);
                        //module2.setText("moudle 6");
                        module2.setBackgroundResource(R.drawable.m6l);
                        moduleName1 = "Module 1";
                        moduleName2 = "Module 2";
                        indexAtBtn1 = 10;
                        indexAtBtn2= 11;
                    }
                    break;
                default:
                    Toast.makeText(this, "Empty Data", Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(this, "1 : "+indexAtBtn1+" 2 : "+indexAtBtn2, Toast.LENGTH_SHORT).show();
            module1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // we will move to new Activity with rvc
                    //if ( pos == 0 && tapName.equals("الاعدادي")) {
                        //indexAtBtn1 = 0;
                    //} else if (pos == 0 && tapName.equals("الاعدادي"))
                    moveToNext(2,indexAtBtn1,moduleName1);
                }
            });

            module2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // we will move to new Activity with rvc
                    moveToNext(2,indexAtBtn2,moduleName2);
                }
            });
        }
    }
    public void moveToNext(int value, int indexAtBtn,String moduleName){
        Intent i = new Intent(Splash.this,ListActivity.class);
        i.putExtra(MainActivity.LIST_ACTIVITY_KEY,value);
        i.putExtra(MainActivity.TAB_NAME,getIntent().getStringExtra(MainActivity.TAB_NAME));
        i.putExtra(MainActivity.ITEM_NAME,getIntent().getStringExtra(MainActivity.ITEM_NAME));
        i.putExtra(MODULE_NAME,moduleName);
        i.putExtra(INDEX_AT_BTN,indexAtBtn);
        startActivity(i);
    }

}

