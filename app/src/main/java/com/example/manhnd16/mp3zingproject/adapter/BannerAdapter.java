package com.example.manhnd16.mp3zingproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manhnd16.mp3zingproject.R;
import com.example.manhnd16.mp3zingproject.activity.ListSongActivity;
import com.example.manhnd16.mp3zingproject.constant.Constant;
import com.example.manhnd16.mp3zingproject.model.Advertisement;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by mac on 7/6/18.
 */

public class BannerAdapter extends PagerAdapter {
    Context context;
    ArrayList<Advertisement> advertisementArrayList;

    public BannerAdapter(Context context, ArrayList<Advertisement> advertisementArrayList) {
        this.context = context;
        this.advertisementArrayList = advertisementArrayList;
    }

    @Override
    public int getCount() {
        return advertisementArrayList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.banner_row, null);
        ImageView imgBackgroundBanner = view.findViewById(R.id.imageview_background_banner);
        ImageView imgSongBanner = view.findViewById(R.id.banner_imageview);
        final TextView txtSongBanner = view.findViewById(R.id.title_banner_song_textview);
        TextView txtContent = view.findViewById(R.id.content_banner_textview);
        Picasso.with(context).load(advertisementArrayList.get(position).getAdvertisementImage()).into(imgBackgroundBanner);
        Picasso.with(context).load(advertisementArrayList.get(position).getSongImage()).into(imgSongBanner);
        txtSongBanner.setText(advertisementArrayList.get(position).getSongName());
        txtContent.setText(advertisementArrayList.get(position).getAdvertisementContent());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ListSongActivity.class);
                intent.putExtra(Constant.INTENT_NAME_BANNER, advertisementArrayList.get(position));
                context.startActivity(intent);
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
