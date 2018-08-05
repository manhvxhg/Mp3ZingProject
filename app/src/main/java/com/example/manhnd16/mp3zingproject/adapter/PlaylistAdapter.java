package com.example.manhnd16.mp3zingproject.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.manhnd16.mp3zingproject.R;
import com.example.manhnd16.mp3zingproject.model.PlayList;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by mac on 7/16/18.
 */

public class PlaylistAdapter extends ArrayAdapter<PlayList> {
    private Context mContext;
    private List<PlayList> mPlayListArrayList;

    public PlaylistAdapter(@NonNull Context context, int resource, @NonNull List<PlayList> objects) {
        super(context, resource, objects);
        mContext = context;
        mPlayListArrayList = objects;
    }
    class PlaylistViewHolder {
        TextView txtPlaylistName;
        ImageView imgBackground, imgIcon;
    }
    class AllPlaylistViewHolder {
        TextView txtPlaylistName, txtPlaylistVarious;
        ImageView imgIcon;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }
    @Nullable
    @Override
    public PlayList getItem(int position) {
        return mPlayListArrayList.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        PlaylistViewHolder playlistViewHolder = null;
        AllPlaylistViewHolder allPlaylistViewHolder = null;
            if (convertView == null) {
                LayoutInflater layoutInflater = LayoutInflater.from(mContext);
                convertView = layoutInflater.inflate(R.layout.playlist_row, null);
                playlistViewHolder = new PlaylistViewHolder();
                playlistViewHolder.txtPlaylistName = convertView.findViewById(R.id.playlist_name_textview);
                playlistViewHolder.imgBackground = convertView.findViewById(R.id.playlist_background_imagebutton);
                playlistViewHolder.imgIcon = convertView.findViewById(R.id.playlist_avatar_imageview);
                convertView.setTag(playlistViewHolder);
            } else {
                playlistViewHolder = (PlaylistViewHolder) convertView.getTag();
            }
            PlayList playList = getItem(position);
            Picasso.with(mContext).load(playList.getPlaylistImage()).into(playlistViewHolder.imgBackground);
            Picasso.with(mContext).load(playList.getPlaylistIcon()).into(playlistViewHolder.imgIcon);
            playlistViewHolder.txtPlaylistName.setText(playList.getPlaylistName());

        return convertView;
    }
}
