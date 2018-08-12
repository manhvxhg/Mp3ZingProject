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
import com.example.manhnd16.mp3zingproject.model.Album;
import com.example.manhnd16.mp3zingproject.model.Kind;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Album> mAlbumArrayList;

    public AlbumsAdapter(Context mContext, ArrayList<Album> albumArrayList) {
        this.mContext = mContext;
        this.mAlbumArrayList = albumArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.grid_layout_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Album album = mAlbumArrayList.get(position);
        Picasso.with(mContext).load(album.getAlbumImage()).into(holder.imgAvatar);
        holder.txtAlbumName.setText(album.getAlbumName());
    }

    @Override
    public int getItemCount() {
        return mAlbumArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imgAvatar;
        TextView txtAlbumName;

        public ViewHolder(View itemView) {
            super(itemView);
            imgAvatar = itemView.findViewById(R.id.grid_avatar_imageview);
            txtAlbumName = itemView.findViewById(R.id.grid_name_textview);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(mContext, ListSongActivity.class);
            intent.putExtra(Constant.INTENT_NAME_ALBUM, mAlbumArrayList.get(getAdapterPosition()));
            mContext.startActivity(intent);
        }
    }
}
