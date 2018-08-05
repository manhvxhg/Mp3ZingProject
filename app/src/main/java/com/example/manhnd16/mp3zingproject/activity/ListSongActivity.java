package com.example.manhnd16.mp3zingproject.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.manhnd16.mp3zingproject.R;
import com.example.manhnd16.mp3zingproject.adapter.ListSongAdapter;
import com.example.manhnd16.mp3zingproject.constant.Constant;
import com.example.manhnd16.mp3zingproject.model.Advertisement;
import com.example.manhnd16.mp3zingproject.model.PlayList;
import com.example.manhnd16.mp3zingproject.model.Song;
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
    private PlayList mPlayList;
    private CoordinatorLayout mCoordinatorLayout;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private FloatingActionButton mFloatingActionButton;
    private ListSongAdapter mAdapter;
    private ImageView mListSongImage;
    private ArrayList<Song> mSongArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_song);
        fetchDataIntent();
        referView();
        init();
        if (mAdvertisement != null) {
            setValueInitView(mAdvertisement.getSongName(), mAdvertisement.getSongImage());
            getDataSongAds(mAdvertisement.getAdvertisementId());
        }
        if (mPlayList != null) {
            setValueInitView(mPlayList.getPlaylistName(), mPlayList.getPlaylistImage());
            getDataPlaylist(mPlayList.getPlaylistId());
        }
    }

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

    private void setValueInitView(String name, String image) {
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
        Picasso.with(this).load(image).into(mListSongImage);
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
        mListSongImage = findViewById(R.id.list_song_imageview);
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
            if (intent.hasExtra(Constant.INTENT_NAME_PLAYLIST)) {
                mPlayList = (PlayList) intent.getSerializableExtra(Constant.INTENT_NAME_PLAYLIST);
            }
        }
    }

    /**
     * get data play list id
     * @param playlistId
     */
    private void getDataPlaylist(String playlistId) {
        ServiceListener serviceListener = ApiService.getService();
        Call<List<Song>> callback = serviceListener.getListSongByPlaylist(playlistId);
        callback.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                mSongArrayList = (ArrayList<Song>) response.body();
                mAdapter = new ListSongAdapter(ListSongActivity.this, mSongArrayList);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(ListSongActivity.this));
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {

            }
        });
    }

    /**
     * get data song by ads
     * @param idAds
     */
    private void getDataSongAds(String idAds) {
        ServiceListener serviceListener = ApiService.getService();
        Call<List<Song>> callback = serviceListener.getListSong(idAds);
        callback.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                mSongArrayList = (ArrayList<Song>) response.body();
                mAdapter = new ListSongAdapter(ListSongActivity.this, mSongArrayList);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(ListSongActivity.this));
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {

            }
        });
    }
}
