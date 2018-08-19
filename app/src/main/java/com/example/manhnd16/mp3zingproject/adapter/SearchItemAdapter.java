package com.example.manhnd16.mp3zingproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manhnd16.mp3zingproject.R;
import com.example.manhnd16.mp3zingproject.activity.PlayMusicActivity;
import com.example.manhnd16.mp3zingproject.constant.Constant;
import com.example.manhnd16.mp3zingproject.model.Song;
import com.example.manhnd16.mp3zingproject.service.ApiService;
import com.example.manhnd16.mp3zingproject.service.ServiceListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchItemAdapter extends RecyclerView.Adapter<SearchItemAdapter.SearchViewHolder> {

    private Context mContext;
    private ArrayList<Song> mSongArrayList;

    public SearchItemAdapter(Context mContext, ArrayList<Song> mSongArrayList) {
        this.mContext = mContext;
        this.mSongArrayList = mSongArrayList;
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.search_item_row, parent, false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        Song song = mSongArrayList.get(position);
        holder.txtSongName.setText(song.getSongName());
        holder.txtSingerName.setText(song.getSingerName());
        Picasso.with(mContext).load(song.getSongImage()).into(holder.imgSearchItem);
    }

    @Override
    public int getItemCount() {
        return mSongArrayList.size();
    }

    class SearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgSearchItem, imgFavourite;
        TextView txtSongName, txtSingerName;

        public SearchViewHolder(View itemView) {
            super(itemView);
            imgSearchItem = itemView.findViewById(R.id.search_item_imageview);
            imgFavourite = itemView.findViewById(R.id.search_item_favourite_imageview);
            txtSongName = itemView.findViewById(R.id.search_item_name_song);
            txtSingerName = itemView.findViewById(R.id.search_item_name_singer);
            RelativeLayout relativeLayout = itemView.findViewById(R.id.search_item_relative_layout);
            relativeLayout.setOnClickListener(this);
            imgFavourite.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.search_item_relative_layout:
                    Intent intent = new Intent(mContext, PlayMusicActivity.class);
                    intent.putExtra(Constant.INTENT_SONG, mSongArrayList.get(getAdapterPosition()));
                    mContext.startActivity(intent);
                    break;
                case R.id.search_item_favourite_imageview:
                    imgFavourite.setImageResource(R.drawable.iconloved);
                    ServiceListener serviceListener = ApiService.getService();
                    Call<String> callback = serviceListener.updateLikeCount("1", mSongArrayList.get(getAdapterPosition()).getSongId());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String result = response.body();
                            if (Constant.SUCCESS.equals(result)) {
                                Toast.makeText(mContext, "Liked!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(mContext, "Failed!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                    imgFavourite.setEnabled(false);
                    break;
                default:
                    break;
            }
        }
    }
}
