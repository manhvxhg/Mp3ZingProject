package com.example.manhnd16.mp3zingproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.manhnd16.mp3zingproject.R;
import com.example.manhnd16.mp3zingproject.model.Kind;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class KindsAdapter extends RecyclerView.Adapter<KindsAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Kind> mKindArrayList;

    public KindsAdapter(Context mContext, ArrayList<Kind> kindArrayList) {
        this.mContext = mContext;
        this.mKindArrayList = kindArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.grid_layout_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Kind kind = mKindArrayList.get(position);
        Picasso.with(mContext).load(kind.getKindImage()).into(holder.imgAvatar);
        holder.txtKindName.setText(kind.getKindName());
    }

    @Override
    public int getItemCount() {
        return mKindArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgAvatar;
        TextView txtKindName;

        public ViewHolder(View itemView) {
            super(itemView);
            imgAvatar = itemView.findViewById(R.id.grid_avatar_imageview);
            txtKindName = itemView.findViewById(R.id.grid_name_textview);
        }
    }
}
