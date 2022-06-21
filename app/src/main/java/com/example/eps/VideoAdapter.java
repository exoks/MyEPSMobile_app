package com.example.eps;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoHolder> {
    Context c;
    ArrayList<VideoClassModel> videos;
    OnVideoClickListener listener;

    public VideoAdapter(Context c, ArrayList<VideoClassModel> videos, OnVideoClickListener listener) {
        this.c = c;
        this.videos = videos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public VideoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VideoHolder(LayoutInflater.from(c).inflate(R.layout.video_item_holder,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull VideoHolder holder, int position) {
        holder.title.setText(videos.get(position).getVideoTitle());
        Glide.with(c).load(videos.get(position).getImageTempnail()).into(holder.tumpnail);
        holder.title.setTag(videos.get(position));
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }


    public class VideoHolder extends RecyclerView.ViewHolder{
        TextView title;
        ImageView tumpnail;
        public VideoHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.video_title);
            tumpnail = itemView.findViewById(R.id.video_tumpnail);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onVideoClick((VideoClassModel) title.getTag());
                }
            });
        }
    }
    interface OnVideoClickListener {
        void onVideoClick(VideoClassModel video);
    }
}