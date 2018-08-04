package com.example.manhnd16.mp3zingproject.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.manhnd16.mp3zingproject.R;
import com.example.manhnd16.mp3zingproject.constant.Constant;
import com.example.manhnd16.mp3zingproject.model.Advertisement;
import com.example.manhnd16.mp3zingproject.model.PlayList;
import com.example.manhnd16.mp3zingproject.model.Song;
import com.example.manhnd16.mp3zingproject.service.APIRetrofitClient;
import com.example.manhnd16.mp3zingproject.service.ApiService;
import com.example.manhnd16.mp3zingproject.service.ServiceListener;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListSongActivity extends AppCompatActivity {
    private Advertisement mAdvertisement;
    private CoordinatorLayout mCoordinatorLayout;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private FloatingActionButton mFloatingActionButton;
    private ImageView mListSongImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_song);
        fetchDataIntent();
        referView();
        init();
        if (mAdvertisement != null && !mAdvertisement.getAdvertisementId().equals("")) {
            setupView(mAdvertisement.getSongName(), mAdvertisement.getSongImage());
            getDataAds(mAdvertisement.getAdvertisementId());
        }
    }


    private void setupView(String name, String image) {
        mCollapsingToolbarLayout.setTitle(name);
        try {
            URL url = new URL(image);
            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                mCollapsingToolbarLayout.setBackground(bitmapDrawable);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Picasso.with(this).load(image).into(mListSongImageView);
    }

    private void getDataAds(String idAds) {
        ServiceListener serviceListener = ApiService.getService();
        Call<List<Song>> callBack = serviceListener.getListSongByAds(idAds);
        callBack.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                ArrayList<Song> songArrayList = (ArrayList<Song>) response.body();
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {

            }
        });
    }


    /**
     * Init actionbar
     */
    private void init() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mCollapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
    }

    /**
     * Reference view
     */
    private void referView() {
        mCoordinatorLayout = findViewById(R.id.list_song_coordinator_layout);
        mCollapsingToolbarLayout = findViewById(R.id.list_song_collapsing_toolbar_layout);
        mToolbar = findViewById(R.id.list_song_toolbar);
        mRecyclerView = findViewById(R.id.list_song_recyclerview);
        mFloatingActionButton = findViewById(R.id.list_song_floating_action_button);
        mListSongImageView = findViewById(R.id.list_song_imageview);
    }

    /**
     * Get data from intent
     */
    private void fetchDataIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra(Constant.INTENT_NAME_BANNER)) {
                mAdvertisement = (Advertisement) intent.getSerializableExtra(Constant.INTENT_NAME_BANNER);
            }
        }
    }
}
