package com.example.manhnd16.mp3zingproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.manhnd16.mp3zingproject.R;
import com.example.manhnd16.mp3zingproject.activity.ListAllAlbumActivity;
import com.example.manhnd16.mp3zingproject.activity.ListSongActivity;
import com.example.manhnd16.mp3zingproject.constant.Constant;
import com.example.manhnd16.mp3zingproject.model.Album;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by mac on 7/28/18.
 */

public class AlbumHotAdapter extends RecyclerView.Adapter<AlbumHotAdapter.ViewHolder> {
    private Context mContext;
    ArrayList<Album> albumArrayList;

    public AlbumHotAdapter(Context mContext, ArrayList<Album> albumArrayList) {
        this.mContext = mContext;
        this.albumArrayList = albumArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.album_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Album album = albumArrayList.get(position);
        holder.albumName.setText(album.getAlbumName());
        holder.singerName.setText(album.getSingerName());
        Picasso.with(mContext).load(album.getAlbumImage()).into(holder.albumImage);
    }

    @Override
    public int getItemCount() {
        return albumArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView albumImage;
        TextView albumName, singerName;

        public ViewHolder(View itemView) {
            super(itemView);
            albumImage = itemView.findViewById(R.id.album_imageview);
            albumName = itemView.findViewById(R.id.album_name_textview);
            singerName = itemView.findViewById(R.id.singer_name_textview);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, ListSongActivity.class);
                    intent.putExtra(Constant.INTENT_NAME_ALBUM, albumArrayList.get(getAdapterPosition()));
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
