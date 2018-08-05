package com.example.manhnd16.mp3zingproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.manhnd16.mp3zingproject.R;
import com.example.manhnd16.mp3zingproject.model.PlayList;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AllPlaylistAdapter extends RecyclerView.Adapter<AllPlaylistAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<PlayList> mPlayListArrayList;

    public AllPlaylistAdapter(Context mContext, ArrayList<PlayList> mPlayListArrayList) {
        this.mContext = mContext;
        this.mPlayListArrayList = mPlayListArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.all_playlist_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PlayList playList = mPlayListArrayList.get(position);
        Picasso.with(mContext).load(playList.getPlaylistImage()).into(holder.imgAvatar);
        holder.txtPlaylistName.setText(playList.getPlaylistName());
    }

    @Override
    public int getItemCount() {
        return mPlayListArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgAvatar;
        TextView txtPlaylistName;

        public ViewHolder(View itemView) {
            super(itemView);
            imgAvatar = itemView.findViewById(R.id.all_playlist_imageview);
            txtPlaylistName = itemView.findViewById(R.id.all_playlist_name_playlist_textview);
        }
    }
}
