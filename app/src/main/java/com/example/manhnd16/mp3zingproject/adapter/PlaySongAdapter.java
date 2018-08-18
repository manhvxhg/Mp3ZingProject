package com.example.manhnd16.mp3zingproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.manhnd16.mp3zingproject.R;
import com.example.manhnd16.mp3zingproject.model.Song;

import java.util.ArrayList;

public class PlaySongAdapter extends RecyclerView.Adapter<PlaySongAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<Song> mSongArrayList;

    public PlaySongAdapter(Context mContext, ArrayList<Song> mSongArrayList) {
        this.mContext = mContext;
        this.mSongArrayList = mSongArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.play_song_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Song song = mSongArrayList.get(position);
        holder.txtindex.setText(position + 1 + "");
        holder.txtsongname.setText(song.getSongName());
        holder.txtsingername.setText(song.getSingerName());
    }

    @Override
    public int getItemCount() {
        return mSongArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtindex, txtsongname,txtsingername;
        public ViewHolder(View itemView) {
            super(itemView);
            txtindex = itemView.findViewById(R.id.play_music_row_index_textview);
            txtsongname = itemView.findViewById(R.id.play_music_row_name_song_textview);
            txtsingername = itemView.findViewById(R.id.play_music_row_name_singer_textview);
        }
    }
}
