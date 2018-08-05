package com.example.manhnd16.mp3zingproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.manhnd16.mp3zingproject.R;
import com.example.manhnd16.mp3zingproject.model.Song;

import java.util.ArrayList;

/**
 * Created by mac on 8/4/18.
 */

public class ListSongAdapter extends RecyclerView.Adapter<ListSongAdapter.ListSongViewHolder>{
    private Context mContext;
    private ArrayList<Song> mSongArrayList;

    public ListSongAdapter(Context mContext, ArrayList<Song> mSongArrayList) {
        this.mContext = mContext;
        this.mSongArrayList = mSongArrayList;
    }

    @Override
    public ListSongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.list_song_row, parent, false);
        return new ListSongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListSongViewHolder holder, int position) {
        Song song = mSongArrayList.get(position);
        holder.singerNameTextView.setText(song.getSingerName());
        holder.songNameTextView.setText(song.getSongName());
        holder.indexSong.setText((position + 1) + "");
        
    }

    @Override
    public int getItemCount() {
        return mSongArrayList.size();
    }

    public class ListSongViewHolder extends RecyclerView.ViewHolder {
        ImageView favouriteImage;
        TextView indexSong, songNameTextView, singerNameTextView;
        public ListSongViewHolder(View itemView) {
            super(itemView);
            favouriteImage = itemView.findViewById(R.id.favourite_image_list_song_detail);
            indexSong = itemView.findViewById(R.id.list_song_index_textview);
            songNameTextView = itemView.findViewById(R.id.list_song_name_textview);
            singerNameTextView = itemView.findViewById(R.id.list_song_sing_name_textview);
        }
    }

}
