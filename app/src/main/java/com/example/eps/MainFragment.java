package com.example.eps;

import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class MainFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    private static final String ARG_PARAM4 = "param4";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ArrayList<ShapeModel> mParam3;
    private ArrayList<Integer> param4;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance(String param1, String param2,ArrayList<ShapeModel> mParam3,ArrayList<Integer> param4) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putIntegerArrayList(ARG_PARAM4,param4);
        args.putSerializable(ARG_PARAM3,(Serializable) mParam3);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            mParam3 = (ArrayList<ShapeModel>) getArguments().getSerializable(ARG_PARAM3);
            param4 = getArguments().getIntegerArrayList(ARG_PARAM4);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        RecyclerView mainRv = v.findViewById(R.id.main_rcv);
        switch (mParam1){
            case "button1":
                mainRv.setAdapter(new FragmentAdapter(null,param4));
                break;
            case "button2":
                mainRv.setAdapter(new FragmentAdapter(mParam3,null));
                break;
            default:
                //
                break;
            //ArrayList<String> items = new ArrayList<>();
                //if ( mParam2 != null){
                        //items.add("المرفقات الادارية");
                        //items.add("مرفقات التربية البدنية");
               // }else {
                    //items = (ArrayList<String>) mParam3;
                //}
            /*case "module1":
                ArrayList<String> modules1 = new ArrayList<>();
                for(int i = 1 ; i <=8 ; i++){
                    modules1.add("moduleItem1"+i);
                }
                mainRv.setAdapter(new FragmentAdapter(modules1,null));
                break;
            case "module2":
                ArrayList <String> modules2 = new ArrayList<>();
                for (int i =1 ; i <= 8 ; i++){
                    modules2.add("moduleItem2"+i);
                }
                mainRv.setAdapter(new FragmentAdapter(modules2,null));*/
        }
        mainRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mainRv.setHasFixedSize(true);
        return v;
    }



    public class FragmentAdapter extends RecyclerView.Adapter<FragmentAdapter.MyViewHolder>{
        ArrayList<ShapeModel> module;
        ArrayList<Integer> gif ;

        public FragmentAdapter(ArrayList<ShapeModel> module, ArrayList<Integer> gif) {
            this.module = module;
            this.gif = gif;
        }

        @NonNull
        @Override
        public FragmentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = getLayoutInflater().inflate(R.layout.main_item,parent,false);
            return new MyViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull FragmentAdapter.MyViewHolder holder, int position) {
            if (gif != null  && gif.size() >= 1){
                holder.itemText.setVisibility(View.GONE);
                holder.gifView.setImageResource(gif.get(position));
                holder.gifView.setTag(position);
            }else/*( module != null  && module.size() >= 1)*/{
                holder.gifView.setVisibility(View.GONE);
                //Drawable d = Drawable.createFromPath(String.valueOf(R.drawable.sliderimage1));
                holder.itemText.setBackgroundResource(module.get(position).getImage());
                //holder.itemText.setText(module.get(position));
                holder.itemText.setTag(position);
            }
        }

        @Override
        public int getItemCount() {
            if ( gif != null){
                return gif.size();
            }else{
                return module.size();
            }
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            ImageView gifView;
            TextView itemText;
            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                gifView = itemView.findViewById(R.id.main_gif);
                itemText = itemView.findViewById(R.id.main_item);
                OnItemClickListener listener = (OnItemClickListener)getActivity();

                gifView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        OnGifClickListener listener = (OnGifClickListener) getActivity();
                        listener.onGifClick((int) gifView.getTag(),mParam2);
                    }
                });

                itemText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        OnItemClickListener listener = (OnItemClickListener)getActivity();
                        listener.onItemClick((String) module.get((int) itemText.getTag()).getItemName(),mParam2, (int) itemText.getTag());
                    }
                });
            }
        }
    }

    interface OnItemClickListener {
        void onItemClick(String itemName, String tabName, int position);
    }

    interface OnGifClickListener {
        void onGifClick(int index , String tabName);
    }
}