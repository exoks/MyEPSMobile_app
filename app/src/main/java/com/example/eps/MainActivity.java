package com.example.eps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.slider.Slider;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainFragment.OnGifClickListener, MainFragment.OnItemClickListener {

    private ViewPager2 mainViewPager;
    private TextView title;
    private TabLayout tabLayout;
    public static final String TAB_NAME = "tabName";
    public static final String ITEM_NAME = "itemName";
    public static final String POSITION = "position";
    public static final String LIST_ACTIVITY_KEY = "TabsKey";
    public static final String POSITION_AT_SPLASH = "pos";
    public static final String GIF_TAB_NAME = "gifTabName";
    public static final String GIF_INDEX_ITEM = "gif_index_item";

    private ArrayList<ShapeModel> items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //  Inflating
        title= findViewById(R.id.main_tile);
        tabLayout = findViewById(R.id.main_tabLayout);
        mainViewPager = findViewById(R.id.main_viewPager);
        ImageView mainBack = findViewById(R.id.main_back);
        mainBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ArrayList<MainFragment> fragments = new ArrayList<>();
        String[] tabsNames1 = {"partiel1" , "partiel2"};
        String[] tabsNames2 = {"collége", "lycée" , "pièces jointes"};
        //String [] tabsNames3 = {"afterModule1","afterModule2"};

        // gif data for partiel 1
        ArrayList<Integer> gifs1 = new ArrayList<>();
        gifs1.add(R.drawable.planification1);
        gifs1.add(R.drawable.tice2);
        gifs1.add(R.drawable.basket3);
        gifs1.add(R.drawable.recher4);
        gifs1.add(R.drawable.gestion5);
        gifs1.add(R.drawable.didac6);
        gifs1.add(R.drawable.ath7);
        gifs1.add(R.drawable.sceduca8);
        // gif data for partiel 2
        ArrayList<Integer> gifs2 = new ArrayList<>();
        gifs2.add(R.drawable.sliderimage4);
        gifs2.add(R.drawable.sliderimage3);
        gifs2.add(R.drawable.sliderimage2);
        gifs2.add(R.drawable.sliderimage1);
        gifs2.add(R.drawable.sliderimage4);
        gifs2.add(R.drawable.sliderimage4);
        gifs2.add(R.drawable.sliderimage4);
        gifs2.add(R.drawable.sliderimage4);

        if(getIntent() != null){
            switch (getIntent().getIntExtra(LIST_ACTIVITY_KEY,0)){
                case 1:
                    title.setText(getIntent().getStringExtra(SliderActivity.ROOT));
                    fragments.add(MainFragment.newInstance("button1",tabsNames1[0],null,gifs1));
                    fragments.add(MainFragment.newInstance("button1",tabsNames1[1],null,gifs2));
                    mainViewPager.setAdapter(new MainViewPager(this,fragments));
                    TabLayoutMediator mediator1 = new TabLayoutMediator(tabLayout, mainViewPager, new TabLayoutMediator.TabConfigurationStrategy() {
                        @Override
                        public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                            tab.setText(tabsNames1[position]);
                        }
                    });
                    mediator1.attach();
                    break;
                case 2:
                    title.setText(getIntent().getStringExtra(SliderActivity.ROOT));
                    // Data for tanawi i3dadi
                    ArrayList<ShapeModel> pagerData1 = new ArrayList<>();
                    pagerData1.add(new ShapeModel("1AC",R.drawable.ac1));
                    pagerData1.add(new ShapeModel("2AC",R.drawable.ac2));
                    pagerData1.add(new ShapeModel("3AC",R.drawable.ac3));
                    // Data for Tanawi ta2hili
                    ArrayList<ShapeModel> pagerData2 = new ArrayList<>();
                    pagerData2.add(new ShapeModel("TC",R.drawable.tc));
                    pagerData2.add(new ShapeModel("1BAC",R.drawable.bac1));
                    pagerData2.add(new ShapeModel("2BAc",R.drawable.bac2));
                    // Data for morfa9at
                    items = new ArrayList<>();
                    items.add(new ShapeModel("pièces jointes Administratives",R.drawable.piecead));
                    items.add(new ShapeModel("pièces jointes EPS",R.drawable.pieceeps));

                    fragments.add(MainFragment.newInstance("button2",tabsNames2[0],pagerData1,null));
                    fragments.add(MainFragment.newInstance("button2",tabsNames2[1],pagerData2,null));
                    fragments.add(MainFragment.newInstance("button2",tabsNames2[2],items,null));
                    mainViewPager.setAdapter(new MainViewPager(this,fragments));
                    TabLayoutMediator mediator2 = new TabLayoutMediator(tabLayout, mainViewPager, new TabLayoutMediator.TabConfigurationStrategy() {
                        @Override
                        public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                            tab.setText(tabsNames2[position]);
                            tab.setContentDescription(tabsNames2[position]);
                        }
                    });
                    mediator2.attach();
                    break;
                /*case 3:
                    fragments.add(MainFragment.newInstance("module1",null,null));
                    fragments.add(MainFragment.newInstance("module1",null,null));
                    mainViewPager.setAdapter(new MainViewPager(this,fragments));
                    TabLayoutMediator mediator3 = new TabLayoutMediator(tabLayout, mainViewPager, new TabLayoutMediator.TabConfigurationStrategy() {
                        @Override
                        public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                            tab.setText(tabsNames3[position]);
                            tab.setContentDescription(tabsNames3[position]);
                        }
                    });
                    mediator3.attach();
                    break;
                case 4:
                    fragments.add(MainFragment.newInstance("module2",null,null));
                    fragments.add(MainFragment.newInstance("module2",null,null));
                    mainViewPager.setAdapter(new MainViewPager(this,fragments));
                    TabLayoutMediator mediator4 = new TabLayoutMediator(tabLayout, mainViewPager, new TabLayoutMediator.TabConfigurationStrategy() {
                        @Override
                        public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                            tab.setText(tabsNames3[position]);
                            /tab.setContentDescription(tabsNames3[position]);
                        //}
                    //});
                    //mediator4.attach();
                    //break;**/
            }

        }

    }

    @Override
    public void onItemClick(String itemsName,String tabName, int position) {
        if ( itemsName.equals(items.get(0).getItemName()) || itemsName.equals(items.get(1).getItemName())){
            moveToNext(tabName,itemsName,position,ListActivity.class);
        }else {
            moveToNext(tabName,itemsName,position,Splash.class);
            //Bundle b = new Bundle();
            //b.putString(TAB_NAME,tabName);
            //b.putString("name",itemsName);
            //b.putInt("position",position);startActivity(new Intent(this, Splash.class).putExtra("TabsKey", 1).putExtras(b));
        }
    }

    @Override
    public void onGifClick(int index,String tabName) {
        Intent in = new Intent(this,VideoActivityList.class);
        in.putExtra(GIF_TAB_NAME,tabName);;
        in.putExtra(GIF_INDEX_ITEM,index);
        startActivity(in);
    }
    public void moveToNext(String tabName, String itemName,int position , Class<?> cls){
        Intent in = new Intent(this,cls);
        in.putExtra(TAB_NAME,tabName);
        in.putExtra(ITEM_NAME,itemName);
        in.putExtra(LIST_ACTIVITY_KEY, position);
        in.putExtra(POSITION_AT_SPLASH, 1);
        startActivity(in);
    }
}