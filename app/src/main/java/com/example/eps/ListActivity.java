package com.example.eps;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ListActivity extends AppCompatActivity implements ListRecyclerAdapter.OnAllShapeClickListener {

    private RecyclerView listRcv;
    private String root;
    private String item;
    private TextView title;
    private String moduleName;
    public static final String INDEX_AT_GRID = "index";
    public static final String PATH = "path";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listRcv = findViewById(R.id.list_rcv);
        title = findViewById(R.id.title);
        ImageView listBack = findViewById(R.id.list_back);
        listBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        root = getIntent().getStringExtra(MainActivity.TAB_NAME);

        int itemsImage[]= {R.drawable.basket1, R.drawable.handball2, R.drawable.volleyball3, R.drawable.gymnastique4, R.drawable.coursevitesse5, R.drawable.courseduree6, R.drawable.lancerdep7, R.drawable.sautlong8, R.drawable.foot};
        if (getIntent().getIntExtra(MainActivity.LIST_ACTIVITY_KEY,-1) == 0){
            item = getIntent().getStringExtra(MainActivity.ITEM_NAME);
            title.setTextSize(20);
            title.setText(root+" -> "+item);

            ArrayList<String> data = new ArrayList<>();
            data.add("الميثاق الوطني");
            data.add("التوجيهات التربوية 2009");
            data.add("التوجيهات التربوية 2007");
            data.add("قانون التربية البدنية");
            data.add("psem");
            data.add(" ");
            data.add("جذادة التحضير");
            data.add("اتفاقية الشراكة بين MJS & MEN");
            data.add("اتفاقية الضمان المدرسي");
            data.add("طلب الاستيداع");
            data.add("مدكرة الجمعية الرياضية (ASS)");
            data.add("Quelques Définitions en Eps");
            data.add("ميثاق الفصل");
            data.add("مختصرات Abréviations");
            data.add("Organigrame Bureau D'ASS");
            data.add("Inventaire Du Materiel");

            listRcv.setAdapter(new ListRecyclerAdapter(this,R.layout.item_first_shape,data,null,null,1));
            listRcv.setLayoutManager(new LinearLayoutManager(this));
            listRcv.setHasFixedSize(true);
        }else if (getIntent().getIntExtra(MainActivity.LIST_ACTIVITY_KEY,-1) == 1){
            item = getIntent().getStringExtra(MainActivity.ITEM_NAME);
            title.setText(root+" -> "+item);
            title.setTextSize(20);
            String itemsName[]= {"Basckeball à l'école", "Handball à l'école", "Volleyball à l'ecole", "Gymnastique à l'école", "Athlétisme à l'école", "L'entrainement en athlétisme", "Les bases d'entrainement aérobie", "Distionnair Sport"};

            ArrayList<ShapeModel> dataShape = new ArrayList<>();
            int j=0;
            for(int i =0 ; i <=16 ; i++){
                if((i+2) % 2 == 0){
                    dataShape.add(i,new ShapeModel("Analyse didactique","Réglement",null,itemsImage[j]));
                }else {
                    dataShape.add(i,new ShapeModel(null,null,itemsName[j],0));
                    j++;
                }
            }
            listRcv.setAdapter(new ListRecyclerAdapter(this,R.layout.item_first_shape,null,dataShape,null,2));
            listRcv.setLayoutManager(new LinearLayoutManager(this));
            listRcv.setHasFixedSize(true);
        } else if (getIntent().getIntExtra(MainActivity.LIST_ACTIVITY_KEY,-1) == 2){
            title.setTextSize(20);
            item = getIntent().getStringExtra(MainActivity.ITEM_NAME);
            moduleName = getIntent().getStringExtra(Splash.MODULE_NAME);
            title.setText(root+" -> "+item+" -> "+moduleName);
            //ArrayList<Integer> images = new ArrayList<>();
            //for (int i =0 ; i<= 7 ; i++){
                //images.add(R.drawable.mobile);
            //}
            listRcv.setAdapter(new ListRecyclerAdapter(this,R.layout.item_grid_shape,null,null,itemsImage,3));
            listRcv.setLayoutManager(new GridLayoutManager(this,2));
            listRcv.setHasFixedSize(true);
        } else {
            title.setText("Les Situations d'apprentisage");
            title.setTextSize(25);
            ArrayList<String> data = new ArrayList<>();
            for ( int i = 0 ; i < 20 ; i++){
                data.add("item"+i);
            }
            listRcv.setAdapter(new ListRecyclerAdapter(this,R.layout.item_first_shape,data,null,null,4));
            listRcv.setLayoutManager(new LinearLayoutManager(this));
            listRcv.setHasFixedSize(true);
        }

    }

    @Override
    public void onGridItemClick(int index) {
        Toast.makeText(this, ""+index, Toast.LENGTH_LONG).show();
        Intent i = new Intent(this,CheckActivity.class);
        i.putExtra(INDEX_AT_GRID,index);
        i.putExtra(PATH,root+" -> "+item+" -> "+moduleName);
        i.putExtra(Splash.INDEX_AT_BTN,getIntent().getIntExtra(Splash.INDEX_AT_BTN,-1));
        startActivity(i);
    }

    @Override
    public void onSecondFirstItemClick(String name ,int index) {
        Intent intent = new Intent(this,ShowDownloadActivity.class);
        String root = getIntent().getStringExtra(MainActivity.ITEM_NAME);
        intent.putExtra(PATH,root+"/"+name);
        startActivity(intent);
        // startActivity(new Intent(this,ShowDownloadActivity.class));
        //Toast.makeText(this, "On Second Shape from morfa9at idariya at "+index, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSecondSecondItemClick(String itemName) {
        Intent intent = new Intent(this,ShowDownloadActivity.class);
        String root = getIntent().getStringExtra(MainActivity.ITEM_NAME);
        intent.putExtra(PATH,root+"/"+itemName);
        startActivity(intent);
        //Toast.makeText(this, "On Second Shape from morfa9at tarbawiya at "+itemName, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onThirdShapeButtonClick(String btnName) {
        Intent intent = new Intent(this,ShowDownloadActivity.class);
        String root = getIntent().getStringExtra(MainActivity.ITEM_NAME);
        intent.putExtra(PATH,root+"/"+btnName);
        startActivity(intent);
        //Toast.makeText(this, "On Third Shape Button name : "+btnName, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFirstShapeButtonClick(String btnName,int index) {
        Intent intent = new Intent(this,ShowDownloadActivity.class);
        String root = getIntent().getStringExtra(MainActivity.ITEM_NAME);
        intent.putExtra(PATH,root+"/"+index+"/"+btnName);
        startActivity(intent);
        //Toast.makeText(this, "On first Shape Button name : "+btnName, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSecondSituationClick(int index) {
        Toast.makeText(this, "PDF AT : "+index, Toast.LENGTH_SHORT).show();
    }
}