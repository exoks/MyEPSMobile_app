package com.example.eps;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;

public class ListRecyclerAdapter extends RecyclerView.Adapter<ListRecyclerAdapter.ListViewHolder> {
    private Context c;
    private int layout;
    private ArrayList<String> data;
    private int pos ;
    private ArrayList<ShapeModel> dataShape;
    private int[] images;
    private OnAllShapeClickListener listener;

    public ListRecyclerAdapter(Context c, int layout ,ArrayList<String> data, ArrayList<ShapeModel> dataShape,int[] images,int position) {
        this.c = c;
        this.layout = layout;
        this.data = data;
        this.pos = position;
        this.dataShape = dataShape;
        this.images = images;
    }



    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        listener = (OnAllShapeClickListener) c;
            View v = LayoutInflater.from(c).inflate(layout,parent,false);
        return new ListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        if(pos == 1){
            if (position == 5){
                holder.third.setVisibility(View.VISIBLE);
                holder.first.setVisibility(View.GONE);
                holder.second.setVisibility(View.GONE);
                holder.third.setTag(position);
            }else {
                holder.second.setVisibility(View.VISIBLE);
                holder.second.setText(data.get(position));
                holder.first.setVisibility(View.GONE);
                holder.third.setVisibility(View.GONE);
                // Sending position at first item morfakat idariya
                holder.second.setTag(position);
            }
        }else if (pos == 2){
            if ((position + 2) % 2 == 0){
                holder.first.setVisibility(View.VISIBLE);
                holder.firstBtn.setText(dataShape.get(position).getFirstBtnName());
                holder.secondBtn.setText(dataShape.get(position).getSecondBtnName());
                holder.view.setImageResource(dataShape.get(position).getImage());
                holder.second.setVisibility(View.GONE);
                holder.first.setTag(position);
            }else{
                holder.first.setVisibility(View.GONE);
                holder.second.setVisibility(View.VISIBLE);
                holder.second.setText(dataShape.get(position).getItemName());
                // sending position at second item morfakat tarbawiya
                holder.second.setTag(position);
            }
        }else if ( pos == 3){
            holder.view2.setImageResource(images[position]);
            holder.view2.setTag(position);
        }else {
            holder.first.setVisibility(View.GONE);
            holder.third.setVisibility(View.GONE);
            holder.second.setVisibility(View.VISIBLE);
            holder.second.setText(data.get(position));
            holder.second.setTag(position);
        }
    }

    @Override
    public int getItemCount() {
        if(data != null)
            return data.size();
        else if (dataShape != null)
            return dataShape.size();
        else {
            return images.length;
        }
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        LinearLayout first,third;
        TextView second;
        Button btn1,btn2,btn3,btn4,firstBtn,secondBtn;
        ImageView view,view2;
        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            second = itemView.findViewById(R.id.second_shape);
            first = itemView.findViewById(R.id.first_shape);
            third = itemView.findViewById(R.id.third_shape);
            view = itemView.findViewById(R.id.imageView2);
            if(pos == 1){
                btn1 = itemView.findViewById(R.id.btn1);
                btn2 = itemView.findViewById(R.id.btn2);
                btn3 = itemView.findViewById(R.id.btn3);
                btn4 = itemView.findViewById(R.id.btn4);
                second.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onSecondFirstItemClick((String) second.getText(),(int) second.getTag());
                    }
                });
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onThirdShapeButtonClick("اشعار بحادثة"+"/"+btn1.getText());
                    }
                });
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onThirdShapeButtonClick("اشعار بحادثة"+"/"+btn2.getText());
                    }
                });
                btn3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onThirdShapeButtonClick("اشعار بحادثة"+"/"+btn3.getText());
                    }
                });
                btn4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onThirdShapeButtonClick("اشعار بحادثة"+"/"+btn4.getText());
                    }
                });
            }else if( pos == 2){
                firstBtn = itemView.findViewById(R.id.first_btn);
                secondBtn = itemView.findViewById(R.id.second_btn);
                second.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onSecondSecondItemClick((String) second.getText());
                    }
                });
                firstBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onFirstShapeButtonClick((String) firstBtn.getText(), (Integer) first.getTag());
                    }
                });
                secondBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onFirstShapeButtonClick((String) secondBtn.getText(),(Integer) first.getTag());
                    }
                });
            }else if ( pos ==3 ){
                view2 = itemView.findViewById(R.id.view2);
                view2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onGridItemClick((int)view2.getTag());
                    }
                });
            }else {
                second.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.onSecondSituationClick((Integer) second.getTag());
                    }
                });
            }
        }
    }

    interface OnAllShapeClickListener {
        void onGridItemClick(int index);
        void onSecondFirstItemClick(String name, int index);
        void onSecondSecondItemClick(String itemName);
        void onThirdShapeButtonClick (String btnName);
        void onFirstShapeButtonClick (String btnName, int index);
        void onSecondSituationClick (int index);
    }
}
