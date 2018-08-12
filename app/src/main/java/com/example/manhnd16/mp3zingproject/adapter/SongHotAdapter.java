package com.example.manhnd16.mp3zingproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.manhnd16.mp3zingproject.R;
import com.example.manhnd16.mp3zingproject.activity.ListSongActivity;
import com.example.manhnd16.mp3zingproject.constant.Constant;
import com.example.manhnd16.mp3zingproject.model.Song;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SongHotAdapter extends RecyclerView.Adapter<SongHotAdapter.SongViewHolder>{
    private Context mContext;
    private ArrayList<Song> songArrayList;

    public SongHotAdapter(Context mContext, ArrayList<Song> songArrayList) {
        this.mContext = mContext;
        this.songArrayList = songArrayList;
    }

    @Override
    public SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.song_hot_row, parent, false);
        return new SongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SongViewHolder holder, int position) {
        Song song = songArrayList.get(position);
        holder.songName.setText(song.getSongName());
        holder.singerName.setText(song.getSingerName());
        Picasso.with(mContext).load(song.getSongImage()).into(holder.songImage);
    }

    @Override
    public int getItemCount() {
        return songArrayList.size();
    }

    public class SongViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView songImage, favouriteImage;
        TextView songName, singerName;
        public SongViewHolder(View itemView) {
            super(itemView);
            songName = itemView.findViewById(R.id.name_song_hot_textview);
            singerName = itemView.findViewById(R.id.singer_name_song_hot_textview);
            songImage = itemView.findViewById(R.id.image_song_hot_imageview);
            favouriteImage = itemView.findViewById(R.id.favourite_imageview);

        }

        @Override
        public void onClick(View view) {

        }
    }
}
