package com.example.eps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;

public class VideoActivityList extends AppCompatActivity {

    public static final String VIDEO_OBJECT_KEY = "object";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);
        TextView page_title= findViewById(R.id.video_page_title);
        ImageView videoListBack = findViewById(R.id.videoList_back);
        videoListBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        String[] gif_module_itmes = {"Planification","Tice","Recherche Action","Gestion 1","Didactique","Athletisme","Science D'éducation","Planification",};
        RecyclerView listRcv = findViewById(R.id.video_rcv);
        ArrayList<VideoClassModel> videos = new ArrayList<>();
        // Catching the data from intent (coming from main activity)
        String gif_tab_name = getIntent().getStringExtra(MainActivity.GIF_TAB_NAME);
        int gif_index_item = getIntent().getIntExtra(MainActivity.GIF_INDEX_ITEM,-1) + 1;
        page_title.setText(gif_module_itmes[gif_index_item-1]);
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference().child(gif_tab_name+"/item"+gif_index_item);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot videoSnapshot : snapshot.getChildren()) {
                    VideoClassModel video = videoSnapshot.getValue(VideoClassModel.class);
                    videos.add(video);
                }
                listRcv.setLayoutManager(new LinearLayoutManager(VideoActivityList.this));
                listRcv.setAdapter(new VideoAdapter(getBaseContext(), videos, new VideoAdapter.OnVideoClickListener() {
                    @Override
                    public void onVideoClick(VideoClassModel video) {
                        Intent intent = new Intent(getBaseContext(),ShowVideoActivity.class);
                        Bundle b = new Bundle();
                        b.putSerializable(VIDEO_OBJECT_KEY,(Serializable) video);
                        intent.putExtras(b);
                        startActivity(intent);
                    }
                }));
                listRcv.setHasFixedSize(true);
                /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    listRcv.setOnCapturedPointerListener(new View.OnCapturedPointerListener() {
                        @Override
                        public boolean onCapturedPointer(View view, MotionEvent motionEvent) {
                            String path = (String) view.getTag();
                            Toast.makeText(VideoActivityList.this, "the path is "+path, Toast.LENGTH_SHORT).show();
                            adapter.onCreateViewHolder(findViewById(R.id.viewGroup),1).onPointer(path);
                            return true;
                        }
                    });
                }*/
                Toast.makeText(getBaseContext(), videos.get(0).getVideoPath(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getBaseContext(), "videos numbers are : "+videos.size(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getBaseContext(), "count for pariel1 :"+snapshot.getChildrenCount(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(VideoActivityList.this, "check your internet connection", Toast.LENGTH_SHORT).show();
            }
        });

    }


}