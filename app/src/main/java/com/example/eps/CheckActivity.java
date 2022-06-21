package com.example.eps;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class CheckActivity extends AppCompatActivity {
    private TextView oti,otc;
    private int indexOfData;
    private LinearLayout checkContainer;
    private Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        oti = findViewById(R.id.oti);
        otc = findViewById(R.id.otc);
        ImageView checkBack = findViewById(R.id.check_back);
        checkBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        String[] gridNames = {"Bascketball","Handball","Volleyball","Gymnastique","Course de vitesse","Course en duree","Lancer de poids","Saut en longueur", "Football"};
        TextView title = findViewById(R.id.check_title);
        title.setText(String.format("%s -> %s", getIntent().getStringExtra(ListActivity.PATH), gridNames[getIntent().getIntExtra(ListActivity.INDEX_AT_GRID, -1)]));
        checkContainer = findViewById(R.id.check_linear);
        DataBase db = new DataBase(this);
        indexOfData = getIntent().getIntExtra(Splash.INDEX_AT_BTN,-1);
        ArrayList<Modules> modules = db.getAllModule();
        Modules m = modules.get(indexOfData);
        oti.setText(m.getOTI());
        ArrayList<MaterialCheckBox> checkBoxes = new ArrayList<>();
        String checkBoxesText =:q
    ::wq
:wq String[] slices = checkBoxesText.split("\n");
        for ( int i = 0 ; i < slices.length; i++){
            MaterialCheckBox checkBox = new MaterialCheckBox(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(5,5,5,5);
            checkBox.setLayoutParams(params);
            checkBox.setText(slices[i]);
            checkBoxes.add(checkBox);
            checkContainer.addView(checkBox);
            //if(i == slices.length-1){
                //next = new Button(this);
                //next.setText("Les Situations D'apprentissage");
               // next.setBackgroundResource(R.drawable.buttonmenu);
                //next.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                //checkContainer.addView(next);
            //}
        }
        switch (getIntent().getIntExtra(ListActivity.INDEX_AT_GRID,-1)){
            case 0:
            case 1:
            case 8:
                otc.setText(m.getBASKHOND());
                break;
            case 2:
                otc.setText(m.getVOLLEY());
                break;
            case 3:
                otc.setText(m.getGYM());
                break;
            case 4:
            case 5:
            case 6:
            case 7:
                otc.setText(m.getFOUR());
                break;
            default:
                Toast.makeText(this, "You have some kinds of problems", Toast.LENGTH_SHORT).show();
        }
        next=findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                int j = 0 ;
                for (int i=0 ; i < checkBoxes.size(); i++){
                    if (checkBoxes.get(i).isChecked()){
                        j++;
                    }
                }
                if (j ==1 || j == 2){
                    startActivity(new Intent(getBaseContext(),ListActivity.class).putExtra(MainActivity.LIST_ACTIVITY_KEY,3));
                } else {
                    Snackbar.make(next,"Svp veuillez selectioner 1 ou 2 compétences au maximum pour continuer!!",Snackbar.LENGTH_LONG).setAction("UNDO", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // No need to add action
                        }
                    }).setActionTextColor(R.color.white).show();
                }
            }
        });
    }
}